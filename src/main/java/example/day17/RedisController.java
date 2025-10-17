package example.day17;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {

    // Redis에 접근하는 객체 생성
    private final RedisTemplate redisTemplate;
    private final RedisTemplate<String, Object> MapRedisTemplate;
    private final RedisTemplate<String, Object> studentTemplate;

    // 1. 간단한 텍스트를 Redis 서버에 저장 및 호출
    // 프로젝트에서는 서비스에서 사용
    @GetMapping("/test")
    public ResponseEntity<?> test(){
        System.out.println("RedisController.test");
        // [1-1] 저장
        // Redis에 key-value 저장 : redisTemplate.opsForValue().set(key, value);
        MapRedisTemplate.opsForValue().set("유재석","90");    // 임의 데이터1 -> {"유재석" : "90"}
        MapRedisTemplate.opsForValue().set("강호동","80");    // 임의 데이터2 -> {"강호동" : "80"}
        MapRedisTemplate.opsForValue().set("유재석","100");    // key는 중복을 허용하지 않고, value는 중복을 허용한다.

        // [1-2] 호출
        // Redis에 저장된 모든 key 반환 : redisTemplate.keys("*")
        // 특정한 key의 값 호출 : redisTemplate.opsForValue().get(key);
        Set<String> keys = MapRedisTemplate.keys("*");
        List<Object> values = new ArrayList<>();
        for (String key : keys){
            values.add(MapRedisTemplate.opsForValue().get(key));
        } // for end
        return ResponseEntity.ok(values);
    } // func end

    // day13|day06 CRUD를 Redis로 구현
    // 1. 등록
    @PostMapping("")
    private ResponseEntity<?> save(@RequestBody StudentDto studentDto){
        System.out.println("studentDto = " + studentDto);
        // 0. 중복없는 key 구상
        String key = "student:" + studentDto.getSno();  // sno를 조합하여 구성, 예) student:1, student:2
        // 1. Redis에 전달받은 값을 저장한다.
        studentTemplate.opsForValue().set(key, studentDto);
        return ResponseEntity.ok().body("[저장성공]");
    } // func end

    // 2. 전체조회
    @GetMapping("")
    private ResponseEntity<?> findAll(){
        // 0. 조회할 key를 모두 가져온다.
        // studentTemplate.keys("문자열*"); -> 문자열까지 동일하면, *는 서로다른 문자열 패턴
        Set<String> keys = studentTemplate.keys("student:*");
        // 1. 반복문을 이용한 value 꺼내기
        List<Object> values = new ArrayList<>();
        for (String key : keys){
            values.add(studentTemplate.opsForValue().get(key));
        } // for end
        return ResponseEntity.ok().body(values);
    } // func end

    // 3. 개별조회
    @GetMapping("/bySno")
    public ResponseEntity<?> getBySno(@RequestParam int sno){
        // 1. 조회할 key 구성
        String key = "student:" + sno;
        // 2. 특정한 key의 value 호출
        Object value = studentTemplate.opsForValue().get(key);
        return ResponseEntity.ok().body(value);
    } // func end

    // 4. 개별삭제
    @DeleteMapping("")
    public ResponseEntity<?> deleteBySno(@RequestParam int sno){
        // 1. 삭제할 key 구성
        String key = "student:" + sno;
        // 2. 특정한 key를 이용한 entry(key-value) 삭제
        // redisTemplate.delete(key); 성공 : true / 실패 : false
        boolean result = studentTemplate.delete(key);
        return ResponseEntity.ok(result);
    } // func end

    // 5. 개별수정
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody StudentDto studentDto){
        // 0. key가 keys에 존재하는지 유효성 검사 필요
        // 1. 수정할 key 구성
        String key = "student:" + studentDto.getSno();
        // 2. 특정한 key를 이용하여 수정(= 사실상 등록이랑 같음, key가 같으면 덮어씌어지기 때문에)
        studentTemplate.opsForValue().set(key, studentDto);
        return ResponseEntity.ok("[수정성공]");
    } // func end

    // * 인증코드를 발급하여 특정한 기간에만 데이터 저장 - Redis 유효기간 설정
    // TTL : Redis에 저장된 Entry를 특정한 시간이 되면(기간이 지나면) 자동 삭제
    @GetMapping("/auth/send")
    public ResponseEntity<?> authSend(@RequestParam String phone){
        // 1. key를 구성한다. -> auth:전화번호
        String key = "auth:" + phone;
        // 2. 인증코드 생성하기 - 난수 6자리
        String code = String.format("%06d", new Random().nextInt(999999));
        // 3. Redis에 key-인증코드 저장하기 + TTL(유효시간) 설정하기 : Duration.ofXXX()
        redisTemplate.opsForValue().set(key, code, Duration.ofSeconds(15));     // 15초의 유효기간 설정
        // 4. API를 이용하여 전화번호에게 인증코드 전송(했다 치고)

        return ResponseEntity.ok("인증코드 발급 완료 : " + code);
    } // func end
    @GetMapping("/auth/confirm")
    public ResponseEntity<?> authConfirm(@RequestParam String phone, @RequestParam String code){
        // 1. 조회할 key를 구성한다
        String key = "auth:" + phone;
        // 2. 특정한 key를 이용하여 value 호출
        Object savedCode = redisTemplate.opsForValue().get(key);
        // 3. savedCode와 입력받은 code 비교하기
        if (savedCode == null){
            return ResponseEntity.ok("[인증실패] : 인증만료 || 전화번호 불일치");
        } else if (savedCode.equals(code)){
            redisTemplate.delete(key);      // 안전하게 삭제
            return ResponseEntity.ok("[인증성공]");
        } else {
            return ResponseEntity.ok("[인증실패] : 코드 불일치");
        } // if end
    } // func end
} // class end
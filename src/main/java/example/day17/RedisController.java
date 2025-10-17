package example.day17;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    // 2. 전체 조회
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
} // class end
package example.Day05.HTTP_ResponseObject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController                         // HTTP 요청과 응답을 처리하는 어노테이션
@RequestMapping("/task/day05")       // HTTP URL을 매핑하는 어노테이션
public class ResponseController {

    // HTTP 응답객체 사용해보기
    // 1. 401 코드 + Boolean 보내기
    @GetMapping("/bool")
    public ResponseEntity<Boolean> method1(){

        return ResponseEntity.status( 401 ).body( false );
        // 401 : 인증 실패
    } // func end

    // 2. 202 코드 + Integer 보내기
    @GetMapping("/int")
    public ResponseEntity<Integer> method2(){

        return ResponseEntity.status( 202 ).body( 1 );
        // 202 : 요청 성공 + 아직 처리중 -> 비동기 처리할 때 발생
    } // func end

    // 3. 201 코드 + String 보내기
    @GetMapping("/string")
    public ResponseEntity<String> method3(){

        return ResponseEntity.status( 201 ).body( "qwe123" );
        // 201 : 요청 성공 + 저장 성공
        // + 저장 성공한 아이디 반환
    } // func end

    // 4. 403 코드 + void 보내기
    @GetMapping("/void")
    public ResponseEntity<Void> method4(){

        return ResponseEntity.status( 403 ).build();
        // 403 : 접근권한 없음 + 권한 거부
        // void일 경우, .build()로 종료
    } // func end

    // 5. + Map 보내기
    @GetMapping("/object")
    public ResponseEntity<Map<String, String>> method5(){
        try {
            // 강제로 예외 발생시키기 -> catch로 넘어감
            // Integer.parseInt("a");

            Map<String,String> map = new HashMap<>();
            map.put( "key", "value" );
            return ResponseEntity.status( 200 ).body( map );
            // 200 : 요청 성공 + 응답 데이터 O
        } catch ( Exception e ){
            return ResponseEntity.status( 500 ).build();
            // 500 : 서버 오류
        } // try-catch end
    } // func end
} // class end
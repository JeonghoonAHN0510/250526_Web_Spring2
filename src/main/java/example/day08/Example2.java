package example.day08;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// [3] AOP 커스텀
@Aspect         // AOP 클래스를 스프링 AOP 컨테이너에 등록
@Component      // AOP 클래스를 스프르이 빈 컨테이너에 등록
class AopClass{
    // [3-1] AopService의 모든 메소드가 실행되면, 같이 실행
    // @Before("execution( 반환타입 경로.메소드명() )")
    // 반환타입 : 어떠한 반환 타입에 적용할 지
        // * : 모든 반환 타입의 메소드들
    // 경로 : 적용할 메소드가 위치한 경로 -> java 이하 경로부터
        // -> 같은 패키지 : AopService
        // -> 다른 패키지 : example.day08.AopService
    // 메소드명 : 적용할 메소드명
    // () : 매개변수
        // .. : 해당 메소드의 모든 매개변수를 갖는 곳에 적용
        // ( int, boolean ) : int, boolean을 갖는 메소드에만 적용
    @Before("execution( * AopService.*(..) )")
    public void check1(){
        System.out.println("[입장 전 열 체크!]");
    } // func end
    // [3-2] AopService
    @After("execution( * AopService.*(..) )")
    public void check2(){
        System.out.println("[퇴장 후 체크!]");
    } // func end
} // class end

// [2] 간단한 서비스
@Service
class AopService{
    public void enter1(){
        System.out.println(">>학원 입장<<");
    } // func end
    public void enter2(){
        System.out.println(">>식당 입장<<");
    } // func end
} // class end

// [1] 간단한 HTTP 컨트롤러
@RestController
class AopController{
    @Autowired
    AopService aopService;
    @GetMapping("/day08/aop")
    public void method(){
        aopService.enter1();
        aopService.enter2();
    } // func end
} // class end

@SpringBootApplication
public class Example2 {
    public static void main(String[] args) {

        SpringApplication.run( Example2.class );

    } // main end
} // class end


//[ AOP ]
//- 관점지향 프로그래밍
//- 부가기능을 하나로 모듈화하여 핵심 비지니스 로직을 분리한다.
//    -> 예시) 로그(기록) 처리, 트랜잭션 처리, 보안(인증/권한) ···
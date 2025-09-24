package example.day08;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
/*
[ AOP ] Aspect Oriented Programming
- 관점지향 프로그래밍
- 부가기능을 하나로 모듈화하여 핵심 비지니스 로직을 분리한다.
    -> 예시) 로그(기록) 처리, 트랜잭션 처리, 보안(인증/권한) ···
    -> 유효성 검사도 가능...?? -> joinPoint.getArgs()로 받아서 조건문?
*/

// [3] AOP 커스텀
@Aspect         // AOP 클래스를 스프링 AOP 컨테이너에 등록
@Component      // AOP 클래스를 스프르이 빈 컨테이너에 등록
class AopClass{
    // @Before("execution( 반환타입 경로.메소드명() )")
    // 반환타입 : 어떠한 반환 타입에 적용할 지
        // * : 모든 반환 타입의 메소드들
    // 경로 : 적용할 메소드가 위치한 경로 -> java 이하 경로부터
        // -> 같은 패키지 : AopService
        // -> 다른 패키지 : example.day08.AopService
    // 메소드명 : 적용할 메소드명
    // () : 매개변수
        // .. : 해당 메소드의 모든 매개변수를 갖는 곳에 적용
        // ( int, boolean ) : 매개변수로 int, boolean을 갖는 메소드에만 적용

    // [3-1] AopService - Before
    @Before("execution( * AopService.*(..) )")
    public void check1(){
        System.out.println("[3-1] [입장 전 열 체크!]");
    } // func end

    // [3-2] AopService - After
    @After("execution( * AopService.*(..) )")
    public void check2(){
        System.out.println("[3-2] [퇴장 후 체크!]");
    } // func end

    // [3-3] AopService - After + 전체경로
    @After("execution( * example.day08.AopService.enter1(..) )")
    public void check3(){
        System.out.println("[3-3] enter1 자동실행");
    } // func end

    // [3-4] AopService - Before + 반환타입 + 매개변수타입
    @Before("execution( boolean AopService.enter3( String ) ) && args(name)")
    // + && args( 매개변수명, ··· ) : 매개변수 값들을 연결할 이름을 추가
    public void check4( String name ){
        System.out.println("[3-4] enter3에서 매개변수 \t name : " + name );
    } // func end

    // [3-5] AopService - AfterReturning
    // Service로부터 리턴값을 반환받을 수 있다.
    @AfterReturning(
            value = "execution( boolean AopService.enter3(..) )",
            returning = "result"
    )
    // , returning="" -> 매개변수로 받을 반환값의 이름을 매핑
    // *으로 정의하면, Object로 처리
    public void check5( boolean result ){
        System.out.println("[3-5] enter3에서 반환값 \t result : " + result );
    } // func end

    // [3-6] Around - 직접 실행 시점을 지정
    @Around("execution( * AopService.enter3(..) )")
    public Object check6( ProceedingJoinPoint joinPoint ) throws Throwable {
        // 반환타입을 *로 했으므로, 반환타입 Object
        // ProceedingJoinPoint
        // 1. joinPoint 확인
        System.out.println( "[3-6] joinPoint : " + joinPoint );
        // 2. 해당 AOP 메소드를 실행한 비지니스 함수 확인 : .getSignature()
        System.out.println( "[3-6] 실행할 메소드 : " + joinPoint.getSignature() );
        // 3. 실행한 대상의 매개변수 확인 : .getArgs() -> 배열로 꺼내진다.
        System.out.println( "[3-6] 실행할 메소드의 매개변수들 : " + Arrays.toString(joinPoint.getArgs()) );
        // 4. 실행 시점을 커스텀 : 이것을 기준으로 Before - After로 나뉨
        // 실행결과를 Object로 받을 수 있다.
        Object result = joinPoint.proceed();
        System.out.println( "[3-6] 실행 결과 : " + result );
        // 5. 실행한 대상의 반환값을 그대로 반환해야한다. -> 그렇지 않으면, 오류가 발생한다.
        return result;
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
    public boolean enter3( String name ){
        System.out.println(">>헬스장 입장<<");
        return true;
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
        aopService.enter3("유재석");
    } // func end
} // class end

@SpringBootApplication
public class Example2 {
    public static void main(String[] args) {

        SpringApplication.run( Example2.class );

    } // main end
} // class end
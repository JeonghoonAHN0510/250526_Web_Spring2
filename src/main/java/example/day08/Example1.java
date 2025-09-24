package example.day08;

class TestService{
    // 만약 학원과 식당에서 같은 코드를 실행한다면 -->
    // 방법1) 열체크 함수화
    // 방법2) 관점지향 프로그래밍(AOP)


    // enter1
    public void enter1(){
        System.out.println("[입장 전 열 체크!]");
        System.out.println(">>식당 입장<<");
    } // func end
    // enter2
    public void enter2(){
        System.out.println("[입장 전 열 체크!]");
        System.out.println(">>학원 입장<<");
    } // func end
} // class end

public class Example1 {
    public static void main(String[] args) {
        TestService testService = new TestService();

        testService.enter1();   // 메소드1 호출
        testService.enter2();   // 메소드2 호출
    } // main end
} // class end
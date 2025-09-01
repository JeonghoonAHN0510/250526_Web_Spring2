package example.Day01._1SpringThread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ThreadService {

    public int thread1(){
        int result = 0;
        // 반복문을 통해 값 누적하기
        for ( int i = 1; i <= 10; i++ ){
            result += i;
            // 만약에 로직 처리가 늦어진다면, 반환은 어떻게 될까?
            // -> 늦어진만큼 반환이 늦어진다.
            try {
                // 현재 스레드를 1초간 일시정지
                Thread.sleep( 1000 );
            } catch ( Exception e ) {
                System.out.println( e );
            } // try-catch end
            System.out.println( i + " second" );
        } // for end
        // 누적된 값 반환하기
        return result;
    } // func end

    @Async  // 비동기 처리방식
    public void thread2(){
        int result = 0;
        // 반복문을 통해 누적하기
        for ( int i = 1; i <= 10; i++ ){
            result += i;
            try {
                // 현재 스레드를 1초간 일시정지
                Thread.sleep( 1000 );
            } catch ( Exception e ) {
                System.out.println( e );
            } // try-catch end
            System.out.println( i + " second" );
        } // for end
        // 누적된 값 반환하기
        // return result;
    } // func end
} // class end
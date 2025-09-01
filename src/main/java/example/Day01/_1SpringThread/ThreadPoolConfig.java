package example.Day01._1SpringThread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration  // 스프링 컨테이너에 Bean 등록
public class ThreadPoolConfig {

    // [1] Spring 멀티스레드 설정
    // Executor : java.util로 import
    @Bean   // 해당 메소드를 스프링 컨테이너에 Bean 등록
    public Executor taskExecutor(){
        // 1. ThreadPoolTaskExecutor 객체 생성
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 2. 최소 실행 스레드 개수 설정하기
        executor.setCorePoolSize( 2 );
        // 3. 최대 실행 스레드 개수 설정하기
        executor.setMaxPoolSize( 10 );
        // 4. 최대 대기 개수 설정 -> 최대를 넘어서면, 503 오류 발생
        executor.setQueueCapacity( 20 );
        // 5. 스레드풀 초기화 -> 서버 재실행마다 초기화
        executor.initialize();
        // 6. 최종적으로 반환
        return executor;
    } // func end
} // class end
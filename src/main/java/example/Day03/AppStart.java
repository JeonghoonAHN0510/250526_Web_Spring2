package example.Day03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // 스프링 컨테이너에 등록된 bean을 스캔
public class AppStart {
    public static void main(String[] args) {

        SpringApplication.run( AppStart.class );

    } // main end
} // class end
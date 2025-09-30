package example.day12;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 설정 관련 빈 등록
public class CorsConfig implements WebMvcConfigurer {
    // 스프링 웹 MVC 설정 재구현

    // 1. CORS 관련 매핑 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // registry.addMapping("허용할 컨트롤러 URL")
        // .allowedOrigins("허용할 도메인")
        // .allowedMethods("허용할 HTTP 메소드");
        registry.addMapping("/axios")                               // 전체 : /**
                .allowedOrigins("http://localhost:5173")            // 전체 : *
                .allowedOrigins("http://localhost:5174")            // react가 2개 켜졌을 때의 도메인
                .allowedMethods("GET", "POST", "PUT", "DELETE");    // 전체 : *

    } // func end
} // class end
package web2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// =============================== 스프링 시큐리티 커스텀 ===============================
// 시큐리티 검증/확인
// 미리 만들어진 필터들을 커스텀 수정/제외
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    // 내가 만든 토큰을 스프링 시큐리티 방식으로 통합한 클래스
    private final JwtAuthFilter jwtAuthFilter;

    // [0] username : user , password : 콘솔에 password 복사하여 붙여넣기
    // [1] HTTP 관련 필터들을 커스텀
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // 1-1. HTTP 요청에 따른 권한 커스텀
        // authorizeHttpRequests(auth -> auth.requestMatches("경로").권한);
        // .permitAll() : 모든 권한 허용
        // .hasRole("권한명") : "권한명"일때만 허용
        // .hasAnyRole("권한명1", "권한명2") : 여러 개의 권한 허용
        httpSecurity.authorizeHttpRequests(auth -> auth
                // admin 관련 요청은 "ADMIN"권한일 때만 사용가능
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/user/info").hasAnyRole("USER", "ADMIN")
                // 모든 권한허용은 항상 최하단에 정의(정의되지않은 권한에 대한 허용)
                .requestMatchers("/**").permitAll()
        );
        // 1-2. 차단된 POST/PUT(csrf 공격) 차단 해제
        // httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers("csrf를 제외할 경로"));
        httpSecurity.csrf(csrf -> csrf.disable());  // csrf 비활성화(개발단계에서 권장) -> POST, PUT 허용

        // 시큐리티 내부에서 사용되는 (세션 기반)토큰 : UsernamePasswordAuthenticationToken
        // JwtService에서는 (쿠키 기반) 토큰 구현 -> 시큐리티가 제공하는 토큰 사용안함 설정
        // 1-3. 시큐리티 세션 기반 토큰 끄기 -> 자체적인 토큰을 사용하기 위해서
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // 1-4. UsernamePasswordAuthenticationToken을 내가 만든 토큰으로 대체
        // httpSecurity.addFilterBefore(내가만든토큰객체필터, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        // 1-. 커스텀 완료된 객체 반환
        return httpSecurity.build();
    } // func end
} // class end
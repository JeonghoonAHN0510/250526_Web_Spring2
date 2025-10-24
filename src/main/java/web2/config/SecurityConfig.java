package web2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
    private final Oauth2SuccessHandler oauth2SuccessHandler;

    // [0] username : user , password : 콘솔의 password 복사하여 붙여넣기
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
                .requestMatchers("/api/user/info").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
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
        // ========================= 구글 로그인 연동 ================================
        // 1-5. Oauth2 로그인 필터 사용 설정
        // httpSecurity.oauth2Login(매개변수 -> 매개변수.successHandler(로그인성공시 특정클래스이동));
        httpSecurity.oauth2Login(oauth -> oauth
                .loginPage("/oauth2/authorization/google")      // 현재 로그인페이지가 아닌 타사 로그인페이지로 이동
                .successHandler(oauth2SuccessHandler)             // 타사 로그인페이지에서 로그인 성공 시 반환되는 클래스 정의
        );
        // 1-6. CORS 정책 필터 사용 설정 : 자체적인 CorsConfig 설정
        httpSecurity.cors(Customizer.withDefaults());
        // 1-7. 커스텀 완료된 객체 반환
        return httpSecurity.build();
    } // func end
} // class end
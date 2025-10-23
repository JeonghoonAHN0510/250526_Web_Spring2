package web2.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web2.service.JwtService;
import web2.service.UserService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;
    private final UserService userService;
    // 타사(oauth2 : 구글/카카오/네이버 등) 로그인 성공 이후 로직 커스텀
    // 로그인 실패 커스텀은 하지 않는다.
    // ** OAuth2 관련 라이브러리 설치 : implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
    // ** application.properties에

    // [1] onAuthenticationSuccess 오버라이딩하기
    // 1. 어느 곳의 로그인 성공인지 확인
    // 2. 동의항목(정보) 가져오기 : 개인정보
    // 3. 내 서버와 타사 서버 통합 로그인 : 토큰/쿠키 || 세션
    // 4. 내 서버와 타사 서버 통합 DB : 최초 로그인이면 DB 저장, 아니면 DB 처리 X
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 1-1. 로그인 성공한 회원의 타사발급 토큰 확인
        System.out.println("authentication = " + authentication);       // 인증정보 : 토큰, 개인정보 등
        OAuth2AuthenticationToken oAuthToken = (OAuth2AuthenticationToken) authentication;
        System.out.println("oAuthToken = " + oAuthToken);
        // 1-2. 로그인 성공한 회원의 동의항목 확인
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("oAuth2User = " + oAuth2User);
        // 1-3. 회사명 식별하기 : google | kakao
        String provider = oAuthToken.getAuthorizedClientRegistrationId();
        System.out.println("provider = " + provider);
        String uid = null; String name = null;
        // 2-1. 회사가 구글일 때
        if (provider.equals("google")){
            // oAuth2User.getAttribute("동의항목명");
            uid = oAuth2User.getAttribute("email");
            name = oAuth2User.getAttribute("name");
        } else if (provider.equals("kakao")){
            // 2-2. 회사가 카카오일 때
        } // if end
        // 3. OAuth2 정보를 DB에 저장
        userService.oAuth2UserSignup(uid, name);
        // 4. 우리의 로그인 방식과 통합, 권한은 USER로 정의
        Cookie cookie = new Cookie("loginUser", jwtService.generateToken2(uid, "USER"));
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        System.out.println("cookie = " + cookie);
        // 5. 로그인 성공 시, 이동할 페이지 설정 : 프론트엔드 루트
        // response.sendRedirect("http://localhost:5173/");
        response.sendRedirect("/");
    } // func end
} // class end
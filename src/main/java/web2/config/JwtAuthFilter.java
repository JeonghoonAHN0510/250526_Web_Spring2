package web2.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import web2.service.JwtService;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    // 내가 만든 토큰인증 방식(JwtService)을 스프링 시큐리티 방식(UsernamePasswordAuthenticationToken)와 통합하기
    // [1] 내가 만든 토큰인증 방식
    private final JwtService jwtService;

    // [2] 기존 스프링 시큐리티 방식의 필터 커스텀 -> OncePerRequestFilter로부터 상속받기
    @Override   // 상속받은 함수 재정의
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 2-1. 세션이 아닌 쿠키 방식의 토큰 추출
        String token = null;
        if (request.getCookies() != null){                  // 쿠키가 존재하면
            for (Cookie cookie : request.getCookies()){     // 모든 쿠키를 순회하면서
                if (cookie.getName().equals("loginUser")){  // "loginUser" 쿠키가 존재하면
                    token = cookie.getValue();              // 토큰에 쿠키값 저장하기
                    System.out.println("cookie = " + cookie);
                    System.out.println("token = " + token);
                } // if end
            } // for end
        } // if end
        // 2-2. UsernamePasswordAuthenticationToken를 재정의
        if (token != null && jwtService.validateToken2(token)){ // 토큰이 존재하면서, 토큰이 유효하면
            String uid = jwtService.getUid(token);              // 아이디 추출하기
            String urole = jwtService.getUrole(token);          // 권한 추출하기
            System.out.println("uid = " + uid);
            System.out.println("urole = " + urole);
            // 2-3. 스프링 시큐리티가 원하는 서명 만들기
            UsernamePasswordAuthenticationToken newAuthenticationToken =
                    // new UsernamePasswordAuthenticationToken("아이디", "비밀번호", "권한");
                    // 권한 = List.of(new SimpleGrantedAuthority("ROLE_권한명1"), new SimpleGrantedAuthority("ROLE_권한명2")));
                    new UsernamePasswordAuthenticationToken(uid, null, List.of(new SimpleGrantedAuthority("ROLE_" + urole)));
            // 2-4. 스프링 시큐리티가 사용할 수 있게 토큰 저장 -> SecurityContext
            SecurityContextHolder.getContext().setAuthentication(newAuthenticationToken);
            System.out.println("newAuthenticationToken = " + newAuthenticationToken);
        } // if end
        // 2-5. 다른 필터에서 해당하는 토큰필터를 호출할 수 있도록 허용
        filterChain.doFilter(request, response);
    } // func end
} // class end
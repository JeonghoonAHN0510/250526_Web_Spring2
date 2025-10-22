package web2.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web2.model.dto.UserDto;
import web2.service.JwtService;
import web2.service.UserService;

@RestController
@RequestMapping("/api/user")     // 공통URL 정의
@RequiredArgsConstructor            // final 필드에 대한 자동 생성자 주입
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    // [1] 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto){
        // 1-1. 입력받은 값을 Service에 전달하여 회원가입 진행
        int result = userService.signup(userDto);
        // 1-2. 최종적으로 결과 반환
        return ResponseEntity.ok(result);   // .ok : 200 성공 의미
    } // func end

//    // [2-1] 로그인 + 세션(자바웹서버(톰캣)의 임시 저장소)
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpSession session){
//        // 2-1. 입력받은 값을 Service에 전달하여 로그인 진행
//        UserDto result = userService.login(userDto);
//        // 2-2. 로그인을 성공했다면, 성공한 User의 ID를 세션에 저장
//        if (result != null) session.setAttribute("loginUser", result.getUid());
//        // 2-3. 최종적으로 결과 반환
//        return ResponseEntity.ok(result);
//    } // func end

    // [2-2] 로그인 + 쿠키(클라이언트 브라우저의 임시 저장소) + 토큰(아이디 + 권한)
    // 장점 : 서버에 부담이 줄어든다.
    // 단점 : 쿠키에 저장하면 세션보다 비교적 위험하다. -> 안전장치가 필요
    // 사용처 : 주로 사용자들의 설정값 저장
    // 클라이언트에 저장하는 임시 저장소이므로 서버가 종료되도 상태가 유지된다.
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpServletResponse response){
        // 2-1. 입력받은 값을 Service에 전달하여 로그인 진행
        UserDto result = userService.login(userDto);
        // 2-2. 로그인을 성공했다면, 성공한 User의 ID를 쿠키에 저장
        if (result != null){
            // Cookie cookie = new Cookie();
            // Cookie cookie = new Cookie("loginUser", result.getUid());
            // 2-3. 회원정보를 토큰으로 생성하여 쿠키에 저장하기
            Cookie cookie = new Cookie("loginUser", jwtService.generateToken2(userDto.getUid(), userDto.getUrole()));
            // 2-4. 쿠키 노출 및 탈취 방지 - 안전장치 설정
            cookie.setHttpOnly(true);       // 무조건 http에서만 사용 -> JS에서 접근 불가능
            cookie.setSecure(false);        // http이용하여 탈취하더라도 암호화 -> https에서만 true 사용 가능
            cookie.setPath("/");            // 쿠키에 접근할 수 있는 경로 지정
            cookie.setMaxAge(3600);         // 쿠키의 유효기간 설정(초 단위)
            // 2-5. 생성한 쿠키를 클라이언트에게 반환
            response.addCookie(cookie);
        } // if end
        // 2-6. 최종적으로 결과 반환
        return ResponseEntity.ok(result);
    } // func end

    // [3] (쿠키를 활용한) 현재 로그인된 정보 호출 (+ 마이페이지)
    @GetMapping("/getUser")
    public ResponseEntity<?> getUserByUid(HttpServletRequest request){
        // 3-1. 현재 클라이언트(브라우저)에 저장된 모든 쿠키 가져오기
        Cookie[] cookies = request.getCookies();
        // 3-2. 모든 쿠키를 순회하며 특정한 쿠키 찾기
        if (cookies != null){                                   // cookies가 존재하면
            for (Cookie cookie : cookies){                      // 모든 cookie를 순회하며
                if (cookie.getName().equals("loginUser")){      // cookie의 이름이 "loginUser"이면
                    // 3-3. 쿠키에 저장된 토큰 반환
                    String token = cookie.getValue();
                    // 3-4. 토큰 검증하기
                    boolean validateToken = jwtService.validateToken2(token);
                    // 3-5. 토큰이 유효하면
                    if (validateToken){
                        // 3-6. 토큰에 저장된 아이디 추출하기
                        String uid = jwtService.getUid(token);
                        // 3-7. 아이디로 내정보 조회하기
                        return ResponseEntity.ok(userService.getUserByUid(uid));
                    } else {
                        // 3-8. 토큰이 유효하지 않으면, null 반환
                        return ResponseEntity.ok(null);
                    } // if end
                } // if end
            } // for end
        } // if end
        // 3-9. 쿠키가 존재하지 않으면, null 반환
        return ResponseEntity.ok(null);
    } // func end

    // [4] (쿠키 기반) 로그아웃 - 컨트롤러만 존재
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response){
        // 4-1. 삭제할 쿠키명을 null로 변경
        Cookie cookie = new Cookie("loginUser", null);
        // 4-2. 쿠키에 대한 설정 진행
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0);        // 0초라서 즉시 삭제
        // 4-3. 생성한 쿠키를 클라이언트에게 반환
        response.addCookie(cookie);
        // 4-4. 모든 로직 진행 후, 최종적으로 true 반환
        return ResponseEntity.ok(true);
    } // func end
} // class end
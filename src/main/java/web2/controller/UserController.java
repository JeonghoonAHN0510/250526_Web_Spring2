package web2.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web2.model.dto.UserDto;
import web2.service.UserService;

@RestController
@RequestMapping("/api/user")     // 공통URL 정의
@RequiredArgsConstructor            // final 필드에 대한 자동 생성자 주입
public class UserController {
    private final UserService userService;

    // 1. 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto){
        // 1-1. 입력받은 값을 Service에 전달하여 회원가입 진행
        int result = userService.signup(userDto);
        // 1-2. 최종적으로 결과 반환
        return ResponseEntity.ok(result);   // .ok : 200 성공 의미
    } // func end

    // 2. 로그인 + 세션
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpSession session){
        // 2-1. 입력받은 값을 Service에 전달하여 로그인 진행
        UserDto result = userService.login(userDto);
        // 2-2. 로그인을 성공했다면, 성공한 User의 ID를 세션에 저장
        if (result != null) session.setAttribute("loginUser", result.getUid());
        // 2-3. 최종적으로 결과 반환
        return ResponseEntity.ok(result);
    } // func end
} // class end
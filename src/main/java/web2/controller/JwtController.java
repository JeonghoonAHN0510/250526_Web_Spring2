package web2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web2.service.JwtService;

@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
public class JwtController {
    private final JwtService jwtService;

    // [1] 토큰 생성
    @GetMapping("/generate")
    public ResponseEntity<?> generateToken(@RequestParam String data){
        return ResponseEntity.ok(jwtService.generateToken1(data));
    } // func end

    // [2] 토큰 검증
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam String token){
        return ResponseEntity.ok(jwtService.validateToken1(token));
    } // func end

    // [3] 토큰 추출
    @GetMapping("/get")
    public ResponseEntity<?> getValue(@RequestParam String token){
        return ResponseEntity.ok(jwtService.getClaims1(token));
    } // func end

} // class end
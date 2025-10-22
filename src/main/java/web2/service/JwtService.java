package web2.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    // 0-1. 비밀키 생성 -> 직접 생성한 임의 키
    private final String secret = "123456789123456789123456789123456789";
    // 0-2. 비밀키에 SHA-256 알고리즘 적용하여 계산식 생성
    private final Key secretKey = Keys.hmacShaKeyFor(secret.getBytes());

    // [1] 토큰 생성
    public String generateToken(String data){
        // 1-1. 토큰 생성 : builder 패턴을 이용
        // builder 패턴 : 생성자 대신에 함수형 객체생성 방식
        String token = Jwts.builder()
                // .claim(key, value) : 토큰에 넣을 데이터 대입
                .claim("key", data)
                // .setIssuedAt(토큰발급시간) | new Date() : 시스템 시간 반환
                .setIssuedAt(new Date())
                // .setExpiration(토큰만료시간)
                // new Date(System.currentTimeMillis() + 1000 * 초) : 현재 시간을 밀리초로 반환
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 30))
                // .signWith(비밀키, SignatureAlgorithm.HS256) : 토큰에 알고리즘을 이용한 계산식으로 서명하기
                .signWith(secretKey, SignatureAlgorithm.HS256)
                // .compact() : 최종 JWT 문자열 형태로 생성
                .compact();
        System.out.println("token = " + token);
        return token;
    } // func end

    // [2] 토큰 검증
    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    // .setSigningKey(비밀키) : 서명 검증을 위한 비밀키 대입
                    .setSigningKey(secretKey)
                    .build()
                    // parseClaimsJws(토큰) : 검증할 토큰 대입
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            System.out.println("token이 존재하지 않습니다,");
            return false;
        } // try-catch end
        return true;
    } // func end

    // [3] 토큰 payload(내용물) claim 값 추출
    public String getValue(String token){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    // .getBody() : 검증 후, 내용물 가져오기
                    .getBody();
            System.out.println("claims = " + claims);
            return claims.get("key").toString();
        } catch (ExpiredJwtException e) {
            return null;
        } // try-catch end
    } // func end
} // class end

/*
[ JWT(JSON WEB TOKEN) ]
- 웹에서 사용하는 자바스크립트 기반의 특정한 데이터를 대신하는 증표
    -> 특정한 데이터를 직접 보여주지않고, 증표를 대신 보여주는 구조
    -> 웹에서 데이터 숨기기 -> 보안을 위해서 사용
- 데이터 간의 서로 다른 토큰을 생성하기 위해 복잡한 계산식 추가
    -> SHA-256 알고리즘을 사용하여 비밀키 생성

[ 대칭키 VS 비대칭키 ]
*/
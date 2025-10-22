package web2.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

/*
[ JWT(JSON WEB TOKEN) ]
- 웹에서 사용하는 자바스크립트 기반의 특정한 데이터를 대신하는 증표
    -> 특정한 데이터를 직접 보여주지않고, 증표를 대신 보여주는 구조
    -> 웹에서 데이터 숨기기 -> 보안을 위해서 사용
- 데이터 간의 서로 다른 토큰을 생성하기 위해 복잡한 계산식 추가
    -> SHA-256 알고리즘을 사용하여 비밀키 생성

[ 대칭키 VS 비대칭키 ]
*/

@Service
@RequiredArgsConstructor
public class JwtService {
    // ============================ 토큰 기초 수업 ============================
    // 0-1. 비밀키 생성 -> 직접 생성한 임의 키
    private final String secret1 = "123456789123456789123456789123456789";
    // 0-2. 비밀키에 SHA-256 알고리즘 적용하여 계산식 생성
    private final Key secretKey1 = Keys.hmacShaKeyFor(secret1.getBytes());

    // [1] 토큰 생성
    public String generateToken1(String data){
        // 1-1. 토큰 생성 : builder 패턴을 이용
        // builder 패턴 : 생성자 대신에 함수형 객체생성 방식
        String token = Jwts.builder()
                // .claim(key, value) : 토큰에 넣을 데이터 대입
                .claim("key", data)
                // .setIssuedAt(토큰발급시간) | new Date() : 시스템 날짜/시간 반환
                .setIssuedAt(new Date())
                // .setExpiration(토큰만료시간)
                // new Date(System.currentTimeMillis() + 1000 * 초) : 현재 시간을 밀리초로 반환
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 30))
                // .signWith(비밀키, SignatureAlgorithm.HS256) : 토큰에 알고리즘을 이용한 계산식으로 서명하기
                .signWith(secretKey1, SignatureAlgorithm.HS256)
                // .compact() : 최종 JWT 문자열 형태로 생성
                .compact();
        System.out.println("token = " + token);
        return token;
    } // func end

    // [2] 토큰 검증
    public boolean validateToken1(String token){
        try {
            Jwts.parser()
                    // .setSigningKey(비밀키) : 서명 검증을 위한 비밀키 대입
                    .setSigningKey(secretKey1)
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
    public String getClaims1(String token){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey1)
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

    // ============================ 토큰 로그인 연동 ============================
    // [1] 비밀키 정의 - 임의의 32글자 이상의 문자열로 구성
    private final String secret2 = "123456789123456789123456789123456789";
    // [2] 정의한 비밀키에 SHA-256 알고리즘 적용
    private final Key secretKey2 = Keys.hmacShaKeyFor(secret2.getBytes(StandardCharsets.UTF_8));
    // [3] 토큰 생성 : 회원로그인정보 전용 토큰(아이디, 권한)
    public String generateToken2(String uid, String urole){
        // 3-1. 토큰객체 생성 빌더 시작
        String token = Jwts.builder()
                // 3-2. uid라는 key에 회원아이디 저장
                .claim("uid", uid)
                // 3-3. urole이라는 key에 회원권한 저장
                .claim("urole", urole)
                // 3-4. 발급시간에 현재 시스템 날짜/시간 저장
                .setIssuedAt(new Date())
                // 3-5. 만료시간에 밀리초 단위로 저장 - 1000 * 60 * 60 : 1시간
                .setExpiration( new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                // 3-6. HS256 알고리즘을 이용하여 서명
                .signWith(secretKey2, SignatureAlgorithm.HS256)
                // 3-7. 토큰객체 생성 빌더 종료
                .compact();
        System.out.println("token = " + token);
        // 3-8. 최종적으로 토큰 반환
        return token;
    } // func end

    // [4] 토큰 검증
    public boolean validateToken2(String token){
        try {
            Jwts.parser()
                    // 4-1. 서명검증을 위한 비밀키 대입
                    .setSigningKey(secretKey2)
                    .build()
                    // 4-2. 검증할 토큰을 대입하여 검증 실행
                    .parseClaimsJws(token);
            // 4-3. 예외가 발생하지 않으면, 검증 성공
            return true;
        } catch (JwtException e) {
            // 4-4. 예외가 발생하면, 검증 실패
            // -> 유효기간이 지나거나 존재하지않는 토큰 등
            return false;
        } // try-catch end
    } // func end

    // [5] 토큰 추출
    public Claims getClaims2(String token){
        return Jwts.parser()
                // 5-1. 서명검증을 위한 비밀키 대입
                .setSigningKey(secretKey2)
                .build()
                // 5-2. 검증할 토큰을 대입하여 검증 실행
                .parseClaimsJws(token)
                // 5-3. 검증에 성공한 토큰의 Claims 반환
                .getBody();
    } // func end

    // [6] Claims의 특정값 추출
    public String getUid(String token){
        return getClaims2(token).get("uid").toString();
    } // func end
    public String getUrole(String token){
        return getClaims2(token).get("urole").toString();
    } // func end
} // class end
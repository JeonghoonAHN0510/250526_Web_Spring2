package web2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web2.model.dto.UserDto;
import web2.model.mapper.UserMapper;

@Service
@RequiredArgsConstructor    // final 필드에 대한 자동 생성자 주입
public class UserService {
    private final UserMapper userMapper;
    // 1-1. BCrypt 라이브러리 객체 생성
    private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    // [1] 회원가입
    public int signup(UserDto userDto){
        // 1-2. BCrypt를 이용한 비밀번호 암호화
        // String 암호문 = bCrypt.encode(평문);
        userDto.setUpwd(bCrypt.encode(userDto.getUpwd()));
        // 1-3. 암호문이 포함된 Dto를 Mapper에게 전달하여 회원가입 진행
        userMapper.signup(userDto);
        // 회원가입 성공 시 생성된 회원번호, 실패 시 0 반환
        return userDto.getUno();
    } // func end

    // [2] 로그인 : 암호문을 복호화하여 평문을 비교하는 방식이 아닌, 암호문을 비교하는 방식
    // BCrypt는 복호화가 불가능하기에 암호문을 비교하는 방식을 사용
    public UserDto login(UserDto inputUserDto){
        // 2-1. 로그인 요청된 아이디가 유효한지 확인
        UserDto DBUserDto = userMapper.login(inputUserDto.getUid());
        // 2-2. 유효하지 않으면, null 반환
        if (DBUserDto == null) return null;
        // 2-3. 유효하면, 입력받은 비밀번호와 암호화된 비밀번호 비교
        // 암호문 비교 방식 : bCrypt.matches(평문, 암호문);
        boolean result = bCrypt.matches(inputUserDto.getUpwd(), DBUserDto.getUpwd());
        // 2-4. 결과 반환
        if (result){                    // 비밀번호가 일치하면
            DBUserDto.setUpwd(null);    // 안전하게 암호문을 없애고
            return DBUserDto;           // 결과 반환
        } else {                        // 비밀번호가 일치하지 않으면
            return null;                // null 반환
        } // if end
    } // func end

    // [3] 현재 로그인된 정보 호출 (+ 마이페이지)
    public UserDto getUserByUid(String uid){
        return userMapper.getUserByUid(uid);
    } // func end

} // class end

/*

- 평문 : 본래의 데이터
- 암호문 : 암호화된 데이터
- 암호화 : 평문을 암호문으로 변경하는 것
- 복호화 : 암호문을 평문으로 변경하는 것

- 암호화 알고리즘 : BCrypt, 블록체인 등

- BCrypt 특징 : 복호화 불가능, 동일한 데이터여도 서로 다른 암호문 발행

*/
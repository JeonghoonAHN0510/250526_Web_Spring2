package web2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web2.model.dto.UserDto;
import web2.model.mapper.UserMapper;

@Service
@RequiredArgsConstructor    // final 필드에 대한 자동 생성자 주입
public class UserService {
    private final UserMapper userMapper;

    // 1. 회원가입
    public int signup(UserDto userDto){
        userMapper.signup(userDto);
        // 회원가입 성공 시 생성된 회원번호, 실패 시 0 반환
        return userDto.getUno();
    } // func end

    // 2. 로그인
    public UserDto login(UserDto userDto){
        // 1. SQL 조회결과 반환
        return userMapper.login(userDto);
    } // func end
} // class end
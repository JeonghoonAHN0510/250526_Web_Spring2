package web2.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import web2.model.dto.UserDto;

@Mapper
public interface UserMapper {

    // [1] 회원가입 - PK 반환
    @Insert("INSERT INTO users (uid, upwd, uname, uphone, urole) VALUES (#{uid}, #{upwd}, #{uname}, #{uphone}, #{urole})")
    @Options(useGeneratedKeys = true, keyProperty = "uno")  // INSERT 성공 시, 매개변수에 생성된 PK값 주입
    int signup(UserDto userDto);

    // [2-1] 로그인 - 평문 비교 방식
    // @Select("SELECT * FROM users WHERE uid = #{uid} AND upwd = #{upwd}")
    // UserDto login(UserDto userDto);

    // [2-2] 로그인 - 암호문 비교 방식
    @Select("SELECT * FROM users WHERE uid = #{uid}")
    UserDto login(String uid);

    // [3] 현재 로그인된 정보 호출 (+ 마이페이지)
    // 안전하게 패스워드를 제외한 정보 호출
    @Select("SELECT uno, uid, uname, uphone, urole, udate FROM users WHERE uid = #{uid}")
    UserDto getUserByUid(String uid);

} // interface end
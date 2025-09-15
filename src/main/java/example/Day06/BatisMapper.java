package example.Day06;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper // 해당 인터페이스를 스프링 컨테이너(MyBatis)에 등록
// Mapper Interface는 Dao의 역할을 대신한다.
public interface BatisMapper {
    // 1. 학생 등록


    // 2. 전체학생 조회
    @Select("select * from student")
    List< StudentDto > findAll();

    // 3. 개별학생 조회


    // 4. 개별학생 삭제


    // 5. 개별학생 수정


} // interface end
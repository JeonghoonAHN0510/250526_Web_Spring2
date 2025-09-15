package example.Day06;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper // 해당 인터페이스를 스프링 컨테이너(MyBatis)에 등록
// Mapper Interface는 Dao의 역할을 대신한다.
public interface BatisMapper {
    // 1. 학생 등록
    // dao : insert into student( name, kor, math ) values ( ?, ?, ? )
    // MyBatis : insert into student( name, kor, math ) values ( #{매개변수1}, #{매개변수2}, #{매개변수3} )
    @Insert("insert into student( name, kor, math ) values ( #{name}, #{kor}, #{math} )")
    int save( StudentDto studentDto );
    // int : 1 성공, 0 실패

    // 2. 전체학생 조회
    @Select("select * from student")
    List< StudentDto > findAll();

    // 3. 개별학생 조회 -> Dto로 가능하지만, Map 테스트
    @Select("select * from student where sno = #{sno}")
    Map<String, Object> findBySno( int sno );

    // 4. 개별학생 삭제
    @Delete("delete from student where sno = ${sno}")
    int deleteBySno( int sno );

    // 5. 개별학생 수정
    @Update("update student set kor = #{kor}, math = #{math} where sno = #{sno}")
    int updateBySno( StudentDto studentDto );

} // interface end
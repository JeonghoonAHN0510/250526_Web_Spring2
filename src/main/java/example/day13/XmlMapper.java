package example.day13;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface XmlMapper {
    // MyBatis에서 SQL을 매핑하는 방법
    // 방법1. 추상메소드에 @Insert("SQL") 추가, 주로 간단한 CRUD
        // @Insert("INSERT INTO student(name, kor, math)\n VALUES (#{name}, #{kor}, #{math});")
        // @Options(useGeneratedKeys = true, keyProperty = "sno")   // auto_increment 반환
    // 방법2. 추상메소드를 매핑하는 XML 파일에서 SQL 작성, 주로 복잡한 SQL(동적 쿼리)

    // 1. 등록
    int save(StudentDto studentDto);

    // 2. 전체조회
    List<StudentDto> getAll();

    // 3. 개별조회
    StudentDto getBySno(int sno);

    // 4. 개별삭제
    int deleteBySno(int sno);

    // 5. 개별수정
    int updateBySno(StudentDto studentDto);
} // interface end
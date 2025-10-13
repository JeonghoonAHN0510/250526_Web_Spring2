package example.day13;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface XmlMapper {
    // MyBatis에서 SQL을 매핑하는 방법
    // 방법1. 추상메소드에 @Insert("SQL") 추가, 간단한 CRUD
    // 방법2. 추상메소드를 매핑하는 XML 파일에서 SQL 작성, 복잡한 SQL

    // 1. 등록
    int save(StudentDto studentDto);

    // 2. 전체조회
    List<StudentDto> getAll();
} // interface end
package example.day13;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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

    //================== 동적 쿼리(if, forEach) ==================
    // 일반 SQL을 프로그래밍 SQL로 변경하는 것
    // 방법1 : @어노테이션("""<script> XML 형식의 SQL </script>""")
    // """ """ : java15 이상부터 지원, + 연산자처럼 문자열 연결
    // 조건에 따른 SQL이 오류가 발생하지 않게, where 1 = 1과 같은 조건문을 넣거나 <where> 마크업 사용
    // <if test="조건문"> 참일 경우 SQL </if>

    // 6. 특정한 국어점수 이상인 학생 조회
    @Select("""
            <script>
                SELECT * FROM student WHERE 1 = 1
                <if test="kor != null">
                    and kor >= #{kor}
                </if>
            </script>
            """)
    StudentDto query1(int kor);

    // 방법2 : XML에서 SQL 작성
    StudentDto query2(int kor);

    // 7. 이름 또는 수학점수로 학생 조회
    StudentDto query3(String name, int math);

    // 8. 다수 학생 등록
    int saveAll(List<StudentDto> studentDtos);
} // interface end
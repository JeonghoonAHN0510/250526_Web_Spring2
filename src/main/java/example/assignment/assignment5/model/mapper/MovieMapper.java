package example.assignment.assignment5.model.mapper;

import example.assignment.assignment5.model.dto.MovieDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieMapper {
    /**
     * 제목, 감독, 장르, 간단한 소개, (삭제를 위한) 비밀번호를 입력받아, movie table에 저장한다.
     * <p>
     * 영화 등록시 사용되는 메소드
     * @param movieDto
     * @return boolean
     */
    @Insert("insert into movie ( mtitle, mdirector, mgenre, mcomment, mpwd ) values ( #{mtitle}, #{mdirector}, #{mgenre}, #{mcomment}, #{mpwd} )")
    boolean postMovie( MovieDto movieDto );

    // [2] 영화 삭제

    // [3] 영화 전체조회

} // interface end
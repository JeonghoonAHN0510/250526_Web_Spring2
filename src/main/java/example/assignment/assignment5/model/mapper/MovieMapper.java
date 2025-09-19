package example.assignment.assignment5.model.mapper;

import example.assignment.assignment5.model.dto.MovieDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * PK 번호, 비밀번호를 입력받아, 해당 영화를 삭제한다.
     * @param movieDto
     * @return boolean
     */
    @Delete("delete from movie where mno = #{mno} and mpwd = #{mpwd}")
    boolean deleteMovie( MovieDto movieDto );

    /**
     * 등록된 추천 영화목록을 조회한다.
     * @return List, MovieDto
     */
    @Select("select * from movie")
    List<MovieDto> getMovies();

} // interface end
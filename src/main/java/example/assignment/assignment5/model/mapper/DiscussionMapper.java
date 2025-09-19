package example.assignment.assignment5.model.mapper;

import example.assignment.assignment5.model.dto.DiscussionDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DiscussionMapper {
    /**
     * 영화번호, 토론내용, 비밀번호를 입력받아, Discussion Table에 저장한다.
     * @param discussionDto
     * @return boolean
     */
    @Insert("insert into discussion( mno, dcontent, dpwd ) values ( #{mno}, #{dcontent}, #{dpwd})")
    boolean postDiscussion( DiscussionDto discussionDto );

    /**
     * 토론번호, 비밀번호를 입력받아, 해당 토론을 삭제한다.
     * @param discussionDto
     * @return boolean
     */
    @Delete("delete from discussion where dno = #{dno} and dpwd = #{dpwd}")
    boolean deleteDiscussion( DiscussionDto discussionDto );

    /**
     * 영화번호를 입력받아, 해당 영화의 모든 토론을 조회한다.
     * @param mno
     * @return List, DiscussionDto
     */
    @Select("select * from discussion where mno = #{mno}")
    List<DiscussionDto> getDiscussionsByMno( int mno );
} // interface end
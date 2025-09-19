package example.assignment.assignment5.model.mapper;

import example.assignment.assignment5.model.dto.DiscussionDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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

    // [3] 영화별 토론 전체 조회

} // interface end
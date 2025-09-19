package example.assignment.assignment5.service;

import example.assignment.assignment5.model.dto.DiscussionDto;
import example.assignment.assignment5.model.mapper.DiscussionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscussionService {
    private final DiscussionMapper discussionMapper;

    /**
     * 영화번호, 토론내용, 비밀번호를 입력받아, Discussion Table에 저장한다.
     * @param discussionDto
     * @return boolean
     */
    public boolean postDiscussion( DiscussionDto discussionDto ){
        return discussionMapper.postDiscussion( discussionDto );
    } // func end

    /**
     * 토론번호, 비밀번호를 입력받아, 해당 토론을 삭제한다.
     * @param discussionDto
     * @return boolean
     */
    public boolean deleteDiscussion( DiscussionDto discussionDto ){
        return discussionMapper.deleteDiscussion( discussionDto );
    } // func end

    /**
     * 영화번호를 입력받아, 해당 영화의 모든 토론을 조회한다.
     * @param mno
     * @return List, DiscussionDto
     */
    public List<DiscussionDto> getDiscussionsByMno( int mno ){
        return discussionMapper.getDiscussionsByMno( mno );
    } // func end
} // class end
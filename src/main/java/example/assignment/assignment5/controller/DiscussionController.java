package example.assignment.assignment5.controller;

import example.assignment.assignment5.model.dto.DiscussionDto;
import example.assignment.assignment5.service.DiscussionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/discussion")
@CrossOrigin( value = "http://localhost:5173")  // React 서버와 CORS 통신 허용
public class DiscussionController {
    private final DiscussionService discussionService;

    /**
     * 영화번호, 토론내용, 비밀번호를 입력받아, Discussion Table에 저장한다.
     * <p>
     * method : POST, URL : /discussion/post
     * @param discussionDto
     * @return boolean
     */
    @PostMapping("/post")
    public ResponseEntity<Boolean> postDiscussion( @RequestBody DiscussionDto discussionDto ){
        System.out.println("DiscussionController.postDiscussion");
        // 1. Service로부터 결과받기
        boolean result = discussionService.postDiscussion( discussionDto );
        // 2. 결과 반환하기
        if ( result ){
            return ResponseEntity.status(200).body( true );
        } else {
            return ResponseEntity.status(400).body( false );
        } // if end
    } // func end

    /**
     * 토론번호, 비밀번호를 입력받아, 해당 토론을 삭제한다.
     * <p>
     * method : PUT, URL : /discussion/delete
     * @param discussionDto
     * @return boolean
     */
    @PutMapping("/delete")
    public ResponseEntity<Boolean> deleteDiscussion( @RequestBody DiscussionDto discussionDto ){
        System.out.println("DiscussionController.deleteDiscussion");
        // 1. Service로부터 결과받기
        boolean result = discussionService.deleteDiscussion( discussionDto );
        // 2. 결과 반환하기
        if ( result ){
            return ResponseEntity.status(200).body( true );
        } else {
            return ResponseEntity.status(400).body( false );
        } // if end
    } // func end

    /**
     * 영화번호를 입력받아, 해당 영화의 모든 토론을 조회한다.
     * <p>
     * method : GET, URL : /discussion/getByMno
     * @param mno
     * @return List, DiscussionDto
     */
    @GetMapping("/getByMno")
    public ResponseEntity<List<DiscussionDto>> getDiscussionByMno( @RequestParam int mno ){
        System.out.println("DiscussionController.getDiscussionByMno");
        // 1. Service로부터 결과받기
        List<DiscussionDto> result = discussionService.getDiscussionsByMno( mno );
        // 2. 결과 반환하기
        if ( result == null ){
            return ResponseEntity.status(400).body( null );
        } else {
            return ResponseEntity.status(200).body( result );
        } // if end
    } // func end
} // class end
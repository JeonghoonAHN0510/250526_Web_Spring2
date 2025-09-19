package example.assignment.assignment5.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DiscussionDto {
    // 기본적인 정보
    private int dno;            // PK 번호
    private int mno;            // 영화번호
    private String dcontent;    // 토론(댓글) 내용
    private String dpwd;        // 토론(댓글) 비밀번호
} // class end
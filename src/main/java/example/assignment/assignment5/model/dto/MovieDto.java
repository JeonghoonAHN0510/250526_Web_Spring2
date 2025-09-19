package example.assignment.assignment5.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MovieDto {
    // 기본적인 정보
    private int mno;            // PK 번호
    private String mtitle;      // 제목
    private String mdirector;   // 감독
    private String mgenre;      // 장르
    private String mcomment;    // 간단한 소개
    private String mpwd;        // 비밀번호
} // class end
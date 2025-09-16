package example.Day07.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BoardDto {
    // 기본적인 정보
    private int bno;
    private String bcontent;
    private String bwriter;
} // class end
package example.day13;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudentDto {
    // 기본적인 정보
    private int sno;
    private String name;
    private int kor;
    private int math;
} // class end
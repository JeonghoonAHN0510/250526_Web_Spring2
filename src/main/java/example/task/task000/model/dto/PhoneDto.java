package example.task.task000.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PhoneDto {
    // 기본적인 정보
    private int mno;
    private String name;
    private String phone;
    private int age;
} // class end
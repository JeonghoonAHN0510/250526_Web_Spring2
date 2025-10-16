package example.task.task06.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RentalDto {
    // 기본적인 정보
    private int id;
    private int book_id;
    private String member;
    private String rent_date;
    private String return_date;
} // class end
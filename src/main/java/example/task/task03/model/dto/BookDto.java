package example.task.task03.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookDto {
    // 기본적인 정보
    private int bookId;
    private String title;
    private int stock;
    // 부가적인 정보
    private String member;
} // class end
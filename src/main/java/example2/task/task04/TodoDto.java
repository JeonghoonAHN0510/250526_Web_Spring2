package example2.task.task04;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    private int id;
    private String title;
    private String content;
    private String create_date;
    private String update_date;

    public TodoEntity toEntity(){
        return TodoEntity.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .build();
    } // func end
} // class end
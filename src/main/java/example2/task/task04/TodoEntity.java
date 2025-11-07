package example2.task.task04;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;

    public TodoDto toDto(){
        return TodoDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .create_date(this.getCreateTime().toString())
                .update_date(this.getUpdateTime().toString())
                .build();
    } // func end
} // class end
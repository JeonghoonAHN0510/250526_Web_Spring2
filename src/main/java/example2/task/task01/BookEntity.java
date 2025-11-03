package example2.task.task01;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {
    @Id
    private int bid;
    private String btitle;
    private String bauthor;
    private String bpublisher;
} // class end
package example2.task.task03;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task_student")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    private String studentName;
    // 양방향 연결
    @OneToMany(mappedBy = "studentEntity")
    @ToString.Exclude
    @Builder.Default
    private List<EnrollEntity> enrollEntityList = new ArrayList<>();
} // class end
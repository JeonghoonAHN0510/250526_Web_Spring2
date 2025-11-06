package example2.task.task03;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task_course")
@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String courseName;
    // 양방향 연결
    @OneToMany(mappedBy = "courseEntity")
    @ToString.Exclude
    @Builder.Default
    private List<EnrollEntity> enrollEntityList = new ArrayList<>();
} // class end
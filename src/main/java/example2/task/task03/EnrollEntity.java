package example2.task.task03;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task_enroll")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollId;
    private String status;
    // 단방향 연결
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "studentId")     // FK 필드명 설정
    private StudentEntity studentEntity;
    // 단방향 연결
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "courseId")  // FK 필드명 설정
    private CourseEntity courseEntity;
} // class end
package example2.day106;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 해당 클래스가 Entity임을 주입
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamEntity {
    // DTO처럼 데이터베이스에서 사용될 테이블과 속성 일치
    @Id
    private int col1;
    private String col2;
    private double col3;
} // class end
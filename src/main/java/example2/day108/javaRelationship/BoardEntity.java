package example2.day108.javaRelationship;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "eboard")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int bno;
    private String btitle;
    private String bcontent;
    // 단방향 연결 : 하위 엔티티가 상위 엔티티 참조관계
    @ManyToOne(     // M:1, 다수 FK가 하나의 PK에게
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "cno")   // FK 필드명 설정
    private CategoryEntity categoryEntity;
} // class end
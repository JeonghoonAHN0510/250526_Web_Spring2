package example2.day108.javaRelationship;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity @Builder
@Table(name = "ecategory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int cno;
    private String cname;
    // 양방향 연결 : 상위 엔티티가 하위 엔티티 참조 관계
    @OneToMany(     // 1:M, 하나의 PK가 다수 FK에게
            mappedBy = "categoryEntity"
    )
    @ToString.Exclude   // 순환참조 방지
    @Builder.Default    // 빌더패턴 사용 시, 초기값 사용
    // JPA에서는 양방향을 사용하고, DB에서는 양방향을 사용하지 않는다.
    private List<BoardEntity> boardEntityList = new ArrayList<>();
} // class end
package example2.day108.javaRelationship;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "eboard")
@Data
@Builder
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

    // 양방향 연결
    @OneToMany(mappedBy = "boardEntity")
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();
} // class end

/*
[ 영속성 ]
- Java 데이터와 DB 데이터를 연결하는 것
- 영속 중이라면, Java 객체의 데이터 수정하면 DB 데이터가 수정된다.
- 영속 중이 아니라면, Java 객체의 데이터를 수정해도 DB 데이터가 수정되지 않는다.

[ PK-FK 제약조건 옵션 ]
- cascade
    1. cascade=CascadeType.ALL : 부모가 삭제/수정/저장되면, 자식도 같이 삭제/수정/저장 진행
    2. cascade=CascadeType.PERSIST : 부모가 저장되면, 자식도 같이 저장 진행
    3. cascade=CascadeType.MERGE : 부모가 수정되면, 자식도 같이 수정 진행
    4. cascade=CascadeType.REMOVE : 부모가 삭제되면, 자식도 같이 삭제 진행
    5. cascade=CascadeType.REFRESH : 부모가 재호출되면, 자식도 같이 재호출 진행
    6. cascade=CascadeType.DETACH : 부모가 영속해제되면, 자식도 영속해제 진행

- fetch : 부모/자식 엔티티를 조회하는 방법
    1. fetch=FetchType.EAGER : 해당 엔티티를 조회하면, 참조 엔티티를 즉시 조회
        -> 기본값, 초기 로딩이 느림, 불필요한 엔티티 정보가 있을 경우 성능 저하
    2. fetch=FetchType.LAZY : 해당 엔티티를 조회하면, 참조 엔티티를 즉시 조회하지 않는다.
        -> 초기 로딩이 빠름, 사용할 엔티티 정보를 적절하게 사용하면 성능 최적화
        -> 참조엔티티.getXXX()하면 엔티티 조회 진행


- 동일한 조회결과는 MyBatis와 JPA는 자동 1차 캐싱을 해준다.
*/
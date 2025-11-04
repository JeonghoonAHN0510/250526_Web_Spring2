package example2.day107;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity                 // 해당 클래스를 데이터베이스 테이블과 매핑
@Table(name = "goods")  // 테이블 이름 정의, 생략 시 클래스명으로 정의
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsEntity {
    @Id // PK 설정 - Entity 1개당 1개는 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가 = AUTO_INCREMENT
    private int gno;        // 제품번호, int --> int

    // 컬럼 속성 지정 : @Column(속성명=값)
    @Column(nullable = false, length = 100)
    private String gname;   // 제품명, String --> varchar(255)

    private int gprice;     // 제품가격

    // columnDefinition : 컬럼 속성 커스텀
    @Column(columnDefinition = "varchar(100) default '제품설명' not null")
    private String gdesc;   // 제품설명
} // class end
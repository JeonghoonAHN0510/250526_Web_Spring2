package example2.day108.javaRelationship;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ereply")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;            // 댓글번호
    private String rcontent;    // 댓글내용
    // 단방향 연결
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "bno")   // FK 필드명 설정
    private BoardEntity boardEntity;
} // class end
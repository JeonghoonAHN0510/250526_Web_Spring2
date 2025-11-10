package example2.day111.model.entity;

import example2.day111.model.dto.TodoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity     // 해당 Entity를 테이블로 매핑
@Table(name = "todo")   // 테이블명 정의
@Data
@Builder    // Builder 왜 사용? -> 객체를 유연하게 생성하기 위해서
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity extends BaseTime{
    // + BaseTime : 엔티티 생성/수정 날짜 및 시간 상속
    // -> AppStart에 @EnableJpaAuditing 주입 필요

    // 1. 테이블 설계
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;             // PK
    @Column(nullable = false, length = 100) // 컬럼 옵션 설정
    private String title;       // 제목
    @Column(columnDefinition = "longtext")  // SQL 문법 작성
    private String content;     // 내용
    @Column(columnDefinition = "boolean default false")
    private boolean done;       // 실행여부
    // 2. 참조관계 설계

    // 3. toDto 생성 : R
    public TodoDto toDto(){
        return TodoDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .done(this.done)
                .createDate(this.getCreateDate().toString())
                .updateDate(this.getUpdateDate().toString())
                .build();
    } // func end
} // class end
package example2.day111.model.dto;

import example2.day111.model.entity.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    // 데이터를 이동시키는 객체(Data Transfer Object)
    // 기능 구현에 필요한 목적에 따른 이동할 데이터 구성
    // 1) 테이블과 유사하게 2) 기능/상황별로 구성

    // 1. 기본적인 정보
    private int id;             // R U
    private String title;       // C R U
    private String content;     // C R U
    private boolean done;       // C R U
    private String createDate;  // R
    private String updateDate;  // R

    // 2. 부가적인 정보

    // 3. toEntity 생성 : C
    public TodoEntity toEntity(){
        return TodoEntity.builder()
                .title(this.title)
                .content(this.content)
                .done(this.done)
                .build();
    } // func end
} // class end
package example2.day108.javaReferences;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
public class Category {
    // 1. 멤버변수
    private int cno;        // 카테고리번호
    private String cname;   // 카테고리명
    @ToString.Exclude       // toString 제외
    private List<Board> boardList = new ArrayList<>();
    // 2. 생성자

    // 3. 메소드
} // class end
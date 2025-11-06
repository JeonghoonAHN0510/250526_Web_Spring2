package example2.day108.javaReferences;

public class Example {
    public static void main(String[] args) {
        // 자바는 100% 객체지향언어
        System.out.println("출력");
        // System 클래스, System.out 객체, print() 함수
        // -> 클래스를 코드에 설계하고 클래스 기반으로 new를 통해 객체를 만든다.
        // -> 아파트를 도면에 설계하고 도면 기반으로 아파트를 만든다.

        // JPA는 영속성의 특징을 가짐
        // -> DB의 테이블 == (Entity)클래스
        // -> DB의 행 == (Entity)인스턴스

        // [1] 카테고리 생성 - PK(상위 테이블)
        Category category1 = new Category();
        category1.setCno(1);
        category1.setCname("공지사항");

        Category category2 = new Category();
        category2.setCno(2);
        category2.setCname("자유게시판");

        // [2] 게시물 생성 - FK(하위 테이블)
        // 공지사항에 작성될 게시물
        Board board1 = new Board();
        board1.setBno(1); board1.setBtitle("공지1");
        board1.setBcontent("공지내용1");
        board1.setCategory(category1);
        System.out.println(board1.getCategory().getCname());

        // [3] 공지사항 데이터로 게시물 조회 - 양방향
        category1.getBoardList().add(board1);
        System.out.println(category1.getBoardList());

        // [4] 상황1 : 1번 공지사항에 게시물 작성
        Board board2 = new Board();
        board2.setBno(2); board2.setBtitle("공지2");
        board2.setBcontent("공지내용2");
        board2.setCategory(category1);
        // 양방향 참조
        category1.getBoardList().add(board2);
        System.out.println(category1.getBoardList());
        System.out.println(board2.getCategory());
    } // main end
} // class end
package example.day11;

import java.util.List;
import java.util.function.Function;

public class Example3 {
    public static void main(String[] args) {
        /*
        [ 메소드 레퍼런스 ]
        - **이미 정의된** 메소드를 참조해서 사용하는 표현식
            -> 람다식과 함께 사용가능
        - 클래스명::정적메소드명 vs 클래스명.정적메소드명()
            -> 정적메소드   : 객체(인스턴스)없이 사용 가능한 메소드, static이 있는
            -> (멤버)메소드 : 객체를 통해 사용 가능한 메소드, static이 없는
        - 객체명::메소드명 vs 객체명.메소드명()
        */

        // [1] 예제1 : static 메소드 -> 객체 필요 X
        System.out.println(Integer.parseInt("123"));
        Function<String, Integer> function = Integer::parseInt;
        System.out.println(function.apply("123"));

        // [2] 예제2 : 메소드 -> 객체 필요 O
        System.out.println("=====");
        List<String> names = List.of("유재석", "강호동", "신동엽");
        for (String name : names){
            System.out.println(name);
        } // for end
        System.out.println("=====");
        names.forEach(name -> System.out.println(name));
        System.out.println("=====");
        names.forEach(System.out::println);

        // [3] 예제3
        // 클래스명::new -> 생성자
        names.forEach(name -> new Member(name));
        names.forEach(Member::new);
        // MyBatis SelectAll 자동화에서 사용가능(?)
        List<Member> members = names.stream().map(Member::new).toList();
        System.out.println("members = " + members);
    } // main end
} // class end
class Member{
    String name;
    Member(String name){
        this.name = name;
    } // func end
} // class end
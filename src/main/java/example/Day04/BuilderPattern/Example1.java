package example.Day04.BuilderPattern;

import lombok.Builder;
import lombok.ToString;

// (1) 실행 클래스
public class Example1 {
    public static void main(String[] args) {
        // 1. 생성자 -> new XXX()
        // 1-1. 기본 생성자
        MemberDto m1 = new MemberDto();
        // 1-2. 전체 생성자
        MemberDto m2 = new MemberDto( "유재석", 40 );

        // 2. Builder Pattern
        MemberDto m3 = MemberDto.builder().build();
        System.out.println("m3 = " + m3);

        MemberDto m4 = MemberDto.builder().name( "유재석" ).build();
        System.out.println("m4 = " + m4);

        MemberDto m5 = MemberDto.builder().age( 40 ).name( "유재석" ).build();
        System.out.println("m5 = " + m5);



    } // main end
} // class end

// (2) 설계 클래스
@Builder        // 빌더 패턴 활성화
@ToString
class MemberDto{
    // 1. 멤버변수 : 객체 속성
    private String name;
    private int age;

    // 2. 생성자 : 객체를 생성할 때 사용되는 초기화 메소드
    // 2-1. 기본 생성자 -> 매개변수가 없음, @NoArgsConstructor
    MemberDto(){}
    // 2-2. 전체 생성자 -> 모든 멤버변수를 매개변수로 받음, @AllArgsConstructor
    MemberDto( String name, int age ){
        this.name = name;
        this.age = age;
    } // func end

    // 3. 메소드 : 객체의 이벤트 메소드

} // class end

/*
**************** 생성자 규칙 ****************
1. 존재하지않는 생성자는 생성할 수 없다.
2. 매개변수의 순서를 바꾸면 불가능하고, 가능해도 의미가 달라진다.
3. 정해진 매개변수에 따라 사용할 수 있다. -> 유연성이 떨어진다.

**************** Builder Pattern ****************
- 생성자의 유무에 상관없이 메소드로 객체를 초기화할 수 있다.


*/
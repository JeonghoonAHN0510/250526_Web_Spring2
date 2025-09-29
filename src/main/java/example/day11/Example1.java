package example.day11;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// [2] 인터페이스 정의 : 주로 상수, 추상메소드를 정의한다.
interface Calculator{
    int plus(int a, int b);     // 추상 메소드
} // interface end

public class Example1 {
    // [1] 일반 메소드 정의
    public static int plus(int a, int b){
        return a+b;
    } // func end
    public static void main(String[] args) {
        // [1] 일반 메소드 호출 : static일 때, 객체가 필요없다.
        int result1 = plus(3, 5);
        System.out.println("[1] 일반 메소드(static) : " + result1);

        // [2] 인터페이스 추상메소드 호출 : 1) 구현체, 2) 익명구현체(1회성)
        // 1) implements해서 구현체를 만든다.
        // 2) new 인터페이스명(){구현} : 원래는 생성자가 없기에 객체를 만들 수 없다.
        Calculator calculator = new Calculator(){
            @Override
            public int plus(int a, int b) {
                return a+b;
            } // func end
        }; // interface end
        int result2 = calculator.plus(3, 5);
        System.out.println("[2] 익명 구현체(메소드) : " + result2);

        // [3] 람다식으로 익명 구현체만들기(java 8~)
        Calculator calculator2 = ( x, y ) -> x + y;
        int result3 = calculator2.plus(3, 5);
        System.out.println("[3] 익명 구현체(람다식) : " + result3);

        // [4] 람다식을 사용하는 함수형 인터페이스들
        // [4-1] Function< T, R > : T(입력)를 받아서, R(결과)을 반환 -> .apply(T)
        // 제네릭 : 인스턴스(객체) 생성 시, 인스턴스 내 멤버들의 타입 정의(기본타입 불가능)
        Function<Integer, Integer> function = x -> x * 2;
        System.out.println("[4-1] Function : " + function.apply(3));

        // [4-2] Supplier<T> : 입력 없이, T(결과)를 반환 -> .get(T)
        Supplier<Double> supplier = () -> Math.random();
        System.out.println("[4-2] Supplier : " + supplier.get());

        // [4-3] Consumer<T> : T(입력)을 받고, 결과는 없음 -> .accept(T)
        Consumer<String> consumer = x -> System.out.println("[4-3] Consumer : " + x);
        consumer.accept("안녕하세요.");

        // [4-4] Predicate<T> : T(입력)을 받아서 결과를 boolean으로 반환 -> .test(T)
        Predicate<Integer> predicate = x -> x % 2 == 0;
        System.out.println("[4-4] Predicate : " + predicate.test(4));
    } // main end
} // class end
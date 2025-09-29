package example.day11;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Example2 {
    public static void main(String[] args) {
        // 임의 리스트 만들기
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2);

        // stream이란?? 데이터가 다니는 통로
        // stream API : 데이터(매개변수) ---> 중간연산 ---> 최종출력

        // [1] stream() + forEach()
        numbers.stream().forEach(x -> System.out.println("[1] forEach : " + x));

        // [2] stream() + map() + collect(Collectors.toXXX)
        // map() : return이 있는 반복
        List<Integer> newNumbers = numbers.stream().map(x -> x * 2).toList();
        System.out.println("[2] newNumbers : " + newNumbers);

        // [3] stream() + map() + 최종출력(forEach())
        numbers.stream()                                                        // 스트림 시작
               .map(x -> x * 2)                                                 // 중간 연산
               .forEach(x -> System.out.println("[3] map + forEach : " + x));   // 최종 출력

        // [4] stream() + filter() + 최종출력(forEach())
        numbers.stream()
               .filter(x -> x % 2 == 0)
               .forEach(x -> System.out.println("[4] filter + forEach : " + x));

        // [5] stream() + sorted() + 최종출력
        // sorted() : 오름차순(기본값)
        // sorted(Comparator.reverseOrder()) : 내림차순
        numbers.stream()
            // .sorted()
               .sorted(Comparator.reverseOrder())
               .forEach(x -> System.out.println("[5] sorted + forEach : " + x));

        // [6] stream() + distinct() + 최종출력
        // distinct() : 중복제거
        List<Integer> distinctList = numbers.stream().distinct().toList();
        System.out.println("[6] distinctList : " + distinctList);

        // [7] stream() + limit() + 최종출력
        // limit(N) : 앞부터 N개의 데이터만 추출
        List<Integer> limitList = numbers.stream().limit(5).toList();
        System.out.println("[7] limitList : " + limitList);
    } // main end
} // class end
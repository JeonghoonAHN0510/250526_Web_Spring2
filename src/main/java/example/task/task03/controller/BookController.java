package example.task.task03.controller;

import example.task.task03.model.dto.BookDto;
import example.task.task03.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Log4j2     // 로그를 처리하는 어노테이션
public class BookController {
    private final BookService bookService;

    // [*] 로그 기록해보기 -> 로그 함수 사용(부가기능 제공)
    @GetMapping("/log")
    public void log(){
        // log.XXX : 출력함수처럼 출력하는 메시지 함수(부가기능 제공 - 파일처리, 제약조건 등)
        log.debug("테스트 과정");                         // 테스트(개발) 단계에서 사용
        log.info("서비스의 흐름, 데이터 상태 확인");         // 운용 단계에서 사용
        log.warn("잠재적인 문제 알림");                    // 유지보수 단계에서 사용
        log.error("예외 또는 오류, 실패 알림");             // 운용, 유지보수 단계에서 사용
    } // func end

    // 1. 도서대출
    @PostMapping("/rent")
    public ResponseEntity<Boolean> rentBook(@RequestBody BookDto bookDto){
        log.debug("[대여신청] " + bookDto.toString());
        boolean rentBook = false;
        try {
            rentBook = bookService.rentBook(bookDto);
            log.debug("[대여성공] " + bookDto);
            return ResponseEntity.ok(rentBook);
        } catch (RuntimeException e) {
            log.debug("[대여실패] " + e.getMessage());
            return ResponseEntity.status(405).body(rentBook);
        } // try-catch end
    } // func end
    // 2. 도서반납
    @PostMapping("/return")
    public ResponseEntity<Boolean> returnBook(@RequestBody BookDto bookDto){
        log.debug("[반납신청] " + bookDto.toString());
        boolean returnBook = false;
        try {
            returnBook = bookService.returnBook(bookDto);
            log.debug("[반납성공] " + bookDto);
            return ResponseEntity.ok(returnBook);
        } catch (RuntimeException e) {
            log.debug("[반납실패] " + e.getMessage());
            return ResponseEntity.status(405).body(returnBook);
        } // try-catch end
    } // func end
} // class end
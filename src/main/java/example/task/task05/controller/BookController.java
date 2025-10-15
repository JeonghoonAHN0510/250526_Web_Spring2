package example.task.task05.controller;

import example.task.task05.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/task05")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    // 1. books 테이블에 price int 속성 추가
    @PostMapping("/addField")
    public ResponseEntity<?> addField(){
        bookService.addField();
        return ResponseEntity.ok(true);
    } // func end

    // 2. books 테이블의 title 속성의 타입을 longtext로 수정
    @PostMapping("/updateField")
    public ResponseEntity<?> updateField(){
        bookService.updateField();
        return ResponseEntity.ok(true);
    } // func end

    // 3. 평균 재고보다 많은 재고를 가진 도서 조회
    @GetMapping("/getMore")
    public ResponseEntity<?> getMoreStockBooks(){
        return ResponseEntity.ok(bookService.getMoreStockBooks());
    } // func end

    // 4. 가장 많이 대출한 도서 조회
    @GetMapping("/getMost")
    public ResponseEntity<?> getMostRentedBook(){
        return ResponseEntity.ok(bookService.getMostRentedBook());
    } // func end
} // class end
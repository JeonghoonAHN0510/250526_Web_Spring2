package example.task.task06.controller;

import example.task.task06.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task06")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    // 1. 대출기록 상세 View 생성(books+rentals)
    @PostMapping("/createView1")
    public ResponseEntity<?> createBooksRentalsView(){
        bookService.createBooksRentalsView();
        return ResponseEntity.ok(true);
    } // func end
    // 2. 평균보다 많은 재고를 가진 도서 View 생성
    @PostMapping("/createView2")
    public ResponseEntity<?> createMoreStockBooksView(){
        bookService.createMoreStockBooksView();
        return ResponseEntity.ok(true);
    } // func end
    // 3. 대출기록 상세 View 조회
    @GetMapping("/getView1")
    public ResponseEntity<?> getBooksRentalsView(){
        return ResponseEntity.ok(bookService.getBooksRentalsView());
    } // func end
    // 4. 평균보다 많은 재고를 가진 도서 View 조회
    @GetMapping("/getView2")
    public ResponseEntity<?> getBooksMoreStockBooksView(){
        return ResponseEntity.ok(bookService.getBooksMoreStockBooksView());
    } // func end
} // class end
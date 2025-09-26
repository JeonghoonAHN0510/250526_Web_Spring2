package example.task.task03.controller;

import example.task.task03.model.dto.BookDto;
import example.task.task03.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    // 1. 도서대출
    @PostMapping("/rent")
    public ResponseEntity<Boolean> rentBook(@RequestBody BookDto bookDto){
        boolean rentBook = false;
        try {
            rentBook = bookService.rentBook(bookDto);
            return ResponseEntity.ok(rentBook);
        } catch (RuntimeException e) {
            return ResponseEntity.status(405).body(rentBook);
        } // try-catch end
    } // func end
    // 2. 도서반납
    @PostMapping("/return")
    public ResponseEntity<Boolean> returnBook(@RequestBody BookDto bookDto){
        boolean returnBook = false;
        try {
            returnBook = bookService.returnBook(bookDto);
            return ResponseEntity.ok(returnBook);
        } catch (RuntimeException e) {
            return ResponseEntity.status(405).body(returnBook);
        } // try-catch end
    } // func end
} // class end
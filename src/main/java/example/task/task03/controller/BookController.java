package example.task.task03.controller;

import example.task.task03.model.dto.BookDto;
import example.task.task03.service.BookService;
import lombok.RequiredArgsConstructor;
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
    public boolean rentBook(@RequestBody BookDto bookDto){
        return bookService.rentBook(bookDto);
    } // func end
    // 2. 도서반납
    @PostMapping("/return")
    public boolean returnBook(@RequestBody BookDto bookDto){
        return bookService.returnBook(bookDto);
    } // func end
} // class end
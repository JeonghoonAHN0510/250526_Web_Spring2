package example.task.task04.controller;

import example.task.task04.model.dto.BookDto;
import example.task.task04.model.dto.RentalDto;
import example.task.task04.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task04")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    // 1. 단일 등록
    @PostMapping("/book")
    public ResponseEntity<?> postBook(@RequestBody BookDto bookDto){
        System.out.println("bookDto = " + bookDto);
        if (bookService.postBook(bookDto) == 1){
            System.out.println("bookDto = " + bookDto);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        } // if end
    } // func end
    // 2. 일괄 등록
    @PostMapping("/books")
    public ResponseEntity<?> postBooks(@RequestBody List<BookDto> bookDtoList){
        System.out.println("bookDtoList = " + bookDtoList);
        if (bookService.postBooks(bookDtoList) >= 1){
            System.out.println("bookDtoList = " + bookDtoList);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        } // func end
    } // func end
    // 3. 대출 검색 - 대출한 사람 || 대출한 도서명
    @GetMapping("/rentals")
    public ResponseEntity<?> getRentals(@RequestParam(required = false) String member,
                                        @RequestParam(required = false) String title){
        List<RentalDto> rentalDtoList = bookService.getRentals(member, title);
        return ResponseEntity.ok(rentalDtoList);
    } // func end
} // class end
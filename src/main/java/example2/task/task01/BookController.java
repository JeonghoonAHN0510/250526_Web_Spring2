package example2.task.task01;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    // 1. 도서 등록
    // { "bid": 1, "btitle": "테스트1", "bauthor" : "강호동", "bpublisher" : "더조은출판사" }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody BookEntity bookEntity){
        System.out.println("bookEntity = " + bookEntity);
        return ResponseEntity.ok(bookService.save(bookEntity));
    } // func end

    // 2. 도서 전체 조회
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(bookService.findAll());
    } // func end

    // 3. 특정 도서 수정
    @PutMapping
    public ResponseEntity<?> update(@RequestBody BookEntity bookEntity){
        System.out.println("bookEntity = " + bookEntity);
        return ResponseEntity.ok(bookService.put(bookEntity));
    } // func end

    // 4. 특정 도서 삭제
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int bId){
        return ResponseEntity.ok(bookService.delete(bId));
    } // func end
} // class end
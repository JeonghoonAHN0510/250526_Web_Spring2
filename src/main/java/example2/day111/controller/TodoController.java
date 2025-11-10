package example2.day111.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example2.day111.service.TodoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    // 1. query1 실행해보기
    @GetMapping("/query1")
    public ResponseEntity<?> query1(@RequestParam String title){
        return ResponseEntity.ok(todoService.query1(title));
    } // func end

    // 2. query2 실행해보기
    @GetMapping("/query2")
    public ResponseEntity<?> query2(@RequestParam String title,
                                    @RequestParam String content){
        return ResponseEntity.ok(todoService.query2(title, content));
    } // func end

    // 3. query3 실행해보기
    @GetMapping("/query3")
    public ResponseEntity<?> query3(@RequestParam(required = false) String keyword){
        keyword = keyword == null ? "" : keyword;
        return ResponseEntity.ok(todoService.query3(keyword));
    } // func end
} // class end
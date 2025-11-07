package example2.task.task04;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    // 1. todo 등록
    // {"title" : "테스트", "content" : "테스트내용"}
    @PostMapping
    public ResponseEntity<?> save(@RequestBody TodoDto todoDto){
        return ResponseEntity.ok(todoService.save(todoDto));
    } // func end

    // 2. todo 전체조회
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(todoService.findAll());
    } // func end
} // class end
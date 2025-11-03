package example2.day106;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/exam")
public class ExamController {
    private final ExamService examService;

    // 1. C
    // { "col1": 1, "col2": "유재석", "col3" : 3.14 }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody ExamEntity examEntity){
        return ResponseEntity.ok(examService.save(examEntity));
    } // func end

    // 2. R
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(examService.findAll());
    } // func end

    // 3. U
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ExamEntity examEntity){
        return ResponseEntity.ok(examService.put2(examEntity));
    } // func end

    // 4. D
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int col1){
        return ResponseEntity.ok(examService.delete(col1));
    } // func end
} // class end
package example2.task.task02;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    // 1. 등록
    // { "director" : "최초영화감독", "title" : "최초영화제목", "rating" : "3.14" }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody MovieDto movieDto){
        return ResponseEntity.ok(movieService.save(movieDto));
    } // func end

    // 2. 전제조회
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(movieService.findAll());
    } // func end

    // 3. 개별조회
    @GetMapping("/byid")
    public ResponseEntity<?> findById(@RequestParam int movie_id){
        return ResponseEntity.ok(movieService.findById(movie_id));
    } // func end

    // 4. 수정
    // { "movie_id" : 1, "director" : "수정영화감독", "title" : "수정영화제목", "rating" : "9.99" }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody MovieDto movieDto){
        return ResponseEntity.ok(movieService.update(movieDto));
    } // func end

    // 5. 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam int movie_id){
        return ResponseEntity.ok(movieService.deleteById(movie_id));
    } // func end
} // class end
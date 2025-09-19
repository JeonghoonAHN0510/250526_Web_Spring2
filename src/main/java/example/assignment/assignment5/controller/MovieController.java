package example.assignment.assignment5.controller;

import example.assignment.assignment5.model.dto.MovieDto;
import example.assignment.assignment5.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
@CrossOrigin( value = "http://localhost:5173")  // React 서버와 CORS 통신 허용
public class MovieController {
    private final MovieService movieService;

    /**
     * 제목, 감독, 장르, 간단한 소개, (삭제를 위한) 비밀번호를 입력받아, movie table에 저장한다.
     * <p>
     * 영화 등록시 사용되는 메소드
     * <p>
     * method : POST, URL : /movie/post
     * @param movieDto
     * @return boolean
     */
    @PostMapping("/post")
    public ResponseEntity<Boolean> postMovie( @RequestBody MovieDto movieDto ){
        System.out.println("MovieController.postMovie");
        // 1. Service로부터 결과받기
        boolean result = movieService.postMovie( movieDto );
        // 2. 결과 반환하기
        if ( result ){
            return ResponseEntity.status(200).body( true );
        } else {
            return ResponseEntity.status(400).body( false );
        } // if end
    } // func end

    /**
     * PK 번호, 비밀번호를 입력받아, 해당 영화를 삭제한다.
     * <p>
     * method : PUT, URL : /movie/delete
     * @param movieDto
     * @return boolean
     */
    @PutMapping("/delete")
    public ResponseEntity<Boolean> deleteMovie( @RequestBody MovieDto movieDto ){
        System.out.println("MovieController.deleteMovie");
        System.out.println("movieDto = " + movieDto);
        // 1. Service로부터 결과 받기
        boolean result = movieService.deleteMovie( movieDto );
        // 2. 결과 반환하기
        if ( result ){
            return ResponseEntity.status(200).body( true );
        } else {
            return ResponseEntity.status(400).body( false );
        } // if end
    } // func end

    /**
     * 등록된 추천 영화목록을 조회한다.
     * <p>
     * method : GET, URL : /movie/getMovies
     * @return List, MovieDto
     */
    @GetMapping("/getMovies")
    public ResponseEntity<List<MovieDto>> getMovies(){
        System.out.println("MovieController.getMovies");
        // 1. Service로부터 결과 받기
        List<MovieDto> result = movieService.getMovies();
        // 2. 결과 반환하기
        if ( result == null ){
            return ResponseEntity.status(400).body( null );
        } else {
            return ResponseEntity.status(200).body( result );
        } // if end
    } // func end
} // class end
package example2.task.task02;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    // 1. 등록
    public MovieDto save(MovieDto movieDto){
        // 1-1. 현재 시간 저장
        movieDto.setRelease_date(LocalDateTime.now().toString());
        // 1-2. 등록 후 반환
        return movieRepository.save(movieDto.toEntity()).toDto();
    } // func end

    // 2. 전제조회
    public List<MovieDto> findAll(){
        // 2-1. .findAll을 통해 모든 Entity 조회
        List<MovieEntity> movieEntities = movieRepository.findAll();
        // 2-2. Stream API를 통해 Entity를 Dto로 변환하여 List로 반환
        return movieEntities
                .stream().map(MovieEntity::toDto)
                .collect(Collectors.toList());
    } // func end

    // 3. 개별조회
    public MovieDto findById(int movie_id){
        // 3-1. PK값으로 Optional 반환받기
        Optional<MovieEntity> optional = movieRepository.findById(movie_id);
        // 3-2. 존재한다면
        if (optional.isPresent()){
            // 3-3. Entity를 꺼내서 반환하기
            MovieEntity movieEntity = optional.get();
            return movieEntity.toDto();
        } else {
            // 3-4. 없으면, null 반환
            return null;
        } // if end
    } // func end

    // 4. 수정
    public MovieDto update(MovieDto movieDto){
        // 4-1. PK값으로 Optional 반환받기
        Optional<MovieEntity> optional = movieRepository.findById(movieDto.getMovie_id());
        // 4-2. 존재한다면
        if (optional.isPresent()){
            // 4-3. Entity를 꺼내서
            MovieEntity movieEntity = optional.get();
            // 4-4. setter로 수정작업 진행하기 -> @Transactional 필수
            movieEntity.setDirector(movieDto.getDirector());
            movieEntity.setTitle(movieDto.getTitle());
            movieEntity.setRating(movieDto.getRating());
            movieEntity.setReleaseDate(LocalDateTime.now().toString());
            // 4-5. 수정된 Entity를 Dto로 변환하여 반환
            return movieEntity.toDto();
        } // if end
        return null;
    } // func end

    // 5. 삭제
    public boolean deleteById(int movie_id){
        // 5-1. PK값으로 존재여부 확인
        if (movieRepository.existsById(movie_id)){
            // 5-2. 존재하면, 삭제 진행 후 true 반환
            movieRepository.deleteById(movie_id);
            return true;
        } else {
            // 5-3. 없으면, false 반환
            return false;
        } // if end
    } // func end
} // class end
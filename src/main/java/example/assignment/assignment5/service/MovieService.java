package example.assignment.assignment5.service;

import example.assignment.assignment5.model.dto.MovieDto;
import example.assignment.assignment5.model.mapper.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieMapper movieMapper;

    /**
     * 제목, 감독, 장르, 간단한 소개, (삭제를 위한) 비밀번호를 입력받아, movie table에 저장한다.
     * <p>
     * 영화 등록시 사용되는 메소드
     * @param movieDto
     * @return boolean
     */
    public boolean postMovie( MovieDto movieDto ){
        return movieMapper.postMovie( movieDto );
    } // func end
    // [2] 영화 삭제

    // [3] 영화 전체조회
} // class end
package example.Day06;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/day06/batis")
@RestController             // 스프링 컨테이너에 빈 등록
@RequiredArgsConstructor    // final 변수에 자동으로 생성자 생성
public class BatisController {
    // * Mapper 객체 의존성 주입
    private final BatisMapper batisMapper;

    // 1. 학생 등록

    // 2. 전체학생 조회
    @GetMapping("")
    // ResponseEntity 활용하기
    public ResponseEntity<List<StudentDto>> findAll(){
        // 1. Mapper로부터 결과값 받아오기
        List<StudentDto> studentDtos = batisMapper.findAll();
        // 2. ResponseEntity에 상태 + 값 넣어서 반환하기
        return ResponseEntity.status(200).body(studentDtos);
    } // func end

    // 3. 개별학생 조회

    // 4. 개별학생 삭제

    // 5. 개별학생 수정

} // class end
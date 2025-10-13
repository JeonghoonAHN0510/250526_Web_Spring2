package example.day13;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/xml")
@RequiredArgsConstructor
public class XmlController {
    private final XmlMapper xmlMapper;
    // 1. 등록
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody StudentDto studentDto){
        // <?> : 제네릭 타입에 ?를 넣으면 모든 타입을 지칭한다.
        System.out.println("studentDto = " + studentDto);
        xmlMapper.save(studentDto);
        System.out.println("studentDto = " + studentDto);
        return ResponseEntity.ok(true);
    } // func end

    // 2. 전체조회
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        List<StudentDto> result = xmlMapper.getAll();
        return ResponseEntity.ok(result);
    } // func end

    // 3. 개별조회
    @GetMapping("/bySno")
    public ResponseEntity<?> getBySno(@RequestParam int sno){
        StudentDto result = xmlMapper.getBySno(sno);
        return ResponseEntity.ok(result);
    } // func end

    // 4. 개별삭제
    @DeleteMapping("")
    public ResponseEntity<?> deleteBySno(@RequestParam int sno){
        xmlMapper.deleteBySno(sno);
        return ResponseEntity.ok(true);
    } // func end

    // 5. 개별수정
    @PutMapping("")
    public ResponseEntity<?> updateBySno(@RequestBody StudentDto studentDto){
        xmlMapper.updateBySno(studentDto);
        return ResponseEntity.ok(true);
    } // func end
} // class end
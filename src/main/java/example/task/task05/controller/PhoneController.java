package example.task.task05.controller;

import example.task.task05.model.dto.PhoneDto;
import example.task.task05.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phone")
@RequiredArgsConstructor
@CrossOrigin( value = "http://localhost:5173")  // React 서버와 CORS 통신 허용
public class PhoneController {
    private final PhoneService phoneService;

    /**
     * 전화번호부 등록 시 실행되는 메소드
     * @param phoneDto
     * @return ResponseEntity, Boolean
     */
    @PostMapping("")
    public ResponseEntity<Boolean> addList( @RequestBody PhoneDto phoneDto ){
        System.out.println("PhoneController.addList");
        return ResponseEntity.ok().body( phoneService.addList( phoneDto ) );
    } // func end

    /**
     * 전화번호부 전체조회 시 실행되는 메소드
     * @return ResponseEntity, List, PhoneDto
     */
    @GetMapping("")
    public ResponseEntity<List<PhoneDto>> getList(){
        System.out.println("PhoneController.getList");
        return ResponseEntity.ok().body( phoneService.getList() );
    } // func end

    /**
     * 전화번호부에서 회원 삭제 시 실행되는 메소드
     * @param mno
     * @return ResponseEntity, Boolean
     */
    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteList( @RequestParam int mno ){
        System.out.println("PhoneController.deleteList");
        return ResponseEntity.ok().body( phoneService.deleteList( mno ) );
    } // func end
} // class end
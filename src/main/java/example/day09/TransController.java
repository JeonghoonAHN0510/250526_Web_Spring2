package example.day09;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/day09/trans")
public class TransController {
    private final TransService transService;

    // 1. 유재석과 강호동을 등록하는 예제
    @PostMapping
    public boolean trans1(){
        return transService.trans1();
    } // func end

    // 2. 신동엽이 서장훈에게 10만원을 보내는 예제
    @PostMapping("/transfer")
    public boolean transfer(@RequestBody Map<String, Object> transInfo){
        return transService.transfer(transInfo);
    } // func end
} // class end
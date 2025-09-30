package example.day12;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/axios")
public class AxiosController {
    // [1] 예제 1
    @GetMapping
    public int axios1(){
        System.out.println("AxiosController.axios1");
        return 10;
    } // func end

    // [2] 예제 2
    @PostMapping("/login")
    public boolean axios2(@RequestBody Map<String,String> map, HttpSession session){
        String id = map.get("id");
        session.setAttribute("loginId", id);        // 1. 로그인 세션 등록
        return true;
    } // func end

    // [3] 예제 3
    @GetMapping("/info")
    public boolean axios3(HttpSession session){
        Object object = session.getAttribute("loginId");
        if (object == null) return false;   // 비로그인
        return true;                        // 로그인
    } // func end
} // class end
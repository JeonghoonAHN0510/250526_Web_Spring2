package example.Day05.WebCrawling;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/task/day05")
@RequiredArgsConstructor
// final 멤버변수의 생성자 자동생성 -> 빈이 등록 안되어있다면, Autowired할 수 없다.
public class CrawlingController {
    // @Autowired 스프링 컨테이너에서 빈 꺼내기 -> DI(의존성 주입)
    private final CrawlingService crawlingService;

    // 1.
    @GetMapping("/crawling1")
    public Map< String, String > task1(){
        return crawlingService.task1();
    } // func end
} // class end
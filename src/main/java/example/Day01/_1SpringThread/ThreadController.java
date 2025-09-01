package example.Day01._1SpringThread;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                     // [1] HTTP 요청/응답 처리
@RequestMapping("/task/day01")   // [2] HTTP URL 연결(매핑)
@RequiredArgsConstructor            // [3] final 변수의 생성자 자동 생성
public class ThreadController {
    private final ThreadService threadService;

    // 동기화 : 스프링 매핑 컨트롤러는 자동 동기화 처리한다. -> 먼저 요청한 HTTP부터 처리 후 반환한다.

    @GetMapping
    public int thread1(){
        System.out.println("ThreadController.thread1");
        // Service의 thread1 호출 후, 반환하기
        return threadService.thread1();
    } // func end

    @DeleteMapping
    public void thread2(){
        System.out.println("ThreadController.thread2");
        // Service의 thread2 호출 후, 반환하기
        threadService.thread2();
    } // func end
} // class end
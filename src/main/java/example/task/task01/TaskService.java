package example.task.task01;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskDao taskDao;

    // [1] 매 30초마다 모든 제품의 재고가 3개씩 감소한다.
    @Scheduled( cron = "*/30 * * * * *" )
    public void task1(){
        System.out.println("TaskService.task1 - 재고 3개 감소");
        taskDao.task1();
    } // func end

    // [2] 매 1분마다 모든 제품의 정보를 조회한다. - console에 모든 제품 정보 조회
    @Scheduled( cron = "0 0/1 * * * *" )
    public void task2(){
        System.out.println("TaskService.task2 - 상품 정보 조회");
        List<Map<String,String>> list = taskDao.task2();
        for ( Map<String,String> map : list ){
            System.out.print("상품명 : " + map.get("name") + "\t");
            System.out.println("재고 수량 : " + map.get("quantity"));
        } // for end
    } // func end

    // [3] 매 5분마다 재고가 10개 이하인 상품의 재고를 20개 추가한다.
    @Scheduled( cron = "0 */5 * * * *" )
    public void task3(){
        System.out.println("TaskService.task3 - 조건부 재고 20개 추가");
        taskDao.task3();
    } // func end
} // class end
package example.Day01._2SpringScheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service    // 스프링 컨테이너에 Bean 등록
public class ScheduleService {

    // [1] 3초마다 실행되는 서비스 메소드 - fixedRate
    @Scheduled( fixedRate = 3000 )
    public void task1(){
        System.out.println("ScheduleService.task1 - 3초마다 실행 중");
    } // func end

    // [2] 5초마다 실행되는 서비스 메소드 - fixedRate
    // 변수를 사용 시, 상수로 선언하여 사용한다.
    final int seconds = 5000;
    @Scheduled( fixedRate = seconds )
    public void task2(){
        System.out.println("ScheduleService.task2 - 5초마다 실행 중");
    } // func end

    // [3] 시스템 날짜/시간 기준으로 실행되는 서비스 메소드( 백그라운드/비동기/멀티스레드 )
    // 매 시간의 00:00:05 마다 실행
    // cron = 초 분 시 일 월 요일
    @Scheduled( cron = "*/5 * * * * *" )
    public void task3(){
        System.out.println("ScheduleService.task3 - cron 매 5초마다 실행 중");
    } // func end

    // [4] 시스템 날짜/시간 기준으로 실행되는 서비스 메소드( 백그라운드/비동기/멀티스레드 )
    @Scheduled( cron = "0 */1 * * * *")
    public void task4(){
        System.out.println("ScheduleService.task4 - cron 매 1분마다 실행 중");
    } // func end
} // class end
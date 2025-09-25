package example.day09;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
// 지정한 메소드/클래스 내의 모든 SQL은 트랜잭션 처리
// 실행예외(RuntimeException)가 발생되면, SQL rollback
public class TransService {
    private final TransMapper transMapper;

    // 1. '유재석', '강호동' insert(commit)
    // 만약 한 명이라도 insert가 실패하면, 모두 취소(rollback)
    public boolean trans1(){
        // 0. AOP가 Before로 먼저 commit 준비
        // 1-1. '유재석' insert
        transMapper.insert1("유재석");
        // 1-2. '강호동' insert
        transMapper.insert2("강호동");
        return true;
        // 0. AOP가 After로 처리 후, commit or rollback
        // SQLSyntaxErrorException가 반환되면, rollback 처리
    } // func end

    // 2. 신동엽이 서장훈에게 10만원을 보내는 예제
    public boolean transfer(Map<String, Object> transInfo){
        int money = Integer.parseInt(String.valueOf(transInfo.get("money")));
        // 2-1. 신동엽 10만원 출금 처리
        String fromName = String.valueOf(transInfo.get("fromName"));
        transMapper.withdraw(fromName, money);
        // 2-2. 서장훈 10만원 입금 처리
        String toName = String.valueOf(transInfo.get("toName"));
        transMapper.deposit(toName, money);
        return true;
    } // func end
} // class end
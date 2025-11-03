package example2.day106;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {
    // Mapper객체 -> Repository객체
    private final ExamRepository examRepository;

    // 1. C(등록)
    public ExamEntity save(ExamEntity examEntity){
        // 1-1. Repository의 저장 메소드(.save())를 호출한다.
        // 저장 성공 시, 성공된 Entity 객체 반환
        // insert 자동처리
        ExamEntity savedEntity = examRepository.save(examEntity);
        // 1-2. 반환된 Entity 객체 반환
        return savedEntity;
    } // func end

    // 2. R(전체조회)
    public List<ExamEntity> findAll(){
        // 2-1. Repository의 전체조회(.findAll())를 호출한다.
        // 모든 Entity 객체를 반환
        // select 자동처리
        List<ExamEntity> allEntities = examRepository.findAll();
        // 2-2. 반환된 Entity 객체들 반환
        return allEntities;
    } // func end


    // 3. U


    // 4. D


} // class end
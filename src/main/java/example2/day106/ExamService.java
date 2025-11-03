package example2.day106;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional  // SQL이 2개 이상이면, 트랜잭션 필수
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

    // 3-1. U(특정한 엔티티 수정) - 비권장(없으면 생성해버리기 때문에)
    public ExamEntity put(ExamEntity examEntity){
        // 1. Repository의 저장 메소드(.save())를 호출한다.
        // .save(엔티티) : 만약에 지정한 엔티티에 PK가 없으면 생성, 있으면 수정
        ExamEntity updatedEntity = examRepository.save(examEntity);
        return updatedEntity;
    } // func end

    // 3-2. U(특정한 엔티티 수정) - 권장(트랜잭션 설정 필수)
    // 엔티티를 setter하면, 자동으로 DB도 변경된다.
    public ExamEntity put2(ExamEntity examEntity){
        // 1. 수정할 엔티티를 조회한다.
        Optional<ExamEntity> findEntity = examRepository.findById(examEntity.getCol1());
        System.out.println("findEntity = " + findEntity);
        // 2. Optional이란? 자주 발생하는 NullPointer 예외를 감싼 클래스
        // -> Null 값에 대해 안전한 유효성 검사를 제공
        if(findEntity.isPresent()){                     // .isPresent() : 존재 여부 확인
            ExamEntity getEntity = findEntity.get();    // 존재하면, 엔티티 꺼내기
            getEntity.setCol2(examEntity.getCol2());    // setter를 이용한 꺼낸 엔티티의 값 수정
            getEntity.setCol3(examEntity.getCol3());    // setter를 이용한 꺼낸 엔티티의 값 수정
            System.out.println("getEntity = " + getEntity);
            return getEntity;
        } // if end
        return examEntity;
    } // func end

    // 4. D(삭제)
    public boolean delete(int col1){
        // 4-1. Repository의 삭제(.deleteById())를 호출한다.
        // delete 자동처리
        examRepository.deleteById(col1);
        return true;
    } // func end
} // class end
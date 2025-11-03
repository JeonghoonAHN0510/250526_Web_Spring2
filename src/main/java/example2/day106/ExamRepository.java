package example2.day106;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 지정한 Entity(테이블)을 조작하는 인터페이스 주입
public interface ExamRepository extends JpaRepository<ExamEntity, Integer> {
                             // extends JpaRepository<T, ID>
                             // T : 조작할 테이블(Entity Class)
                             // ID : 조작할 테이블의 ID(PK)의 자료형


} // interface end
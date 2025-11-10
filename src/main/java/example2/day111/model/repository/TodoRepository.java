package example2.day111.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import example2.day111.model.entity.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
    // 기본적인 CRUD를 제공 : save(), findAll(), findById() ···

    // 쿼리 메소드 : SQL을 직접 작성하지않고, 메소드 이름으로 쿼리를 생성하는 방법
    // findBy필드명(String 필드명) : 카멜 표기법으로 작성
    // -> SELECT * FROM 테이블명 WHERE 필드명 = 매개변수

    List<TodoEntity> findByTitle(String title);
    // -> SELECT * FROM todo WHERE title = 매개변수;

    List<TodoEntity> findByTitleAndContent(String title, String content);
    // -> SELECT * FROM todo WHERE title = 매개변수 AND content = 매개변수;

    List<TodoEntity> findByTitleContaining(String title);
    // -> SELECT * FROM todo WHERE title LIKE '%매개변수%';

    // 네이티브 쿼리 메소드 : SQL을 직접 작성하여 실행하는 방법
    // @Query(value = "SQL", nativeQuery = true)을 추상메소드에 주입
    // :매개변수명을 이용하여 매개변수를 대입한다.
    @Query(value = "SELECT * FROM todo WHERE title = :title", nativeQuery = true)
    List<TodoEntity> query1(String title);

    @Query(value = "SELECT * FROM todo WHERE title = :title AND content = :content", nativeQuery = true)
    List<TodoEntity> query2(String title, String content);

    @Query(value = "SELECT * FROM todo WHERE title LIKE %:keyword%", nativeQuery = true)
    List<TodoEntity> query3(String keyword);

} // interface end
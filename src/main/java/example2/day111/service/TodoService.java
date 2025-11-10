package example2.day111.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import example2.day111.model.dto.TodoDto;
import example2.day111.model.entity.TodoEntity;
import example2.day111.model.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 1. query1 실행해보기
    public List<TodoDto> query1(String title){
        // 내가 만든 쿼리메소드 사용해보기
        List<TodoEntity> result1 = todoRepository.findByTitle(title);
        System.out.println("result1 = " + result1);

        // 네이티브 쿼리메소드 사용해보기
        List<TodoEntity> result2 = todoRepository.query1(title);
        System.out.println("result2 = " + result2);

        // 반환하기
        return result2
                .stream()
                .map(TodoEntity::toDto)
                .collect(Collectors.toList());
    } // func end

    // 2. query2 실행해보기
    public List<TodoDto> query2(String title, String content){
        // 내가 만든 쿼리메소드 사용해보기
        List<TodoEntity> result1 = todoRepository.findByTitleAndContent(title, content);
        System.out.println("result1 = " + result1);

        // 네이티브 쿼리메소드 사용해보기
        List<TodoEntity> result2 = todoRepository.query2(title, content);
        System.out.println("result2 = " + result2);

        // 반환하기
        return result2
                .stream()
                .map(TodoEntity::toDto)
                .collect(Collectors.toList());
    } // func end

    // 3. query3 실행해보기
    public List<TodoDto> query3(String keyword){
        // 내가 만든 쿼리메소드 사용해보기
        List<TodoEntity> result1 = todoRepository.findByTitleContaining(keyword);
        System.out.println("result1 = " + result1);

        // 네이티브 쿼리메소드 사용해보기
        List<TodoEntity> result2 = todoRepository.query3(keyword);
        System.out.println("result2 = " + result2);

        // 반환하기
        return result2
                .stream()
                .map(TodoEntity::toDto)
                .collect(Collectors.toList());
    } // func end
} // class end
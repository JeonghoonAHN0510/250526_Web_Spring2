package example2.day111.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    //====================================================== day111 ======================================================
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

    // 4. 페이징처리
    public Page<TodoDto> page(int page, int size){
        // 4-1. 페이징처리 옵션을 설정한다.
        // PageRequest.of(조회할 페이지번호, 조회할 페이지 당 개수, Sort.by(Sort.Direction.DESC, "정렬속성명"));
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id"));
        // page - 1 : JPA는 조회할 페이지가 0부터 시작하므로, -1 처리
        // size : 한 페이지에 조회할 자료 개수
        // Sort.by(Sort.Direction.DESC, "id")) : id를 기준으로 내림차순 정렬

        // 4-2. 조회한다.
        Page<TodoEntity> result = todoRepository.findAll(pageRequest);
        // Page : 페이징 처리결과를 담는 인터페이스 타입

        // 4-3. 조회 결과 반환
        // Page 타입은 .stream을 기본적으로 제공
        return result.map(TodoEntity::toDto);
    } // func end

    // 5. 검색 + 페이징처리
    public Page<TodoDto> page2(String keyword, int page, int size){
        // 5-1. 페이징처리 옵션 설정
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id"));

        // 5-2. 만약에 검색이 없으면, 전제조회 + 페이징처리
        Page<TodoEntity> result;
        if (keyword == null || keyword.isBlank()){
            result = todoRepository.findAll(pageable);
        } else {
        // 5-3. 검색이 있으면, 검색 + 페이징처리
            result = todoRepository.findByTitleContaining(keyword, pageable);
        } // if end

        // 5-3. 반환
        return result.map(TodoEntity::toDto);
    } // func end

    //====================================================== day112 ======================================================
    // 1. 전체조회
    public List<TodoDto> findAll(){
        return todoRepository.findAll()
                .stream()
                .map(TodoEntity::toDto)
                .collect(Collectors.toList());
    } // func end

    // 2. 개별삭제
    public boolean deleteById(int id){
        // 해당 id의 todo가 존재하면 삭제
        if (todoRepository.existsById(id)){
            todoRepository.deleteById(id);
            return true;
        } else {
            return false;
        } // if end
    } // func end

    // 3. 개별조회
    public TodoDto findById(int id){
        Optional<TodoEntity> optional = todoRepository.findById(id);
        if (optional.isPresent()){
            TodoEntity entity = optional.get();
            return entity.toDto();
        } else {
            return null;
        } // if end
    } // func end

    // 4. 개별수정
    public TodoDto update(TodoDto todoDto){
        Optional<TodoEntity> optional = todoRepository.findById(todoDto.getId());
        if (optional.isPresent()){
            TodoEntity entity = optional.get();
            // setter를 이용한 영속성 수정
            entity.setTitle(todoDto.getTitle());
            entity.setContent(todoDto.getContent());
            entity.setDone(todoDto.isDone());
            return entity.toDto();
        } // if end
        return null;
    } // func end
} // class end
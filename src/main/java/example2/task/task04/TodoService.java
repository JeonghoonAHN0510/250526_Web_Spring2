package example2.task.task04;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 1. todo 등록
    public TodoDto save(TodoDto todoDto){
        return todoRepository.save(todoDto.toEntity()).toDto();
    } // func end

    // 2. todo 목록조회
    public List<TodoDto> findAll(){
        List<TodoEntity> todoEntities = todoRepository.findAll();
        return todoEntities
                .stream().map(TodoEntity::toDto)
                .collect(Collectors.toList());
    } // func end

    // 3. todo 삭제
    public boolean delete(int id){
        if (todoRepository.existsById(id)){
            todoRepository.deleteById(id);
            return true;
        } else {
            return false;
        } // if end
    } // func end
} // class end
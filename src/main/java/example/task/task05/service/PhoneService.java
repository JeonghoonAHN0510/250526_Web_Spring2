package example.task.task05.service;

import example.task.task05.model.dto.PhoneDto;
import example.task.task05.model.mapper.PhoneMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneService {
    private final PhoneMapper phoneMapper;

    // [1] 등록
    public boolean addList( PhoneDto phoneDto ){
        return phoneMapper.addList( phoneDto );
    } // func end

    // [2] 전체조회
    public List<PhoneDto> getList(){
        return phoneMapper.getList();
    } // func end

    // [3] 삭제
    public boolean deleteList( int mno ){
        return phoneMapper.deleteList( mno );
    } // func end
} // class end
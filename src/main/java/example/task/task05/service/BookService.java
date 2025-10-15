package example.task.task05.service;

import example.task.task05.model.dto.BookDto;
import example.task.task05.model.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookMapper bookMapper;

    // 1. books 테이블에 price int 속성 추가
    public int addField(){
        return bookMapper.addField();
    } // func end
    // 2. books 테이블의 title 속성의 타입을 longtext로 수정
    public int updateField(){
        return bookMapper.updateField();
    } // func end
    // 3. 평균 재고보다 많은 재고를 가진 도서 조회
    public List<BookDto> getMoreStockBooks(){
        return bookMapper.getMoreStockBooks();
    } // func end
    // 4. 가장 많이 대출한 도서 조회
    public BookDto getMostRentedBook(){
        return bookMapper.getMostRentedBook();
    } // func end
} // class end
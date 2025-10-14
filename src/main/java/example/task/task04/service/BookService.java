package example.task.task04.service;

import example.task.task04.model.dto.BookDto;
import example.task.task04.model.dto.RentalDto;
import example.task.task04.model.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookMapper bookMapper;

    // 1. 단일 등록
    public int postBook(BookDto bookDto){
        return bookMapper.postBook(bookDto);
    } // func end
    // 2. 일괄 등록
    public int postBooks(List<BookDto> bookDtoList){
        return bookMapper.postBooks(bookDtoList);
    } // func end
    // 3. 대출 검색 - 대출한 사람 || 대출한 도서명
    public List<RentalDto> getRentals(String member, String title){
        return bookMapper.getRentals(member, title);
    } // func end
} // class end
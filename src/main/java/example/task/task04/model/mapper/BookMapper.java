package example.task.task04.model.mapper;

import example.task.task04.model.dto.BookDto;
import example.task.task04.model.dto.RentalDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    // 1. 단일 등록
    int postBook(BookDto bookDto);
    // 2. 일괄 등록
    int postBooks(List<BookDto> bookDtoList);
    // 3. 대출 검색 - 대출한 사람 || 대출한 도서명
    List<RentalDto> getRentals(String member, String title);
} // interface end
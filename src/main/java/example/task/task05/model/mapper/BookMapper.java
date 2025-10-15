package example.task.task05.model.mapper;

import example.task.task05.model.dto.BookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    // 1. books 테이블에 price int 속성 추가
    int addField();
    // 2. books 테이블의 title 속성의 타입을 longtext로 수정
    int updateField();
    // 3. 평균 재고보다 많은 재고를 가진 도서 조회
    List<BookDto> getMoreStockBooks();
    // 4. 가장 많이 대출한 도서 조회
    BookDto getMostRentedBook();
} // interface end
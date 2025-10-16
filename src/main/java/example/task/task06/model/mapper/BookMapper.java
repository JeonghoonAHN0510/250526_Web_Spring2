package example.task.task06.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {
    // 1. 대출기록 상세 View 생성(books+rentals)
    int createBooksRentalsView();
    // 2. 평균보다 많은 재고를 가진 도서 View 생성
    int createMoreStockBooksView();
    // 3. 대출기록 상세 View 조회
    List<Map<String, Object>> getBooksRentalsView();
    // 4. 평균보다 많은 재고를 가진 도서 View 조회
    List<Map<String, Object>> getBooksMoreStockBooksView();
} // interface end
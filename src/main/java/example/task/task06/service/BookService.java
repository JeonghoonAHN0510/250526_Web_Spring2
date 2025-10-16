package example.task.task06.service;

import example.task.task06.model.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookMapper bookMapper;

    // 1. 대출기록 상세 View 생성(books+rentals)
    public int createBooksRentalsView(){
        System.out.println("BookService.createBooksRentalsView");
        return bookMapper.createBooksRentalsView();
    } // func end
    // 2. 평균보다 많은 재고를 가진 도서 View 생성
    public int createMoreStockBooksView(){
        return bookMapper.createMoreStockBooksView();
    } // func end
    // 3. 대출기록 상세 View 조회
    public List<Map<String, Object>> getBooksRentalsView(){
        return bookMapper.getBooksRentalsView();
    } // func end
    // 4. 평균보다 많은 재고를 가진 도서 View 조회
    public List<Map<String, Object>> getBooksMoreStockBooksView(){
        return bookMapper.getBooksMoreStockBooksView();
    } // func end
} // class end
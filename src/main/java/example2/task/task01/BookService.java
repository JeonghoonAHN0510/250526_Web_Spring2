package example2.task.task01;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    // 1. 도서 등록
    public BookEntity save(BookEntity bookEntity){
        return bookRepository.save(bookEntity);
    } // func end

    // 2. 도서 전체 조회
    public List<BookEntity> findAll(){
        return bookRepository.findAll();
    } // func end

    // 3. 특정 도서 수정
    public BookEntity put(BookEntity bookEntity){
        // 1. 수정하려는 엔티티 가져오기
        Optional<BookEntity> updateEntity = bookRepository.findById(bookEntity.getBid());
        // 2. 엔티티 확인하기
        if (updateEntity.isPresent()){
            BookEntity update = updateEntity.get();
            update.setBtitle(bookEntity.getBtitle());
            update.setBauthor(bookEntity.getBauthor());
            update.setBpublisher(bookEntity.getBpublisher());
            return update;
        } // if end
        return null;
    } // func end

    // 4. 특정 도서 삭제
    public boolean delete(int bId){
        bookRepository.deleteById(bId);
        return true;
    } // func end
} // class end
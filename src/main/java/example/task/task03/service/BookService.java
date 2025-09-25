package example.task.task03.service;

import example.task.task03.model.dto.BookDto;
import example.task.task03.model.dto.RentalDto;
import example.task.task03.model.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookMapper bookMapper;
    // 1. 도서대출
    public boolean rentBook(BookDto bookDto){
        // 1. 해당 책 재고를 1 감소시키고,
        boolean deBook = bookMapper.deBook(bookDto);
        if (!deBook) throw new RuntimeException("도서 재고 없음");
        // 2-1. 대출 기록을 확인한다.
        RentalDto rentalDto = new RentalDto();
        rentalDto.setBook_id(bookDto.getBookId());
        rentalDto.setMember(bookDto.getMember());
        int checkRentals = bookMapper.checkRentals(rentalDto);
        if (checkRentals > 0) throw new RuntimeException("동일 도서 대출 기록 존재");
        // 2-2. 대출 기록을 추가한다.
        boolean addRentals = bookMapper.addRentals(rentalDto);
        if (!addRentals) throw new RuntimeException("대출 기록 추가 실패");
        // 3. 최종적으로 true 반환
        return true;
    } // func end
    // 2. 도서반납
    public boolean returnBook(BookDto bookDto){
        // 1. 해당 책 재고를 1 증가시키고
        boolean inBook = bookMapper.inBook(bookDto);
        if(!inBook) throw new RuntimeException("책 정보 없음");
        // 2. 대출 기록을 수정한다.
        RentalDto rentalDto = new RentalDto();
        rentalDto.setBook_id(bookDto.getBookId());
        rentalDto.setMember(bookDto.getMember());
        boolean updateRentals = bookMapper.updateRentals(rentalDto);
        if (!updateRentals) throw new RuntimeException("대출 반납 실패");
        // 3. 최종적으로 true 반환
        return true;
    } // func end
} // class end
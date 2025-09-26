package example.task.task03.model.mapper;

import example.task.task03.model.dto.BookDto;
import example.task.task03.model.dto.RentalDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BookMapper {
    // 1-1. 도서 재고 감소
    @Update("update books " +
            "set stock = stock - 1 " +
            "where id = #{bookId} " +
            "and stock > 0")
    boolean deBook(BookDto bookDto);
    // 1-2. 대출 기록 추가
    @Insert("insert into rentals (book_id, member) " +
            "values (#{book_id}, #{member})")
    boolean addRentals(RentalDto rentalDto);
    // 1-3. 대출 기록 검사
    @Select("select count(*) " +
            "from rentals " +
            "where book_id = #{book_id} " +
            "and member = #{member} " +
            "and return_date is null")
    int checkRentals(RentalDto rentalDto);
    // 2-1. 도서 재고 증가
    @Update("update books " +
            "set stock = stock + 1 " +
            "where id = #{bookId}")
    boolean inBook(BookDto bookDto);
    // 2-2. 대출 기록 수정
    @Update("update rentals " +
            "set return_date = now() " +
            "where book_id = #{book_id} " +
            "and member = #{member} " +
            "and return_date is null")
    boolean updateRentals(RentalDto rentalDto);
} // interface end
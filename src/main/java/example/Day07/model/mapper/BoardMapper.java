package example.Day07.model.mapper;

import example.Day07.model.dto.BoardDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // 해당 인터페이스를 스프링 컨테이너에 빈 등록
public interface BoardMapper {
    // [1] 등록
    @Insert("insert into board( bcontent, bwriter ) values ( #{bcontent}, #{bwriter} )")
    boolean boardWrite( BoardDto boardDto );    // -> 추상메소드

    // [2] 전체조회
    @Select("select * from board")
    List<BoardDto> boardPrint();

    // [3] 개별조회
    @Select("select * from board where bno = #{bno}")
    BoardDto boardFind( int bno );

    // [4] 개별삭제
    @Delete("delete from board where bno = #{bno}")
    boolean boardDelete( int bno );

    // [5] 개별수정
    @Update("update board set bcontent = #{bcontent} where bno = #{bno}")
    boolean boardUpdate( BoardDto boardDto );
} // interface end
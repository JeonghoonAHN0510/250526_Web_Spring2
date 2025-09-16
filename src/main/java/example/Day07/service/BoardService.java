package example.Day07.service;

import example.Day07.model.dto.BoardDto;
import example.Day07.model.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    // 의존성 주입
    private final BoardMapper boardMapper;
    // [1] 등록
    public boolean boardWrite(BoardDto boardDto ){
        return boardMapper.boardWrite( boardDto );
    } // func end

    // [2] 전체조회
    public List<BoardDto> boardPrint(){
        return boardMapper.boardPrint();
    } // func end

    // [3] 개별조회
    public BoardDto boardFind( int bno ){
        return boardMapper.boardFind( bno );
    } // func end

    // [4] 개별삭제
    public boolean boardDelete( int bno ){
        return boardMapper.boardDelete( bno );
    } // func end

    // [5] 개별수정
    public boolean boardUpdate( BoardDto boardDto ){
        return boardMapper.boardUpdate( boardDto );
    } // func end
} // class end
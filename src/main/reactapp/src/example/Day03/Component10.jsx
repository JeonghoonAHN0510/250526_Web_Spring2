import { useEffect, useState } from "react";
import axios from 'axios';

// day07(boardService13)과 통신
export default function Component10( props ){
    // 1-1. bcontent useState
    const [ bcontent, setBcontent ] = useState('');
    // 1-2. bcontent input
    const bcontentInput = ( e ) => {
        setBcontent( e.target.value );
    } // func end

    // 2-1. bwriter useState
    const [ bwriter, setBwriter ] = useState('');
    // 2-2. bwriter input
    const bwriterInput = ( e ) => {
        setBwriter( e.target.value );
    } // func end

    // 3-1. axios post
    const boardWrite = async ( ) => {
        // 1. 입력받은 값을 객체로 만들기
        const obj = { bcontent, bwriter };
        // 2. axios로 통신하기
        const response = await axios.post( "http://localhost:8080/board", obj );
        // 3. 응답 확인하기
        console.log( response.status );
        console.log( response.data );
        // 4. 출력 함수 실행
        boardPrint();
    } // func end

    // 4-1. board useState
    const [ boards, setBoards ] = useState( [{bno : 1, bcontent : "test", bwriter : "test"}] );
    // 4-2. axios get -> 게시물이 등록되었을 때, 실행
    const boardPrint = async ( ) => {
        // 1. axios로 통신하기
        const response = await axios.get( "http://localhost:8080/board" );
        // 2. useState를 이용한 재렌더링
        setBoards( response.data );
    } // func end

    // * useEffect를 이용한 최초 1번 실행하기
    // useEffect를 사용하지않고 그냥 실행하게 된다면, 무한루프에 빠지게된다.
    useEffect( ( ) => { boardPrint() }, [ ] );

    return(
        <>
        <h3> Spring과 통신 </h3>
        <div>
            <div>
                <input type="text" placeholder="내용" value={bcontent} onChange={bcontentInput} />
                <input type="text" placeholder="작성자" value={bwriter} onChange={bwriterInput} />
            </div>
            <div>
                <button onClick={boardWrite}> 등록 </button>
            </div>
        </div>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>내용</th>
                        <th>작성자</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        boards.map( (board) => {
                            return(
                                <>
                                <tr>
                                    <td>{board.bno}</td>
                                    <td>{board.bcontent}</td>
                                    <td>{board.bwriter}</td>
                                    <td>
                                        <button>수정</button>
                                        <button>삭제</button>
                                    </td>
                                </tr>
                                </>
                            ) // return end
                        }) // map end
                    }
                </tbody>
            </table>
        </div>
        </>
    ) // return end
} // func end
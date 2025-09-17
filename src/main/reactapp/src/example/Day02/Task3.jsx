import { useState } from "react";

export default function Task3( props ){
    // [1-1] input을 관리하는 useState
    const [ product, setProduct ] = useState('');
    // [1-2] input 함수
    const onInput = ( e ) => {
        // e : onChange의 이벤트 결과 정보가 담긴 객체
        // e.target : onChange가 발생한 마크업 정보
        setProduct( e.target.value );
    } // func end

    // [2-1] 수량을 관리하는 useState
    const [ amount, setAmount ] = useState( 0 );
    // [2-2] 증가 함수
    const amountPlus = ( ) => {
        setAmount(amount + 1);
    } // func end
    // [2-3] 감소 함수
    const amountMinus = ( ) => {
        setAmount(amount - 1);
    } // func end
    return(
        <>
        <p>제품명 : <input value={ product } onChange={ onInput } /></p>
        <p>현재 수량 : <strong>{ amount }</strong></p>
        <p><button onClick={amountMinus}>감소</button><button onClick={amountPlus}>증가</button></p>
        </>
    ) // return end
} // func end
import { useState } from "react"

export default function Component6( props ){
    // 1. React 상태관리 라이브러리 사용 - useState
    const state = useState( "유재석" );
    // state[0] : 초기값, state[1] : 재렌더링 함수
    console.log( state );
    console.log( state[0] );    // useState 초기값
    console.log( state[1] );    // 값이 변경되면 실행되는 함수(재렌더링)

    const onChange = ( ) => {
        state[0] = "강호동";
        console.log( state[0] );
        // 함수이기 때문에, 매개변수를 넣어서 값을 변경하고 재렌더링한다.
        state[1]( "강호동" );
    } // func end

    return(
        <>
        <h3> useState 상태관리 </h3>
        <h4> useState의 초기값 : { state[0] } </h4>
        <h4> useState의 값 변경 <button onClick={ onChange }> 변경 </button></h4>
        </>
    ) // return end
} // func end
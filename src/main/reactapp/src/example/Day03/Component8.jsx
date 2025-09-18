import { useEffect, useState } from "react"

export default function Component8( props ){
    // React Hook : 1. useState 2. useEffect ···
    // [ useEffect ] : 특정한 시점(생명 주기)에 함수를 실행
    // 시점 : 컴포넌트 탄생(실행), 컴포넌트 재실행, 컴포넌트 죽음(종료)
    // useEffect( 함수명 ) or useEffect( ( ) => { }, [ ] )
    // [ ] : 의존성 배열 -> 상태변수를 대입하여 상태변수가 재렌더링되면, useEffect를 실행한다.

    // 1. 컴포넌트 실행될 때마다 실행
    useEffect( ( ) => { console.log('[1] 컴포넌트 실행마다') } );

    // 2. 컴포넌트 최소실행 + 특정 상태의 변경
    const [ count1, setCount1 ] = useState( 0 );
    const [ count2, setCount2 ] = useState( 0 );
    useEffect( ( ) => { console.log('[2] 컴포넌트 최초실행 + 특정 상태변경') }, [ count1 ] );
    // count의 상태가 변경되면(setCount), useEffect를 실행한다.

    // 3. 컴포넌트 최초실행에만
    useEffect( ( ) => { console.log('[3] 컴포넌트 최초실행') }, [ ] );

    return(
        <>
        <h3> useEffect 예제1 </h3>
        <h4> {count1} </h4>
        <h4> {count2} </h4>
        <button onClick={ (e) => { setCount1(count1 + 1) }}> 버튼 </button>
        <button onClick={ (e) => { setCount2(count2 + 1) }}> 버튼 </button>
        </>
    ) // return end
} // func end
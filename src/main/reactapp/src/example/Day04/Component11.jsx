import { useEffect, useRef, useState } from "react"

export default function Component11( props ){
    // [1] useRef(초기값)
    // - 렌더링을 하지않고, 데이터를 참조하는 훅 vs useState
    const inputRef = useRef( null );
    const 등록함수 = ( ) => {
        console.log( inputRef );                // 현재 참조중인 객체 정보, { current : 참조값 }
        console.log( inputRef.current );        // 현재 참조중인 참조값, <input>
        inputRef.current.focus();               // .focus() : 마우스 커서 이동
        console.log( inputRef.current.value );  // 현재 참조중인 마크업의 값
    } // func end

    // [2] useState vs useRef
    // -> 단순 입력이라면, useState보다 useRef가 간단하다.
    // -> 하지만 화면에 입력받은 값을 노출시킬 수 없다.(= 렌더링을 하지않기 때문에)
    const [ count, setCount ] = useState(0);

    // 1. useRef(초기값)
    const countRef = useRef( count );
    // 2. count가 변경될 때마다 실행되는 훅, useEffect
    useEffect( ( ) => {
        countRef.current = count;
    }, [ count ] );
    
    // [3] formRef
    const formRef = useRef();
    const 전송함수 = ( ) => {
        console.log( formRef.current );         // 폼 내용물을 한 번에 가져와서 자바에게 전송하기
        console.log( formRef.current.elements['text1Data'].value );
        console.log( formRef.current.querySelector('.text1Data').value );
        console.log( document.querySelector('.text1Data').value );
        // document는 전체를 가져오기에 formRef가 속도가 더 빠르다.
    } // func end


    return(
        <>
        <h3> useRef 예제1 : 입력할 때 사용 </h3>
        <input ref={inputRef}/>
        <button onClick={등록함수}>등록</button>

        <h3> useRef 예제2 : 이전값을 기억할 때 사용 </h3>
        <p> 현재 count : {count} </p>
        <p> 이전 count : {countRef.current} </p>
        <button onClick={ (e) => {setCount(count + 1)}}>증가</button>

        <h3> useRef 예제3 : 입력 폼 </h3>
        <form ref={formRef}>
            <input name="text1Data" id="text1Data" class="text1Data"/>
            <select name="selectData">
                <option>바나나</option>
            </select>
            <textarea name="text2Data"></textarea>
            <button type="button" onClick={전송함수}>폼 전송</button>
        </form>
        </>
    ) // return end
} // func end
import { useState } from "react";

export default function Component7( props ){
    // 1. useState 변수 선언 - 구조 분해 할당
    // const [ 변수명, set변수명 ] = useState( 초기값 );
    const [ count, setCount ] = useState( 0 );

    // 2. count 변경 함수
    const countAdd = ( ) => {
        // set변수명 -> 만일 값이 변경되었을 때, 해당 컴포넌트 재렌더링
        // 주의할 점 : 값이 변경? -> 주소값이 변경되는 것
        // useState를 사용하려면, 주소값이 변경되어야한다.
        const newValue = count + 1;
        // Hook : 특정한 기능을 실행하면, 다른 기능들도 실행되는 것
        setCount( newValue );
    } // func end

    const [ array, setArray ] = useState( [ '수박' ] );
    const arrayAdd = ( ) => {
        array.push("사과"); // [ ] -> [ "사과" ]
        // setArray( array ); -> 불가능
        setArray( [ ...array ] );
        // 스프레드 연산자를 통해서 객체 복사 진행
    } // func end

    // input value 가져오기
    const [ data, setData ] = useState( '' );
    const dataAdd = ( event ) => {
        // onChange가 실행되었을 때, 이벤트 결과가 함수의 매개변수로 전달된다.
        console.log( event );
        console.log( event.target );        // onChange가 발동한 마크업 (vs document.querySelector() )
        console.log( event.target.value );  // value 가져오기
        // 입력받은 값을 useState로 변경하기
        setData( event.target.value );
    } // func end

    return(
        <>
        <h3> useState 예제1 </h3>
        <h4> { count } </h4>
        <button onClick={ countAdd }> count 증가 </button>
        <h3> useState 예제2 </h3>
        <h4> { array } </h4>
        <button onClick={ arrayAdd }> 과일 추가 </button>
        <h3> useState 예제3 </h3>
        <h4> { data } </h4>
        <input value={ data } onChange={ dataAdd }/>
        <input value={ data } onChange={ ( event ) => { setData( event.target.value ) } }/>
        </>
    ) // return end
} // func end

/*
- 자료의 주소값 변경 기준
    1 -> 2 : 주소 변경
    'a' -> 'b' : 주소 변경
    { name : "유재석" } -> { name : "유재석", age : 40 } : 주소 변경 X

- 데이터를 복사하여 새로운 객체(주소)를 만든다 -> 객체 복사
*/
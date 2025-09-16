/*
- 백틱을 이용한 js 코드 연결 가능 -> but, React에서는 불가능
-> React(JSX)에서는 { }를 통해 코드 연결
- return의 코드가 길어지면, 소괄호 + <>를 통해 감싸줘야한다.
*/

// [1] 컴포넌트 생성
const Component3 = ( props ) => {
    // <-------- JS 코드 Start
    let name = "유재석";
    // --------> JS 코드 End : return 전까지
    // return 이후부터 JSX 문법 적용
    return(
        <>
            <div>
                { name } 입니다.
            </div>
            <div>
                { 10 + 13 }
            </div>
            { /* 다른 컴포넌트 불러오기 + 자료 전달(props) */ }
            <SubCom1 key1={name} key2="40"/>
        </> 
    ) // return end
} // func end

// [2] 컴포넌트 내보내기
export default Component3;

// [3] 컴포넌트 생성 - props 예제
const SubCom1 = ( props ) => {

    const obj = { name : "강호동", age : 50 };
    console.log( obj );

    // 1. props 확인
    console.log( props );
    // 2. props 구조 분해 -> 객체/배열 구조를 쪼개는 방식
    const { key1, key2 } = props;
    console.log( key1 );
    console.log( key2 );

    // <-------- JSX 문법 적용
    return(
        <>
            <h4> { obj.name }님의 나이는 { obj.age }입니다. </h4>
            <h4> { props.key1 }님의 나이는 { props.key2 }입니다. </h4>
            <h4> { key1 }님의 나이는 { key2 }입니다. </h4>
        </>
    ) // return end
} // func end
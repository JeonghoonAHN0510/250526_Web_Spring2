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
        </> 
    ) // return end
} // func end

// [2] 컴포넌트 내보내기
export default Component3;
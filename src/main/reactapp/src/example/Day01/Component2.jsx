/*
- 함수 안에서 다른 함수 호출이 가능하다.
- 즉, 컴포넌트 안에서 다른 컴포넌트 호출이 가능하다.
- props : < 컴포넌트 속성=값 />
- JSP(HTML+JAVA), JSX(HTML+JS)
*/

// ====================== 메인페이지 ======================
// [1] 컴포넌트 생성
function Component2( props ){
    const name = "유재석";

    // return 뒤로 JSX 문법을 사용할 수 있다.
    return <div> <Header/> 메인페이지 <Footer/> </div>
} // func end
// [2] default 컴포넌트 내보내기 -> 1개만 가능하다 -> export named는 여러개 가능
export default Component2;

// ====================== 헤더 ======================
function Header( props ){
    return <div> 헤더 메뉴 </div>
} // func end

// ====================== 푸터 ======================
function Footer( props ){
    return <div> 푸터 메뉴 </div>
} // func end
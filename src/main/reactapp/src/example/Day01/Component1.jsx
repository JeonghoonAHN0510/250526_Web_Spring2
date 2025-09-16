/*
[ 컴포넌트란? ]
 - 출력할 화면(HTML)을 최소로 쪼갠 모듈
 - 코드를 최적화하여 쪼개는 것(= 화면을 최적화하기위해 쪼개는 것)
[ 컴포넌트 만드는 방법 ]
1. 지정한 폴더에 .jsx 확장자로 파일을 생성한다.(영문 카멜표기법 권장)
2. 함수형 컴포넌트 선언 -> js에서의 함수 선언과 동일
3. .jsx 내 default 컴포넌트 명은 주로 파일명과 동일하게 사용한다.
    function 컴포넌트명( props ){ }
4. 컴포넌트 내에서 return 뒤로 출력할 HTML을 작성한다.
5. 다른 .jsx에서 해당 컴포넌트를 import할 수 있게 export한다.
*/
// [1] 컴포넌트 생성
function Component1( props ){

    return <h1> 내가 만든 컴포넌트 </h1>
} // func end

// [2] default 컴포넌트 내보내기
export default Component1;
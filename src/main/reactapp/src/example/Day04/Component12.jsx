import { BrowserRouter, Link, Route, Routes, useNavigate, useParams, useSearchParams } from 'react-router-dom';

// [1] 메인페이지 컴포넌트
function Home( props ){
    return(
        <>
        <h3>메인페이지</h3>
        </>
    ) // return end
} // func end

// [2] 소개페이지 컴포넌트
function About( props ){
    return(
        <>
        <h3>소개페이지</h3>
        </>
    ) // return end
} // func end

// [3] 마이페이지 컴포넌트 : useSearchParams -> 쿼리스트링
function Mypage( props ){
    // React 쿼리스트링
    const [ searchParams ] = useSearchParams();
    const name = searchParams.get('name');
    const age = searchParams.get('age');

    return(
        <>
        <h3> 마이페이지 </h3>
        <p> 이름 : {name} / 나이 : {age} </p>
        </>
    ) // return end
} // func end

// [4] 제품페이지 : useParams -> path 매개변수
function Product( props ){
    // React path -> /product/코카콜라/1000
    // const { 매개변수1, 매개변수2 } = useParams();
    const { name, price } = useParams();

    return(
        <>
        <h3> 제품페이지 </h3>
        <p> 제품명 : {name} / 가격 : {price} </p>
        </>
    ) // return end
} // func end

// [5] 404페이지
function Page404( props ){
    // 1. useNavigate 반환값 저장
    const navigate = useNavigate();

    const 이동함수 = ( ) => {
        // HTML 페이지전환 : <a>, location.href="URL"
        // React 페이지전환 : <Link>, navigate("URL")
        // 2. 반환값으로 navigate 사용하기
        navigate("/home");
    } // func end

    return(
        <>
        <h3> 존재하지 않는 페이지입니다. </h3>
        <a href="/home"> 메인페이지(HTML) </a> <br />
        <Link to="/home"> 메인페이지(React) </Link> <br />
        <button onClick={이동함수}> 홈으로 </button>
        </>
    ) // return end
} // func end

// 라우터 : 하나의 컴포넌트가 여러 컴포넌트를 연결해주는 구조
// 1. 라우터 라이브러리 설치 : npm i react-router-dom
export default function Component12( props ){

    return(
        <>
        <BrowserRouter>
            <ul>
                <a href="/home">메인페이지(HTML)</a>  <br />
                {/* 가상 URL은 <Link to="가상URL">을 사용 */}
                <Link to="/home"> 메인페이지(React) </Link>   <br/>
                <Link to="/about"> 소개페이지(React) </Link> <br/>
                <Link to="/mypage"> 마이페이지(React/쿼리스트링X) </Link> <br/>
                <Link to="/mypage?name=유재석&age=40"> 마이페이지(React/쿼리스트링O) </Link> <br/>
                <Link to="/product"> 제품페이지(React/path X) </Link> <br/>
                <Link to="/product/코카콜라/1000"> 제품페이지(React/path O) </Link>
            </ul>
            <Routes>
                {/* 가상의 URL을 정의하고, 정의한 URL과 매핑할 컴포넌트 정의 */}
                {/* <Route path="가상URL" element={<컴포넌트/>} */}
                <Route path='/home' element={<Home/>}/>
                <Route path='/about' element={<About/>}/>
                <Route path='/mypage' element={<Mypage/>}/>
                <Route path='/product/:name/:price' element={<Product/>}/>
                {/* 만약에 존재하지 않는 가상 URL을 요청하면 404 컴포넌트 생성 */}
                <Route path='*' element={<Page404/>}/>
            </Routes>
        </BrowserRouter>
        </>
    ) // return end
} // func end
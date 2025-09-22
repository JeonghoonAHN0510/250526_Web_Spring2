import { BrowserRouter, Link, Route, Routes, useSearchParams } from 'react-router-dom';

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

// [3] 마이페이지 컴포넌트 : useSearchParams
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

// 라우터 : 하나의 컴포넌트가 여러 컴포넌트를 연결해주는 구조
// 1. 라우터 라이브러리 설치 : npm i react-router-dom
export default function Component12( props ){

    return(
        <>
        <BrowserRouter>
            <ul>
                <a href="/">메인페이지(HTML)</a>  <br />
                {/* 가상 URL은 <Link to="가상URL">을 사용 */}
                <Link to="/home"> 메인페이지(React) </Link>   <br/>
                <Link to="/about"> 소개페이지(React) </Link> <br/>
                <Link to="/mypage"> 마이페이지(React/쿼리스트링X) </Link> <br/>
                <Link to="/mypage?name=유재석&age=40"> 마이페이지(React/쿼리스트링O) </Link>
            </ul>
            <Routes>
                {/* 가상의 URL을 정의하고, 정의한 URL과 매핑할 컴포넌트 정의 */}
                {/* <Route path="가상URL" element={<컴포넌트/>} */}
                <Route path='/home' element={<Home/>}/>
                <Route path='/about' element={<About/>}/>
                <Route path='/mypage' element={<Mypage/>}/>
            </Routes>
        </BrowserRouter>
        </>
    ) // return end
} // func end
import { useRef } from "react";
import { BrowserRouter, Link, Route, Routes, useNavigate } from "react-router-dom";

import './Task6.css';

// [1] 메인페이지 컴포넌트
function Home( props ){
    return(
        <>
        <h3>홈 페이지</h3>
        <p>좌측 메뉴에서 회원가입 또는 로그인으로 이동해보세요.</p>
        </>
    ) // return end
} // func end

// [2] 회원가입 컴포넌트
function Signup( props ){
    // [2-1] Input을 참조하는 useRef
    const idInput = useRef(null);
    const pwdInput = useRef(null);

    // [2-2] 라우터 전용 페이지 전환 함수
    const navigate = useNavigate();

    const postSignup = async ( ) => {
        console.log( idInput.current.value );
        console.log( pwdInput.current.value );
        const obj = { mid : idInput.current.value, mpwd : pwdInput.current.value };
        console.log( obj );
        // axios 진행
        alert('[회원가입 성공]');
        navigate('/login');
    } // func end

    return(
        <>
        <h3>회원가입 페이지</h3>
        <input placeholder="아이디" ref={idInput}/>      <br />
        <input placeholder="비밀번호" ref={pwdInput}/>   <br />
        <button onClick={postSignup}>회원가입</button>
        </>
    ) // return end
} // func end

// [3] 로그인 컴포넌트
function Login( props ){
    // [2-1] form을 참조하는 useRef
    const loginForm = useRef(null);

    // [2-2] 라우터 전용 페이지 전환 함수
    const navigate = useNavigate();

    const postLogin = async ( ) => {
        console.log( loginForm.current );
        const mid = loginForm.current.elements['mid'].value;    console.log( mid );
        const mpwd = loginForm.current.elements['mpwd'].value;  console.log( mpwd );
        const obj = { mid, mpwd };                              console.log( obj );
        // axios 진행
        alert('[로그인 성공]');
        navigate('/home');
    } // func end

    return(
        <>
        <h3>로그인 페이지</h3>
        <form ref={loginForm}>
            <input placeholder="아이디" name="mid"/>      <br />
            <input placeholder="비밀번호" name="mpwd"/>   <br />
            <button onClick={postLogin} type="button">로그인</button>
        </form>
        </>
    ) // return end
} // func end

// [*] default 컴포넌트 + Router
export default function Task6( props ){
    return(
        <>
        <BrowserRouter>
            <div class="container">
                <ul>
                    <li><Link to="/home">홈</Link></li>
                    <li><Link to="/signup">회원가입</Link></li>
                    <li><Link to="/login">로그인</Link></li>
                </ul>
                <div> {/* 라우터 렌더링 되는 곳 */}
                    <Routes>
                        <Route path="/home" element={<Home/>}/>
                        <Route path="/signup" element={<Signup/>}/>
                        <Route path="/login" element={<Login/>}/>
                    </Routes>
                </div>
            </div>
        </BrowserRouter>
        </>
    ) // return end
} // func end
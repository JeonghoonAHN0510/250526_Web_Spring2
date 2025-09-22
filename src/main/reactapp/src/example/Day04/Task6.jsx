import { useRef } from "react";
import { BrowserRouter, Link, Route, Routes } from "react-router-dom";

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
    const idInput = useRef(null);
    const pwdInput = useRef(null);

    const postSignup = async ( ) => {
        console.log( idInput.current.value );
        console.log( pwdInput.current.value );
        const obj = { mid : idInput.current.value, mpwd : pwdInput.current.value };
        console.log( obj );
        // axios 진행

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
    const idInput = useRef(null);
    const pwdInput = useRef(null);

    const postLogin = async ( ) => {
        console.log( idInput.current.value );
        console.log( pwdInput.current.value );
        const obj = { mid : idInput.current.value, mpwd : pwdInput.current.value };
        console.log( obj );
        // axios 진행
    } // func end

    return(
        <>
        <h3>로그인 페이지</h3>
        <input placeholder="아이디" ref={idInput}/>      <br />
        <input placeholder="비밀번호" ref={pwdInput}/>   <br />
        <button onClick={postLogin}>로그인</button>
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
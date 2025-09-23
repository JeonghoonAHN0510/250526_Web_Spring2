import { useRef } from "react"
import { useDispatch, useSelector } from "react-redux";
import { login } from '../store/userSlice.jsx'
import { useNavigate } from "react-router-dom";

export default function LoginPage( props ){
    // =============== useRef ===============
    // 입력을 위한 useRef
    const idRef = useRef(null);
    const pwdRef = useRef(null);
    // ================ Redux ================
    // 상태변경을 위한 dispatch
    const { isAuthenticated } = useSelector( (state) => state.user );
    const dispatch = useDispatch();
    // ================ Route ================
    // 페이지 전환을 위한 useNavigate
    const navigate = useNavigate();

    // [1] 로그인 처리 함수
    const postLogin = async ( ) => {
        // 1. Input Value Object
        const mid = idRef.current.value;
        const mpwd = pwdRef.current.value;
        const obj = { mid, name : '유재석' };
        // 2. Axios -> axios 성공했다는 가정
        console.log( obj );
        // 3. Result
        alert('[로그인 성공]');      // 성공 안내
        dispatch( login(obj) );     // login()을 요청하여, '로그인 여부' 상태 변경
        navigate('/');              // useNavigate를 이용하여 홈으로 이동
    } // func end

    return(
        <>
        <h3> LoginPage </h3>
        <input placeholder="아이디" ref={idRef}/>
        <input placeholder="비밀번호" ref={pwdRef}/>
        <button onClick={postLogin}>로그인</button>
        </>
    ) // return end
} // func end
import axios from "axios";
import { useState } from "react";

export default function Login(props) {
    // 1. 입력받은 아이디/패스워드 상태
    const [uid, SetUid] = useState("");
    const [upwd, SetUpwd] = useState("");
    // 2. 로그인 요청
    const postLogin = async () => {
        try {
            const obj = { uid, upwd };
            const option = { withCredentials: true };

            const response = await axios.post("http://localhost:8080/api/user/login", obj, option);
            if (response.data != '') {
                alert('로그인 성공');
                location.href = '/';      // 페이지 전체 렌더링
            } else {
                alert('로그인 실패');
            }; // if end
        } catch (error) {
            console.log(error);
        }; // try-catch end
    }; // func end

    return (
        <>
            <h3>로그인 페이지</h3>
            <form>
                아이디 : <input value={uid} onChange={(input) => SetUid(input.target.value)} /> <br />
                비밀번호 : <input type="password" value={upwd} onChange={(input) => SetUpwd(input.target.value)} /> <br />
                <button type="button" onClick={postLogin}>로그인</button> <br />
                <a href="http://localhost:8080/oauth2/authorization/google">구글 로그인</a> <br />
                <a href="http://localhost:8080/oauth2/authorization/kakao">카카오 로그인</a>
            </form>
        </>
    ); // return end
}; // func end
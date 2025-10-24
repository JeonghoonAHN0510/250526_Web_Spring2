import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function Header(props) {
    // [1] 로그인된 유저 정보 저장
    const [user, SetUser] = useState(null);
    // [2] 최초로 컴포넌트 실행 시, 유저 정보 요청하기
    const getMyInfo = async () => {
        try {
            const option = { withCredentials: true };
            const response = await axios.get("http://localhost:8080/api/user/info", option);
            SetUser(response.data);
        } catch (error) {
            SetUser(null);
        }; // try-catch end
    }; // func end
    useEffect(() => {
        getMyInfo();
    }, []); // useEffect end
    // [3] 로그아웃 요청하기
    const getLogout = async () => {
        try {
            const option = { withCredentials: true };
            const response = await axios.get("http://localhost:8080/api/user/logout", option);
            alert("로그아웃 되었습니다.");
            location.href = "/login";       // 로그아웃은 서버사이드렌더링
        } catch (error) {
            console.log(error);
        }; // try-catch end
    }; // func end

    return (
        <>
            <div>
                <nav>
                    {user ?
                        <>  {/* 로그인 */}
                            <span to="/login"> 유재석님 </span>
                            <button to="/login"> 로그아웃 </button>
                            <Link to="/user/info"> 마이페이지 </Link>
                            {/* 로그인 상태이면서 관리자면 */}
                            {user.urole == "ADMIN" ?
                                <>
                                    <Link to="/admin/dashboard"> 관리자페이지 </Link>
                                </> : <></>}
                        </>
                        :
                        <>  {/* 비로그인 */}
                            <Link to="/"> HOME </Link>
                            <Link to="/login"> 로그인 </Link>
                            <Link to="/signup"> 회원가입 </Link>
                        </>
                    }
                </nav>
            </div>
        </>
    ); // return end
}; // func end
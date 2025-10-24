import axios from 'axios';
import { useEffect, useState } from 'react';
import { Navigate, Outlet } from 'react-router-dom';

/**
 * 서버로부터 권한을 확인하여 해당 권한에 따른 컴포넌트 제약 설정
 * @param {*} props 
 * @returns 
 */

export default function RoleRoute(props) {
    // props : 상위 컴포넌트로부터 해당 컴포넌트에게 전달해준 속성들
    console.log(props);
    // useState : 현재 컴포넌트 내 상태변수 + 렌더링 지원
    const [auth, SetAuth] = useState({
        isAuth: null,
        urole: null
    }); // useState end
    // [1] 서버로부터 권한 요청
    const checkAuth = async () => {
        try {
            const option = { withCredentials: true };
            const response = await axios.get("http://localhost:8080/api/user/check", option);
            SetAuth(response.data);
            console.log(response.data);
        } catch (error) {
            // 오류가 발생하면, false null로 저장
            SetAuth({
                isAuth: false,
                urole: null
            }); // SetAuth end
        }; // try-catch end
    }; // func end
    // [2] 최초 렌더링 1번 권한 검증
    // useEffect : 컴포넌트의 생명주기에 따른 특정 작업 실행
    useEffect(() => {
        checkAuth();
    }, []); // useEffect end
    // [3] 만약에 서버로부터 권한을 못받았다면, 안내문구 출력
    if (auth.isAuth == null) return <div> 권한 확인 중... </div>;
    // [4] 만약에 로그인(쿠키/토큰 X)을 안했다면, 로그인 페이지로 이동
    if (auth.isAuth == false) return <Navigate to="/login" />;
    // [5] 상위 컴포넌트(App.jsx)로부터 전달받은 권한 중에 포함되지 않으면, 권한 없음 표시
    if (!props.roles.includes(auth.urole)) return <Navigate to="/fobidden" />;
    // [6] 모든 조건문을 통과했다면, 자식 컴포넌트 렌더링하기(<Outlet/>)
    return (
        <>
            <Outlet />
        </>
    ); // return end
}; // func end
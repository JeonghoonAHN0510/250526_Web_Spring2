import { useDispatch, useSelector } from 'react-redux';
import { login, logout } from './UserSlice.jsx';

export default function Comoponent13( props ){
    // [1] store에 저장된 상태 가져오기
    // const { 상태변수명 } = useSelector( (state)=>state.상태명 );
    const { isAuthenticated } = useSelector( (state) => state.user );
    console.log( isAuthenticated );

    // [2] action을 이용한 전역변수 상태 변경
    const dispatch = useDispatch();

    // [3] 로그인 처리
    const loginHandle = ( ) => {
        // 로그인 axios 성공했다는 가정

        // dispatch를 이용한 login action 요청
        // dispatch( action() );
        dispatch( login() );
    } // func end

    // [4] 로그아웃 처리
    const logoutHandle = ( ) => {
        // 로그아웃 axios 성공했다는 가정

        // dispatch를 이용한 logout action 요청
        dispatch( logout() );
    } // func end

    return(
        <>
        <h3> Redux 예제 </h3>
        { isAuthenticated ?
            <div>
                <p> 안녕하세요, 회원님! </p>
                <button onClick={logoutHandle}>로그아웃</button>
            </div>
            :
            <div>
                <p> 비로그인 상태입니다. </p>
                <button onClick={loginHandle}>로그인</button>
            </div>
        }
        </>
    ) // return end
} // func end
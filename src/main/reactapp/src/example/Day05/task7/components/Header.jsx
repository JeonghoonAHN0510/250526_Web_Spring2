import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { logout } from '../store/userSlice.jsx'

export default function Header( props ){
    // ================ Redux ================
    // Store에 저장된 상태를 가져오기 위한 useSelector
    const { isAuthenticated } = useSelector( (state) => state.user );
    // 상태변경을 위한 dispatch
    const dispatch = useDispatch();
    // ================ Route ================
    // 페이지 전환을 위한 useNavigate
    const navigate = useNavigate();

    const logoutHandle = async ( ) => {
        
        dispatch( logout() );       // logout()을 요청하여, '로그인 여부' 상태 변경
        navigate('/');              // useNavigate를 이용하여 홈으로 이동
    } // func end

    return(
        <>
        <div>
            <ul>
                <li><Link to="/">홈</Link></li>
                { isAuthenticated ?
                    <>
                    <li><Link to="/profile">프로필</Link></li>
                    <li><Link onClick={logoutHandle}>로그아웃</Link></li>
                    </>
                    :
                    <>
                    <li><Link to="/login">로그인</Link></li>
                    </>
                }
            </ul>
        </div>
        </>
    ) // return end
} // func end
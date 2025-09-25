import { Link } from "react-router-dom";

export default function Header( props ){
    return(
        <>
        <div>
            <ul>
                <li><Link to="/">홈</Link></li>
                <li><Link to="/menu">제품목록</Link></li>
                <li><Link to="/cart">장바구니</Link></li>
            </ul>
        </div>
        </>
    ) // return end
} // func end
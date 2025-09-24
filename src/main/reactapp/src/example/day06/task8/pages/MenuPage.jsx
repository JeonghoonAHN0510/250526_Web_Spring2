import { useDispatch, useSelector } from "react-redux";
import { addCart } from "../store/cartSlice";



export default function MenuPage( props ){
    // 전역변수의 상태변경을 위한 useDispatch
    const { menu } = useSelector( (state) => state.cart );
    const dispatch = useDispatch();


    // 장바구니 추가
    const addCartBtn = ( id ) => {
        alert('[장바구니 담기 완료!]')
        dispatch( addCart(id) );
    } // func end

    return(
        <>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>이름</th>
                        <th>가격</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        menu.map( (item) => {
                            return(
                                <>
                                <tr>
                                    <td>{item.name}</td>
                                    <td>{item.price}</td>
                                    <td><button onClick={()=>{addCartBtn(item.id)}}>담기</button></td>
                                </tr>
                                </>
                            )
                        })
                    }
                </tbody>
            </table>
        </div>
        </>
    ) // return end
} // func end
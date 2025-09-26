import { useSelector } from "react-redux";
import Table from '@mui/joy/Table';

export default function CartPage( props ){
    // 전역변수 가져오기
    const { cartList } = useSelector( (state) => state.cart );
    
    let totalAmount = 0;
    let totalPrice = 0;

    return(
        <>
        <div>
            <Table borderAxis="both">
            <caption>장바구니</caption>
            <thead>
                <tr>
                    <th style={{ width: '40%' }}>제품명</th>
                    <th>수량</th>
                    <th>금액</th>
                </tr>
            </thead>
            <tbody>
                {
                    cartList.map( (item) => {
                        if ( item.amount > 0 ){
                            totalAmount += item.amount;
                            totalPrice += item.price;
                            let price = item.amount * item.price;
                        return(
                            <>
                            <tr>
                                <td>{item.name}</td>
                                <td>{item.amount}개</td>
                                <td>{price.toLocaleString()}원</td>
                            </tr>
                            </>
                        )}
                    })
                }
            </tbody>
            <tfoot>
                <tr>
                <th scope="row">Totals</th>
                <td>{totalAmount}개</td>
                <td>{totalPrice.toLocaleString()}원</td>
                </tr>
            </tfoot>
            </Table>
        </div>
        </>
    ) // return end
} // func end

/*
            <table>
                <thead>
                    <tr>

                    </tr>
                </thead>
                <tbody>
                    {

                    }
                    <tr>
                        <td>총 합계</td>
                        <td>{totalAmount}개</td>
                        <td>{totalPrice.toLocaleString()}원</td>
                    </tr>
                </tbody>
            </table>
*/
import { useSelector } from "react-redux"

export default function CartPage( props ){
    // 전역변수 가져오기
    const { menu } = useSelector( (state) => state.cart );
    
    let totalAmount = 0;
    let totalPrice = 0;


    return(
        <>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>제품명</th>
                        <th>수량</th>
                        <th>금액</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        menu.map( (item) => {
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
                            )
                        })
                    }
                    <tr>
                        <td>총 합계</td>
                        <td>{totalAmount}개</td>
                        <td>{totalPrice.toLocaleString()}원</td>
                    </tr>
                </tbody>
            </table>
        </div>
        </>
    ) // return end
} // func end
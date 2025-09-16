const products = [
    { title: "무선 키보드", price: 45000, inStock: true },
    { title: "게이밍 마우스", price: 32000, inStock: false },
    { title: "27인치 모니터", price: 280000, inStock: true }
];

export default function Task2( props ){
    return(
        <>
        <div>
            {/* 하위 컴포넌트 호출 + props 전달 */}
            <InfoCard data={products[0]}/>
            <InfoCard data={products[1]}/>
            <InfoCard data={products[2]}/>
        </div>
        </>
    ) // return end
} // func end

function InfoCard( props ){
    return(
        <>
        <ul>
            <li>{props.data.title}</li>
            <li>가격 : {props.data.price.toLocaleString()}</li>
            <li>{props.data.inStock ? '재고 있음':'재고 없음'}</li>
        </ul>
        </>
    ) // return end
} // func end
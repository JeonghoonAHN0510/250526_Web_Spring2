const products = [
    { title: "무선 키보드", price: 45000, inStock: true },
    { title: "게이밍 마우스", price: 32000, inStock: false },
    { title: "27인치 모니터", price: 280000, inStock: true }
];

// [1] CSS 파일 불러오기 - import 'CSS파일경로'
import './Task2.css'


export default function Task2( props ){
    return(
        <>
        <div class="products">
            {/* 하위 컴포넌트 호출 + props 전달 */}
            <InfoCard data={products[0]}/>
            <InfoCard data={products[1]}/>
            <InfoCard data={products[2]}/>
        </div>
        </>
    ) // return end
} // func end

function InfoCard( props ){
    // 매개변수의 위치에서 구조 분해 할당이 가능하다!
    // props.data = { title, price, inStock }
    const { title, price, inStock } = props.data;
    return(
        <>
        <ul>
            <li>{ title }</li>
            <li>가격 : { price.toLocaleString() }</li>
            <li>{ inStock ? '재고 있음':'재고 없음' }</li>
        </ul>
        </>
    ) // return end
} // func end
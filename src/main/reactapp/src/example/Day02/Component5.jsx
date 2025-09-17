export default function Component5( props ){
    const items = [ "사과", "바나나", "딸기" ];

    // forEach로 반복 및 반환해보기
    const forEachReturn = items.forEach( (item) => {
        console.log( item );
    })
    console.log( forEachReturn );   // 반환 불가능

    // map으로 반복 및 반환해보기
    const mapReturn = items.map( (item) => {
        console.log( item );
        return item;
    })
    console.log( mapReturn );       // 반환 가능

    // 함수 만들어보기
    // 함수는 1번 호출에 1번 반환한다.
    // -> 한번 return된 HTML(JSX)는 수정이 불가능하다.
    // -> 해결책 : return을 다시 진행 -> 함수 다시 호출 -> 재렌더링 : Hook(useState)
    const onAdd = ( ) => {
        items.push("수박");
        console.log( items );
    } // func end

    return(
        <>
        <h3> JSX 반복문 </h3>
        <h4> forEach : return X </h4>
        <h4> map : return O </h4>

        <ul>
            {
                items.map( (item) => {
                    return  <li> {item} </li>
                })
            }
        </ul>
        <button onClick={ onAdd }> item 추가 </button>
        </>
    ) // return end
} // func end
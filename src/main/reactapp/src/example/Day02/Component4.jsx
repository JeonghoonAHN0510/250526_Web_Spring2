export default function Component4( props ){
    // <------------ JS Start
    const obj = { name : "유재석", age : 40 };


    // ------------> JS End
    // <------------ JSX Start
    return(
        <>
        <h3> {obj.name} , { obj.age } </h3>
        <SubComp key1="value" key2="value"/>
        <SubComp name="유재석" age="40"/>
        <SubComp name={obj.name} age={obj.age}/>
        </>
    ) // return end
    // ------------> JSX End
} // func end

function SubComp( props ){
    console.log( props );

    return(
        <>
        <h4> SubComp </h4>
        <SubSubComp key3="value"/>
        </>
    ) // return end
} // func end


let count2 = 0;
function SubSubComp( props ){
    console.log( props );

    let count1 = 0;
    const onAdd = ( ) => {
        count1++; console.log( `지역변수 : ${count1}` );
        count2++; console.log( `전역변수 : ${count2}` );
    } // func end

    return(
        <>
        <h6> SubSubComp </h6>
        <button onClick={ onAdd }> 버튼 </button>
        <h6> 지역변수 : { count1 } </h6>
        <h6> 전역변수 : { count2 } </h6>
        </>
    ) // return end
} // func end

/*
- 지역변수 : 해당 함수 안에서 사용되는 변수
- 전역변수 : 해당 파일 안에서 사용되는 변수

- JSX에서는 onclick 대신 onClick 속성을 사용한다.

*/
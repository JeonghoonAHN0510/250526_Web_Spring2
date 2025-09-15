console.log('index js open');

// [1] 변수와 상수 키워드
let count1 = 10;        // 변수의 선언과 초기화
count1 = 20;            // 변수값 수정

const count2 = 20;      // 상수의 선언과 초기화
// count2 = 20;         // 상수값 수정 불가능

// 함수 / 객체 / 배열을 담을 수 있다.
const obj = { name : "유재석" };
const method = ( ) => { };
const arr = [ "유재석", "강호동" ];

// var 키워드 : 중복 선언 가능 -> 변수 식별이 어렵다.
var count3 = 20;
var count3 = 30;

// [2] 백틱 : 문자열 템플릿 -> 문자열 내에서 JS 표현식을 사용할 때 사용
console.log( `Hello : ${count1}` );
let html = ``;
html += `<div> Hello : ${count2} </div>`;
console.log( html );

// [3-1] 조건문 : if
const point = 85;
if ( point >= 90 ){
    console.log( "A학점" );
} else if ( point >= 80 ){
    console.log( "B학점" );
} else {
    console.log( "C학점" );
} // if end

// [3-2] 조건문 : 삼항연산자 -> 조건 ? 참 : 거짓;
console.log( point >= 90 ? "A학점" : point >= 80 ? "B학점" : "C학점" );

// [3-3] 조건문 : 단축평가
// 조건 && 참일때결과       -> 거짓이면, false
// 조건 || 거짓일때결과     -> 참이면, true
console.log( point >= 90 && "A학점" );  // 참이면 "A학점", 아니면 false
console.log( point >= 90 || "A학점" );  // 참이면 true, 아니면 "A학점"

// [4-1] 반복문 - 일반 for문
const array = [ 10, 20, 30, 40, 50 ];
for ( let index = 0; index < array.length; index++ ){
    console.log( array[index] );
} // for end

// [4-2] 반복문 - 향상된 for문
for ( let index in array ){
    console.log( array[index] );
} // for end

// [4-3] 반복문 - 향상된 for문
for ( let value of array ){
    console.log( value );
} // for end

// [4-4] 반복문 - forEach
array.forEach( (value) => {
    console.log( value );
}) // forEach end

// [4-5] 반복문 - **map**
// -> return이 가능하다.
let newArray1 = array.map( (value) => {
    console.log( value );
    return value;
}) // map end
console.log( newArray1 );

// [4-6] 반복문 - **filter**
// -> 조건부 return이 가능하다
let newArray2 = array.filter( (value) => {
    console.log( value );
    return value > 20;
}) // filter end
console.log( newArray2 );

// [5-1] 함수 - 선언적 함수
function func1( param1, param2 ){ };            // 선언적 함수 선언
func1( 4, 10 );                                 // 선언적 함수 호출

// [5-2] 함수 - 익명 함수
const func2 = function( param1, param2 ){ };    // 익명 함수 선언
func2( "param1", { name : "name1" } );          // 익명 함수 호출

// [5-3] 함수 - 람다식 함수
const func3 = ( param1, param2 ) => { };        // 람다식 함수 선언
func3( 10, "param2" );                          // 람다식 함수 호출

const func4 = ( param1, param2 = "name" ) => { };// 람다식 함수 선언 + 매개변수 기본값
func4( 20 );

// [6] 객체 - 여러개의 값을 가진 하나의 값
const obj1 = { name : "유재석", age : 40 };
const obj2 = [ "유재석", 40 ];
const name3 = "강호동";
const age3 = 30;
const obj3 = { name3, age3 };
console.log( obj1.name );
console.log( obj2[0] );
console.log( obj3.name3 );

// [*] 스프레드 연산자 : ...
// 배열이나 객체를 복사할 때 사용 -> 주소값을 변경하기 위해서
const obj4 = { ...obj1, phone : "010" };    // obj1 + phone
console.log( obj4 );
const obj5 = { ...obj3 };                   // ojb3
console.log( obj5 );
// 값은 똑같지만, 새로운 주소로 복사됨
const obj6 = [ 6, 7, ...obj2 ];
console.log( obj6 );

// [7] 구조 분해 할당 : 객체나 배열에서 값을 분해하는 방법
const user = { name : "유재석", age : 40 };
// 객체의 속성명(key)과 동일한 이름으로 선언해야한다.
const { name, age } = user;
console.log( name, age );

// [8] 비구조화 할당과 나머지 연산자
const [ num1, num2, ...intArray ] = [ 1, 2, 3, 4 ];
console.log( num1 );        // 인덱스 순서대로 분해 후
console.log( num2 );
console.log( intArray );    // 나머지를 ...에 저장한다.

// [9] async / await - 동기화
// [9-1] 비동기 fetch
// fetch는 원래 비동기 함수
const method1 = ( ) => {
    fetch( "url" )
    .then( response => response.json() )
    .then( data => console.log( data ) );
} // func end
// [9-2] 동기 fetch
const method2 = async ( ) => {
    const response = await fetch( "url" );
    const data = await response.json();
    console.log( data );
} // func end

// [*] Promise - await은 promise를 사용하는 함수에만 사용할 수 있다.
const promiseFunc = async ( ) => {
    // resolve : 성공, reject : 실패
    return await new Promise( ( resolve, reject ) => {
        if ( 10 > 13 ){
            resolve("10이 13보다 크다.");
        } else {
            reject("10이 13보다 작다.");
        } // if end
    })
} // func end
promiseFunc();
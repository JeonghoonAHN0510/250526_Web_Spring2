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

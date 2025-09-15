console.log('app js open');

// 다른 js에서 export된 자료 가져오기
// [1] math.js 자료 가져오기
import add from './math.js';
console.log( add( 3, 4 ) );

// [2] config.js 자료 가져오기
import config from './config.js';
console.log( config );

// [3] util.js 자료 가져오기
// named export된 자료는 { }로 묶어서 가져온다.
import hello, { PI, E } from './util.js';
console.log( hello( "유재석" ) );
console.log( PI );
console.log( E );
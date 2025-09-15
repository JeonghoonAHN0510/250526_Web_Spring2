console.log('util js open');

// 1. 함수 선언
const hello = ( name ) => { return name + "님"; };
// 2. 함수 기본 내보내기 - export default : 1개만 가능
export default hello;
// 3. 이름을 붙여 내보내기 - named export
export const PI = 3.14;
export const E = 2.71;
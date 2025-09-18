import axios from 'axios';

export default function Component9( props ){
    // [1] axios get
    const onAxios1 = async ( ) => {
        // 1. get - 기본
        const response1 = await axios.get( "https://jsonplaceholder.typicode.com/posts" );
        console.log( response1.status ); // HTTP 응답상태코드
        console.log( response1.data );   // HTTP 응답자료

        // 2. get - 쿼리스트링
        const response2 = await axios.get( "https://jsonplaceholder.typicode.com/comments?postId=1" );
        console.log( response2.status );
        console.log( response2.data );
    } // func end

    // [2] axios post
    const onAxios2 = async ( ) => {
        const obj = { title : "test", body : "test", userId : 101 };
        const response = await axios.post( "https://jsonplaceholder.typicode.com/posts", obj );
        console.log( response.status );
        console.log( response.data );
    } // func end

    // [3] axios put
    const onAxios3 = async ( ) => {
        const obj = { title : "test", body : "test", userId : 101, id : 1 };
        const response = await axios.put( "https://jsonplaceholder.typicode.com/posts/1", obj );
        console.log( response.status );
        console.log( response.data );
    } // func end

    // [4] axios delete
    const onAxios4 = async ( ) => {
        const response = await axios.delete( "https://jsonplaceholder.typicode.com/posts/1" );
        console.log( response.status );
        console.log( response.data );
    } // func end

    return(
        <>
        <h3> axios 예제 </h3>
        <button onClick={onAxios1}> axios GET </button>     <br/>
        <button onClick={onAxios2}> axios POST </button>    <br/>
        <button onClick={onAxios3}> axios PUT </button>     <br/>
        <button onClick={onAxios4}> axios DELETE </button>
        </>
    ) // return end
} // func end

/*
[ axios ]
- React에서 주로 사용되는 REST API 비동기 통신 함수
- 설치방법
    1) React 서버가 종료된 상태에서 React 최상위 폴더에서 터미널 열기
    2) npm install axios -> https://www.npmjs.com/
- REST API test
    1) REST API test URL : https://jsonplaceholder.typicode.com/
    2) GET URL : https://jsonplaceholder.typicode.com/posts
- 사용방법
    1) import axios from 'axios';
    2) const response = await axios.get( URL );
    3) const response = await axios.post( URL, obj );
    4) const response = await axios.put( URL, obj );
    5) const response = await axios.delete( URL );
    6) response.data   : HTTP 응답자료
    7) response.status : HTTP 응답상태코드
*/
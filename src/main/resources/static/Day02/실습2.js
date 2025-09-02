console.log('실습2.js open');

// 클라이언트 웹소켓 구현
const client = new WebSocket("/alarm");

// 1. 서버와 연결 성공
client.onopen = ( event ) => {
    console.log("[클라이언트] 서버와 연결 성공");
} // func end

// 2. 서버와 연결 종료
client.onclose = ( event ) => {
    console.log("[클라이언트] 서버와 연결 종료");
} // func end

// 3. 서버로부터의 메시지 확인
client.onmessage = ( event ) => {
    console.log("[클라이언트] 서버로부터의 메시지 수신");
    console.log( event );
    // 서버로부터 받은 메시지를 alert를 통해 알리기
    alert( event.data );
} // func end
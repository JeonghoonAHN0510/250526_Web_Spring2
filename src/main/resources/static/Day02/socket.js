console.log('socket.js open');

// 클라이언트 웹 소켓 구현
// 1. JS(클라이언트)가 SPRING(서버)에게 웹소켓 접속/연결 요청
// new WebSocket("ws웹소켓 서버주소"); -> 클라이언트 웹소켓 객체 생성
// ws웹소켓 서버주소 : WebSocketConfig에서 정의한 주소
const client = new WebSocket("ws://localhost:8080/chat");  // "ws://localhost:8080" 생략 가능

// 2. JS(클라이언트) 소켓이 제공하는 주요 메소드
// 1) onopen() : 서버소켓과 연결이 성공되었을 때 실행되는 메소드
client.onopen = ( event ) => {
    console.log('[클라이언트] 서버소켓과 연동 성공!');
} // func end

// 2) onclose() : 서버소켓과 연결이 종료되었을 때 실행되는 메소드
client.onclose = ( event ) => {
    console.log('[클라이언트] 서버소켓과 연동 종료!');
} // func end

// 3) onerror() : 서버소켓과 연결 중 에러가 발생했을 때 실행되는 메소드
client.onerror = ( event ) => {
    console.log('[클라이언트] 서버소켓과 에러 발생!');
} // func end

// 4) onmessage() : 서버소켓으로부터 메시지를 받았을 때 실행되는 메소드
client.onmessage = ( event ) => {
    console.log('[클라이언트] 서버소켓으로부터 메시지 수신!');
} // func end
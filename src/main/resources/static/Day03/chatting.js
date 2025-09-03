console.log('chatting.js open');

// [1] JAVA의 서버소켓과 연결
const client = new WebSocket("/chat");

// [2] .onopen()
client.onopen = ( event ) => {
    console.log( '[클라이언트] 서버소켓과 연동 시작' );
} // func end

// [3] .onclose()
client.onclose = ( event ) => {
    console.log( '[클라이언트] 서버소켓과 연동 종료' );
} // func end

// [4] .onerror()
client.onerror = ( event ) => {
    console.log( '[클라이언트] 오류 발생' + event );
} // func end

// [5] .onmessage()
client.onmessage = ( event ) => {
    console.log( '[클라이언트] 서버소켓으로부터 메시지 수신');
    console.log( event.data );
} // func end
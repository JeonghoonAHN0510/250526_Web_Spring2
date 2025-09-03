console.log('chatting.js open');

// [*] 익명, 방번호 전역변수로 선언
const randomId = Math.floor( Math.random() * 1000 ) + 1;    // 1부터 1000까지의 난수 생성
const nickname = `익명${randomId}`;                         // 익명 + 난수
const params = new URL( location.href ).searchParams;       // 쿼리스트링 가져오기
const room = params.get('room') || "0";                     // 쿼리스트링 중에서 'room' 가져오기 -> 없으면 0

// [1] JAVA의 서버소켓과 연결
const client = new WebSocket("/chat");

// [2] .onopen()
client.onopen = ( event ) => {
    console.log( '[클라이언트] 서버소켓과 연동 시작' );
    // 1. JSON 형식으로 문자열 메시지 생성
    let message = { type : "join", room : room, nickname : nickname };
    // 2. 특정한 방에 특정한 닉네임을 등록하는 메시지 전송
    client.send( JSON.stringify( message ) );
        // JSON.stringify() : 객체(JSON) 타입을 형식은 유지하되, 문자열 타입으로 변환
        // JSON.parse()     : 문자열 타입을 객체(JSON) 타입으로 변환
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
    event; // 해당 메소드가 왜 실행되었는지에 대한 여러 정보가 들어있는 객체
    // 5-1. 서버로부터 받은 메시지 확인
    console.log( event.data );
    // 5-2. 받은 메시지를 JSON 타입으로 변환
    const message = JSON.parse( event.data );
    // 5-3. 받은 메시지의 type을 확인하여, 서로 다른 html 만들어주기
    let html = ``;
    if ( message.type == 'alarm' ){
        html += `<div class="alarm">
                    <span> ${ message.message } </span>
                 </div>`
    } // if end
    // 5-4. 구성한 html을 div에 추가하기( += ) <---> 대입( = )
    document.querySelector('.msgbox').innerHTML += html;
} // func end
package example.Day03;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

@Component // 스프링 컨테이너에 bean 등록
public class ChatSocketHandler extends TextWebSocketHandler {
    // ********************** 서버소켓 역할 ********************** \\
    // * 접속된 클라이언트 소켓들을 서버가 가지고 있을 예정
    private static final Map< String, List<WebSocketSession> > 접속명단 = new Hashtable<>();
    // { 0 : [ "유재석", "강호동" ], 1 : [ "서장훈", "김희철" ] }
    // key : 방번호, value : 방(key)에 접속한 클라이언트들

    // TextWebSocketHandler로부터 상속받아서 사용
    // 1. 클라이언트 소켓과 서버 소켓의 연동이 시작되었을 때, 실행되는 메소드
    @Override
    public void afterConnectionEstablished( WebSocketSession session ) throws Exception {
        System.out.println("[서버] 클라이언트 소켓과 연동 시작");
    } // func end

    // 2. 클라이언트 소켓과 서버 소켓의 연동이 종료되었을 때, 실행되는 메소드
    @Override
    public void afterConnectionClosed( WebSocketSession session, CloseStatus status ) throws Exception {
        System.out.println("[서버] 클라이언트 소켓과 연동 종료");
    } // func end

    // 3. 클라이언트 소켓으로부터 메시지를 수신했을 때, 실행되는 메소드
    @Override
    protected void handleTextMessage( WebSocketSession session, TextMessage message ) throws Exception {
        System.out.println("[서버] 클라이언트 소켓으로부터 메시지 수신");
        // 3-1. 클라이언트 소켓으로부터 수신한 메시지 출력 : message.getPayload()
        System.out.println("message = " + message.getPayload() );
        // 3-2. JSON 형식을 Map 타입으로 변환 -> Restful API(@ResponseBody, @RequestBody)가 자동으로 타입 변환해줬기 때문에, 할 필요가 없었다.
        // JAVA는 JSON 형식을 모른다.
        Map< String, String > msg = objectMapper.readValue( message.getPayload(), Map.class );
        // 3-3. 만약 message의 'type'이 'join'이면
        if ( msg.get("type").equals("join") ){
            String room = msg.get("room");          // 방번호
            String nickname = msg.get("nickname");  // 접속자
            // 3-4. 메시지를 보내온 클라이언트 소켓 세션에 부가정보(방번호, 접속자) 추가 -> HTTP 세션과 비슷
            session.getAttributes().put( "room", room );
            session.getAttributes().put( "nickname", nickname );
            // 3-5. 접속명단에 등록하기
            // 3-6. 등록할 방이 존재하면,
            if ( 접속명단.containsKey( room ) ){
                // 3-7. 해당하는 방에 세션 추가
                접속명단.get( room ).add( session );
            } else {
                // 3-8. 등록할 방이 존재하지 않으면, 방을 생성하고
                List< WebSocketSession > list = new Vector<>();
                // 3-9. 새로운 목록에 세션 추가
                list.add( session );
                // 3-10. 새로운 방에 새로운 목록 추가
                접속명단.put( room, list );
            } // if end
        }
        System.out.println("접속명단 = " + 접속명단);   // 확인용 프린트
    } // func end

    // * JSON 타입을 자바 타입으로 변환해주는 라이브러리 객체 : ObjectMapper -> Python, C와 통신할 때, 주로 사용
    // [ 주요 메소드 ]
    // 1. .readValue( JSON형식, 변환할클래스명.class )
    private final ObjectMapper objectMapper = new ObjectMapper();
} // class end
package example.Day03;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component // 스프링 컨테이너에 bean 등록
public class ChatSocketHandler extends TextWebSocketHandler {
    // ********************** 서버소켓 역할 ********************** \\
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
    } // func end
} // class end
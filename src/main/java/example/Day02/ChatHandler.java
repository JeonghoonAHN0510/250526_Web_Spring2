package example.Day02;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component  // 스프링 컨테이너에 bean 등록
public class ChatHandler extends TextWebSocketHandler {

    // 1. 클라이언트 소켓이 서버 소컷과 연결을 성공했을 때 실행되는 메소드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    } // func end
    
    // 2. 클라이언트 소켓이 서버 소켓과 연결이 끊겼을 때 실행되는 메소드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    } // func end
    
    // 3. 클라이언트 소켓이 서버 소켓에게 메시지를 보냈을 떄 실행되는 메소드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    } // func end
} // class end
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
        System.out.println("[서버] 클라이언트 소켓과 연결 성공!");
        // WebSocketSession이란? ws기반으로 서버에게 요청한 클라이언트 정보가 저장된 객체
        // HttpSession이란? Http 기반으로 클라이언트가 요청한 정보가 저장된 객체
        System.out.println("[클라이언트 웹소켓 객체] : " + session );
    } // func end
    
    // 2. 클라이언트 소켓이 서버 소켓과 연결을 종료했을 때 실행되는 메소드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("[서버] 클라이언트 소켓과 연결 종료!");
    } // func end
    
    // 3. 클라이언트 소켓이 서버 소켓에게 메시지를 보냈을 떄 실행되는 메소드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("[서버] 클라이언트 소켓으로부터 메시지 수신!");
    } // func end
} // class end
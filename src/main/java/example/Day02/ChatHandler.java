package example.Day02;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Vector;

@Component  // 스프링 컨테이너에 bean 등록
public class ChatHandler extends TextWebSocketHandler {

    // 서버에 접속된 클라이언트 소켓 목록
    private static final List< WebSocketSession > clientSockets = new Vector<>();
    // ArrayList : 동기화 지원 X, Vector : 동기화 지원 O -> 채팅은 동시다발적으로 요청이 있으므로, 동기화하는 것이 일반적

    // 1. 클라이언트 소켓이 서버 소컷과 연결을 성공했을 때 실행되는 메소드
    @Override
    public void afterConnectionEstablished( WebSocketSession session ) throws Exception {
        System.out.println("[서버] 클라이언트 소켓과 연결 성공!");
        // WebSocketSession이란? ws기반으로 서버에게 요청한 클라이언트 정보가 저장된 객체
        // HttpSession이란? Http 기반으로 클라이언트가 요청한 정보가 저장된 객체
        System.out.println("[클라이언트 웹소켓 객체] : " + session );

        // 1. 접속된 클라이언트 소켓들을 저장 -> 받은 메시지를 접속된 소켓들에게 재전송하기 위해서
        clientSockets.add( session );   // 서버에 접속을 성공한 클라이언트 소켓(세션)을 저장
    } // func end
    
    // 2. 클라이언트 소켓이 서버 소켓과 연결을 종료했을 때 실행되는 메소드
    @Override
    public void afterConnectionClosed( WebSocketSession session, CloseStatus status ) throws Exception {
        System.out.println("[서버] 클라이언트 소켓과 연결 종료!");
        // 1. 서버와 연결이 종료된 클라이언트 소켓을 제거
        clientSockets.remove( session );
    } // func end
    
    // 3. 클라이언트 소켓이 서버 소켓에게 메시지를 보냈을 때 실행되는 메소드
    @Override
    protected void handleTextMessage( WebSocketSession session, TextMessage message ) throws Exception {
        System.out.println("[서버] 클라이언트 소켓으로부터 메시지 수신!");
        System.out.println("[메시지 확인] : " + message );
        // ** 서버가 클라이언트에게 메시지 보내기
        // session.sendMessage( new TextMessage("클라이언트 안녕") ); -> 현재로썬 서버에게 메시지를 보낸 클라이언트에게 메시지를 보내는 컨셉

        // 1. 특정한 클라이언트가 보낸 메시지를 현재 접속된 다른 클라이언트에게 메시지 보내기
        for ( WebSocketSession clientSocket : clientSockets ){
            // message : 서버가 받은 메시지
            clientSocket.sendMessage( message );
        } // for end
    } // func end
} // class end
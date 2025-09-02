package example.task.task02;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Vector;

@Component  // bean 등록
public class AlarmHandler extends TextWebSocketHandler {

    // 서버에 접속한 클라이언트 소켓 목록
    private static final List< WebSocketSession > clientSockets = new Vector<>();

    // 1. 연결 성공 메소드
    @Override
    public void afterConnectionEstablished( WebSocketSession session ) throws Exception {
        System.out.println("[서버] 클라이언트와 연결 성공");

        // 1. 연결 성공하였으므로, 소켓 목록에 추가
        clientSockets.add( session );
        // 2. 모든 클라이언트에게 alert 문구 메시지 보내기
        for ( WebSocketSession clientSocket : clientSockets ){
            clientSocket.sendMessage( new TextMessage("익명의 유저가 접속했습니다.") );
        } // for end
    } // func end

    // 2. 연결 종료 메소드
    @Override
    public void afterConnectionClosed( WebSocketSession session, CloseStatus closeStatus ) throws Exception {
        System.out.println("[서버] 클라이언트와 연결 종료");

        // 1. 연결 종료되었으므로, 소켓 목록에서 삭제
        clientSockets.remove( session );
        // 2. 모든 클라이언트에게 alert 문구 메시지 보내기
        for ( WebSocketSession clientSocket : clientSockets ){
            clientSocket.sendMessage( new TextMessage("익명의 유저가 종료했습니다.") );
        } // for end
    } // func end
} // class end
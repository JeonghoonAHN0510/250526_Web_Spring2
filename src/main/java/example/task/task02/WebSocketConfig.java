package example.task.task02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration      // bean 등록
@EnableWebSocket    // 웹소켓 기능 활성화
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired      // 의존성 주입
    private AlarmHandler alarmHandler;

    // 서버웹소켓 객체를 등록
    @Override
    public void registerWebSocketHandlers( WebSocketHandlerRegistry registry ){
        // 1. 개발자가 만든 서버웹소켓을 주소와 함께 등록한다.
        registry.addHandler( alarmHandler, "/alarm" );
    } // func end
} // class end
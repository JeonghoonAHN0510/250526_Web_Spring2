package example.Day02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration      // 스프링 컨테이너에 bean 등록
@EnableWebSocket    // 웹소켓 기능 활성화
public class WebSocketConfig implements WebSocketConfigurer {      // ws 프로토콜 통신이 왔을 때, 특정한 핸들러(클래스)로 매핑/연결해주는 역할

    @Autowired  // 의존성 주입
    private ChatHandler chatHandler;    // 서버웹소켓 객체

    // 1. 서버웹소켓(핸들러) 객체를 등록한다.
    // -> 개발자가 만든 서버웹소켓(핸들러) 객체들을 스프링이 알 수 있게 경로를 등록한다.
    @Override
    public void registerWebSocketHandlers( WebSocketHandlerRegistry registry ){
        // 1. 개발자가 만든 서버웹소켓을 주소와 함께 등록한다.
        // registry.addHandler( 서버웹소켓객체, "경로" );
        registry.addHandler( chatHandler, "/chat" );

    } // func end




} // class end
package example.Day03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration      // 스프링 컨테이너에 bean 등록
@EnableWebSocket    // 웹소켓 기능 활성화
public class WebSocketConfig implements WebSocketConfigurer {
    // ********************** 서버소켓 주소 매핑 클래스 ********************** \\
    // 1. @Autowired : 스프링 컨테이너에 bean이 등록되어있어야 가능하다.
    @Autowired
    private ChatSocketHandler chatSocketHandler;

    // 2. 내가 만든 서버소켓 Handler를 등록
    @Override
    public void registerWebSocketHandlers( WebSocketHandlerRegistry registry ){
     // registry.addHandler( 서버소켓 Handler, "/chat" ); -> 여러개 등록 가능
        registry.addHandler( chatSocketHandler, "/chat" );
    } // func end
} // class end
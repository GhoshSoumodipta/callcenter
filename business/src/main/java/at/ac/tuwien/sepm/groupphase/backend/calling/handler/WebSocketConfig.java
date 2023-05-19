package at.ac.tuwien.sepm.groupphase.backend.calling.handler;



import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistration;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.List;

@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

    private SignalingHandler socketHandler;
    private Environment environment;

    public WebSocketConfig(SignalingHandler socketHandler, Environment environment) {
        this.socketHandler = socketHandler;
        this.environment = environment;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        WebSocketHandlerRegistration handlerRegistration = registry.addHandler(socketHandler, "/calling");
        if(List.of(environment.getActiveProfiles()).stream().noneMatch(myProfile -> myProfile.toLowerCase().contains("prod"))){
            handlerRegistration.setAllowedOrigins("*");
        }
    }
}

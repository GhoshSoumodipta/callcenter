package at.ac.tuwien.sepm.groupphase.backend.calling.handler;


/*import at.ac.tuwien.sepm.groupphase.backend.websocket.common.JwtTokenProvider;
import at.ac.tuwien.sepm.groupphase.backend.websocket.common.Role;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SignalingHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(SignalingHandler.class);

    private static final String LOCALHOSTTOKEN = "$%&";

   // private JwtTokenProvider jwtTokenProvider;

    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    private record SenderReceiver(String sender, String receiver){
        /**
         * no code
         */
    }

    /*public SignalingHandler(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }*/

   /* private Optional<String> extractToken(WebSocketSession session){
        //String[] tokens = session.getUri().getQuery().split("token=");
        String[] tokens = new String[] {"abc"};
        return tokens.length > 1 ? Optional.ofNullable(tokens[1]) : Optional.empty();
    }

    private boolean checkUserRole(WebSocketSession session){
        Optional<String> optionalToken = extractToken(session);
        logger.info(optionalToken.get());
        return optionalToken.stream().filter(myToken -> jwtTokenProvider.validateToken(myToken))
            .anyMatch(myToken -> jwtTokenProvider.getAuthorities(myToken).stream()
                .anyMatch(myRole -> Role.USERS.equals(myRole)));
    }

    private boolean isTokenExpired(WebSocketSession session){
        Optional<String> optionalToken = extractToken(session);
        return optionalToken.stream().allMatch(myToken -> 0 >= jwtTokenProvider.getTtl(myToken));
    }

    private void removeStaleSession(WebSocketSession session) throws IOException {
        if(!extractToken(session).stream().anyMatch(myToken -> jwtTokenProvider.validateToken(myToken))){
            session.close();
        }
    }

    private SenderReceiver extractSenderReceiver(TextMessage message){
        List<String> fragments = List.of(message.getPayload().split("\\,"));
        String senderId = fragments.stream().filter(myFragment -> myFragment.contains("senderId")).findAny().stream()
            .map(myFragment -> myFragment.split("\\:")[1].replace('"', ' ').trim()).findFirst()
            .orElseThrow(() -> new RuntimeException("SenderId not found in message"));
        String receiverId = fragments.stream().filter(myFragment -> myFragment.contains("receiverId")).findAny()
            .stream().map(myFragment -> myFragment.split("\\:")[1].replace('"', ' ').trim()).findFirst()
            .orElseThrow(() -> new RuntimeException("ReceiverId not found in message"));
        return new SenderReceiver(senderId, receiverId);
    }

    private String extractSessionUsername(WebSocketSession session){
        return extractToken(session).stream().map(myToken -> jwtTokenProvider.getUsername(myToken)).findAny()
            .orElseThrow(() -> new RuntimeException("Session token username not found"));
    }

    private boolean checkSenderReceiver(SenderReceiver senderReceiver, String sessionUsername, String webSocketSessionUsername){
        return senderReceiver.sender.equalsIgnoreCase(sessionUsername) && senderReceiver.receiver.contains(webSocketSessionUsername);
    }

    private boolean checkSenderLocalhostToken(SenderReceiver senderReceiver, String sessionUsername, String webSocketSessionUsername){
        return (senderReceiver.sender.contains(LOCALHOSTTOKEN) || senderReceiver.receiver.contains(LOCALHOSTTOKEN))
            && senderReceiver.sender.contains(webSocketSessionUsername)
            && senderReceiver.receiver.contains(webSocketSessionUsername);
    }

    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        if(checkUserRole(session)){
            SenderReceiver senderReceiver = extractSenderReceiver(message);
            String sessionUsername = extractSessionUsername(session);
            for(WebSocketSession webSocketSession : sessions){
                removeStaleSession(webSocketSession);
                String webSocketSessionUsername = extractSessionUsername(webSocketSession);
                if(webSocketSession.isOpen() && (checkSenderReceiver(senderReceiver, sessionUsername, webSocketSessionUsername)
                || checkSenderLocalhostToken(senderReceiver, sessionUsername, webSocketSessionUsername))){
                    logger.debug("Msg send with params: Msg sender: {}, Msg receiver: {}, Session sender: {}, WebSocket receiver: {}",
                        senderReceiver.sender, senderReceiver.receiver, sessionUsername, webSocketSessionUsername);
                    webSocketSession.sendMessage(message);
                }
            }
        }else{
            if(isTokenExpired(session)){
                session.close();
            }
        }
    }*/

    public void afterConnectionEstablished(WebSocketSession session){
        /*if (checkUserRole(session)){
            sessions.add(session);
        }*/
        sessions.add(session);
        logger.info("socket connection established");
    }

    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        sessions.remove(session);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsavvy.mindsmeet.entity.MsgMst;
import com.techsavvy.mindsmeet.entity.Users;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Acer
 */
@ServerEndpoint("/chat/{userId}")
public class ChatWebSocket {
    // EJB for database operations

    @EJB
    private ChatBean msgMstService;

    // Active WebSocket sessions (userId -> Session)
    private static final ConcurrentHashMap<Integer, Session> activeSessions = new ConcurrentHashMap<>();

    // JSON object mapper
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * On connection open: add the user's session to the map.
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Integer userId) {
        activeSessions.put(userId, session);
        System.out.println("User connected: " + userId);
    }

    /**
     * On receiving a message: handle it based on the message type.
     */
    @OnMessage
    public void onMessage(String messageJson, @PathParam("userId") Integer senderId) {
        try {
            System.out.println(messageJson);
            // Parse the incoming message
            ChatMessage chatMessage = objectMapper.readValue(messageJson, ChatMessage.class);
            
//            JsonObject json = Json.createReader(new StringReader(chatMessage.message)).readObject();
//            Integer receiverId = json.getInt("receiverId");
//            String text = json.getString("message");

            // Save the message to the database
            MsgMst msg = new MsgMst();
            Users sender = new Users();
            sender.setId(chatMessage.getSenderId());
            msg.setSenderId(sender);
            Users receiver = new Users();
            receiver.setId(chatMessage.getReceiverId());
            msg.setReceiverId(receiver);
            msg.setMessage(chatMessage.getMessage());
            msg.setIsRead(false);
            msg.setCreatedAt(new Date());
            msg.setUpdatedAt(new Date());
            msgMstService.saveMessage(msg);

            // Send the message to the receiver if online
            Session receiverSession = activeSessions.get(chatMessage.getReceiverId());
            if (receiverSession != null && receiverSession.isOpen()) {
                receiverSession.getAsyncRemote().sendText(messageJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * On connection close: remove the user's session from the map.
     */
    @OnClose
    public void onClose(@PathParam("userId") Integer userId) {
        activeSessions.remove(userId);
        System.out.println("User disconnected: " + userId);
    }

    /**
     * On error: log the error.
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("WebSocket error: " + throwable.getMessage());
    }

    // Inner class to represent chat messages (JSON structure)
    public static class ChatMessage {

        private Integer senderId;
        private Integer receiverId;
        private String message;

        // Getters and setters
        public Integer getSenderId() {
            return senderId;
        }

        public void setSenderId(Integer senderId) {
            this.senderId = senderId;
        }

        public Integer getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(Integer receiverId) {
            this.receiverId = receiverId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
    
}

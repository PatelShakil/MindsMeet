/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package community;

import chat.ChatWebSocket;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsavvy.mindsmeet.entity.CommunityMsg;
import com.techsavvy.mindsmeet.entity.CommunityMst;
import com.techsavvy.mindsmeet.entity.Users;
import ejb.CommunityBeanLocal;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/community-messaging")
public class CommunityWebSocket {
    
    @EJB CommunityBeanLocal cbl;

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("New session opened: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Message received: " + message);
        CommunityMessage cm;
        try {
            cm = objectMapper.readValue(message, CommunityMessage.class);
        
        
        
        CommunityMsg cms = new CommunityMsg();
        Users user = new Users();
        user.setId(cm.getSenderId());
        cms.setUserId(user);
        CommunityMst cmst = new CommunityMst();
        cmst.setId(cm.communityId);
        cms.setCommunityId(cmst);
        cms.setMessage(cm.getMessage());
        
        cbl.postMsgInCommunity(cms);

        // Broadcast to all clients
        synchronized (sessions) {
            for (Session s : sessions) {
                if (s.isOpen()) {
                    try {
                        s.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        } catch (IOException ex) {
            Logger.getLogger(CommunityWebSocket.class.getName()).log(Level.SEVERE,ex.getMessage(), ex);
        }
    }
    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("Session closed: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("Error on session " + session.getId() + ": " + throwable.getMessage());
    }
    
    public static class CommunityMessage{
        private Integer senderId;
        private Integer communityId;
        private Boolean isReply;
        private String reply;
        private String message;

        public Integer getSenderId() {
            return senderId;
        }

        public void setSenderId(Integer senderId) {
            this.senderId = senderId;
        }

        public Integer getCommunityId() {
            return communityId;
        }

        public void setCommunityId(Integer communityId) {
            this.communityId = communityId;
        }

        public Boolean getIsReply() {
            return isReply;
        }

        public void setIsReply(Boolean isReply) {
            this.isReply = isReply;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        
        
        
        
        
        
        }
}

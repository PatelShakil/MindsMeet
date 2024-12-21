/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package chat;

import com.techsavvy.mindsmeet.entity.MsgMst;
import com.techsavvy.mindsmeet.entity.Users;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Acer
 */
@Stateless
public class ChatBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
        @PersistenceContext(unitName = "my_mindsmeet_pu")
        private EntityManager em;

    /**
     * Save a new message.
     *
     * @param message the MsgMst object to save.
     */
    public void saveMessage(MsgMst message) {
        Users s = em.find(Users.class, message.getSenderId().getId());
        Users r = em.find(Users.class, message.getReceiverId().getId());
        message.setSenderId(s);
        message.setReceiverId(r);
        message.setCreatedAt(new Date());
        message.setUpdatedAt(new Date());
        em.persist(message);
    }

    /**
     * Get all messages between two users, sorted by creation date.
     *
     * @param senderId the ID of the sender.
     * @param receiverId the ID of the receiver.
     * @return a list of MsgMst objects.
     */
    public List<MsgMst> getMessages(Integer senderId, Integer receiverId) {
        TypedQuery<MsgMst> query = em.createQuery(
                "SELECT m FROM MsgMst m "
                + "WHERE (m.senderId.id = :senderId AND m.receiverId.id = :receiverId) "
                + "   OR (m.senderId.id = :receiverId AND m.receiverId.id = :senderId) "
                + "ORDER BY m.createdAt ASC",
                MsgMst.class
        );
        query.setParameter("senderId", senderId);
        query.setParameter("receiverId", receiverId);
        return query.getResultList();
    }
    public List<MsgMst> getMessagesForReceiver(Integer receiverId) {
        TypedQuery<MsgMst> query = em.createQuery(
                "SELECT m FROM MsgMst m "
                + "WHERE (m.receiverId.id = :receiverId) "
                + "ORDER BY m.createdAt ASC",
                MsgMst.class
        );
        query.setParameter("receiverId", receiverId);
        return query.getResultList();
    }

    /**
     * Mark messages as read between two users.
     *
     * @param senderId the ID of the sender.
     * @param receiverId the ID of the receiver.
     */
    public void markMessagesAsRead(Integer senderId, Integer receiverId) {
        em.createQuery(
                "UPDATE MsgMst m SET m.isRead = TRUE, m.updatedAt = :now "
                + "WHERE m.senderId.id = :senderId AND m.receiverId.id = :receiverId AND m.isRead = FALSE"
        )
                .setParameter("senderId", senderId)
                .setParameter("receiverId", receiverId)
                .setParameter("now", new Date())
                .executeUpdate();
    }

    /**
     * Delete a message by ID.
     *
     * @param messageId the ID of the message to delete.
     */
    public void deleteMessage(Integer messageId) {
        MsgMst message = em.find(MsgMst.class, messageId);
        if (message != null) {
            em.remove(message);
        }
    }
}

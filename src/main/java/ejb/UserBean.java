/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import com.techsavvy.mindsmeet.entity.CommunityMembers;
import com.techsavvy.mindsmeet.entity.CommunityMsg;
import com.techsavvy.mindsmeet.entity.CommunityMst;
import com.techsavvy.mindsmeet.entity.CommunityReply;
import com.techsavvy.mindsmeet.entity.FaqAnswers;
import com.techsavvy.mindsmeet.entity.FaqMst;
import com.techsavvy.mindsmeet.entity.MsgMst;
import com.techsavvy.mindsmeet.entity.NoteComments;
import com.techsavvy.mindsmeet.entity.NoteReplies;
import com.techsavvy.mindsmeet.entity.Notes;
import com.techsavvy.mindsmeet.entity.PostComments;
import com.techsavvy.mindsmeet.entity.PostFeedMst;
import com.techsavvy.mindsmeet.entity.UserSettings;
import com.techsavvy.mindsmeet.entity.Users;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utils.Resource;

/**
 *
 * @author M.SHAKIL PATEL
 */

@Stateless
public class UserBean implements UserBeanLocal {

    @PersistenceContext(unitName = "my_mindsmeet_pu")
    EntityManager em;

    @Override
    public Resource<Users> doLogin(String email, String password) {
        Resource<Users> res = new Resource<>(null, "", false);
        try {
            // Fetch user by email
            List<Users> users = em.createNamedQuery("Users.findByEmail", Users.class)
                    .setParameter("email", email)
                    .getResultList();

            // Check if any user exists with the provided email
            if (!users.isEmpty()) {
                Users user = users.get(0);  // Fetch the first user

                // Check password match
                if (user.getPassword().equals(password)) {
                    res.setMessage("User Found");
                    res.setStatus(true);
                    res.setObj(user);
                } else {
                    res.setMessage("Invalid password!");
                    res.setStatus(false);
                }
            } else {
                res.setMessage("Email not found!");
                res.setStatus(false);
            }
        } catch (Exception ex) {
            // Handle unexpected exceptions and return proper error message
            res.setMessage("An error occurred: " + ex.getMessage());
            res.setStatus(false);
        } finally {
            // Return the Resource object, ensuring it always returns something
            return res;
        }
    }

    @Override
    public Resource<Users> doSignup(Users user) {
        Resource<Users> res = new Resource(null, "", false);

        try {
            em.persist(user); // Persist the new user into the database
            em.flush();
            Users u = em.find(Users.class, user.getId()); // Retrieve the persisted user using its ID

            if (u != null) {
                res.setMessage("Signup Successful!!!");
                res.setStatus(true);
                res.setObj(u);
            } else {
                res.setMessage("Signup error!!!");
                res.setStatus(false);
                res.setObj(null);
            }
        } catch (Exception e) {
            res.setMessage("An error occurred during signup: " + e.getMessage());
            res.setStatus(false);
            res.setObj(null);
        }

        return res;
    }


    @Override
    public void updateSetting(UserSettings us) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateUser(Users user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Resource<Boolean> uploadNote(Notes note) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Resource<Collection<Notes>> viewNotes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void doCommentOnNotes(NoteComments noteComment) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void replyNotesComments(NoteReplies nr) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editNotes(Notes note) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteNote(Integer nId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Resource<Collection<FaqMst>> viewFaqs() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void answerFaq(Integer faqId, FaqAnswers answer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void voteFaq(Integer answerId, Boolean isUp, Integer userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editFaq(FaqMst fm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteFaq(Integer fId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void doPost(PostFeedMst pfm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void likeOnPost(Integer postId, Integer userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void doCommentOnPost(PostComments pc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deletePost(Integer pId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void joinCommunity(Integer cId, Integer userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void createCommunity(CommunityMst cm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void leftCommunity(Integer cId, Integer userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeMemberToCommunity(Integer cmId, Integer cId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteCommunity(Integer cId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void postMsgInCommunity(CommunityMsg cms) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void replyToCommunityMsg(CommunityReply cr) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addMemberToCommunity(CommunityMembers cm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Resource<Collection<MsgMst>> getAllMsg(Integer rId, Integer sId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void sendMsg(MsgMst msg) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Resource<Collection<CommunityMsg>> loadCommunityMsg(Integer cId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Resource<Collection<PostFeedMst>> viewPosts() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Resource<Collection<Users>> getAllUsers() {
        Resource<Collection<Users>> res = new Resource(null,"",false);
        res.setMessage("Users List");
        res.setObj(em.createNamedQuery("Users.findAll")
                .getResultList());
        
        return res; 
    }

}

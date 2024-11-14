/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
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
import javax.ejb.Local;
import javax.ws.rs.core.Response;
import utils.Resource;

/**
 *
 * @author M.SHAKIL PATEL
 */
@Local
public interface UserBeanLocal {

    //user
    public Resource<Users> doLogin(String email, String password);

    public Response doSignup(Users user);

    public Resource<Boolean> updateSetting(UserSettings us);
    
    public Resource<Boolean> updateUser(Users user);
    
    public Resource<Collection<Users>> getAllUsers();
    
    public Resource<Users> getUser(Integer id);
    
    public Resource<UserSettings> getSetting(Integer id);
    
    //notes
    public Resource<Boolean> uploadNote(Notes note);

    public Resource<Collection<Notes>> viewNotes();

    public Resource<Boolean> doCommentOnNotes(String cmtText,Integer noteId,Integer userId);

    public void replyNotesComments(NoteReplies nr);
    
    public void editNotes(Notes note);
    
    public void deleteNote(Integer nId);
    
    public Resource<Notes> getNoteById(Integer id);
    
    //faqs
    public Resource<Collection<FaqMst>> viewFaqs();

    public void answerFaq(Integer faqId, FaqAnswers answer);

    public void voteFaq(Integer answerId, Boolean isUp, Integer userId);
    
    public void editFaq(FaqMst fm);
    
    public void deleteFaq(Integer fId);
    
    public Resource<FaqMst> getFaqById(Integer id);

    //posts
    public Resource<Collection<PostFeedMst>> viewPosts();
    
    public void doPost(PostFeedMst pfm);

    public void likeOnPost(Integer postId, Integer userId);
    
    public void doCommentOnPost(PostComments pc);
    
    public void deletePost(Integer pId);
    
    //community
    public void joinCommunity(Integer cId, Integer userId);

    public void createCommunity(CommunityMst cm);

    public void leftCommunity(Integer cId, Integer userId);

    public void removeMemberToCommunity(Integer cmId,Integer cId);
    
    public void deleteCommunity(Integer cId);

    public void postMsgInCommunity(CommunityMsg cms);

    public void replyToCommunityMsg(CommunityReply cr);
    
    public void addMemberToCommunity(CommunityMembers cm);
    
    public Resource<Collection<CommunityMsg>> loadCommunityMsg(Integer cId);

    //chat
    public Resource<Collection<MsgMst>> getAllMsg(Integer rId,Integer sId);
    
    public void sendMsg(MsgMst msg);

}

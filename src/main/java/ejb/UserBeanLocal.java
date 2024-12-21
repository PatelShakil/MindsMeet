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

    public Response updateSetting(UserSettings us);
    
    public Response updateUser(Users user);
    
    public Response getAllUsers();
    
    public Resource<Users> getUser(Integer id);
    
    public Resource<UserSettings> getSetting(Integer id);
    
    public Response getUserByUsername(String username);
    public Users getUserByEmail(String Email);
    
    //notes
    public Response uploadNote(Notes note);

    public Response viewNotes();

    public Response doCommentOnNotes(String cmtText,Integer noteId,Integer userId);

    public Response replyNotesComments(NoteReplies nr);
    
    public Response editNotes(Notes note);
    
    public Response deleteNote(Integer nId);
    
    public Response getNoteById(Integer id);
    
    public Collection<Notes> getNotesForUsers(String email);

    public Response getNoteComment(Integer commentId);

}

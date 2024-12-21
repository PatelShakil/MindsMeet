/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import com.techsavvy.mindsmeet.entity.PostComments;
import com.techsavvy.mindsmeet.entity.PostFeedMst;
import com.techsavvy.mindsmeet.entity.PostLikes;
import java.util.Collection;
import javax.ejb.Local;
import javax.ws.rs.core.Response;

/**
 *
 * @author Acer
 */
@Local
public interface PostBeanLocal {
    //posts

    public Collection<PostFeedMst> viewPosts();

    public void doPost(PostFeedMst pfm);

    public void likeOnPost(Integer postId, String email);

    public void doCommentOnPost(PostComments pc);

    public void deletePost(Integer pId);
    
    public PostFeedMst getPostById(Integer id);
    
    public Collection<PostLikes> getPostLikes(Integer id);
    
    public Collection<PostComments> getPostComments(Integer id);
    
}

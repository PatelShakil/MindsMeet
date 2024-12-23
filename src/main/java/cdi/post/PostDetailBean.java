/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi.post;

import api.PostApi;
import auth.KeepRecord;
import cdi.faq.FaqDetailBean;
import com.techsavvy.mindsmeet.entity.PostComments;
import com.techsavvy.mindsmeet.entity.PostFeedMst;
import com.techsavvy.mindsmeet.entity.PostLikes;
import com.techsavvy.mindsmeet.entity.Users;
import ejb.PostBeanLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Acer
 */
@Named(value = "postDetail")
@SessionScoped
public class PostDetailBean implements Serializable {
    @Inject
    KeepRecord keepRecord;

    private PostApi api = new PostApi();

    @EJB
    PostBeanLocal pbl;

    private String postId;

    private PostFeedMst pfm;

    private Collection<PostComments> comments;
    private Collection<PostLikes> likes;
    
    private String comment;

    /**
     * Creates a new instance of PostDetailBean
     */
    public PostDetailBean() {
    }

    public PostApi getApi() {
        return api;
    }

    public void setApi(PostApi api) {
        this.api = api;
    }

    public PostFeedMst getPfm() {
        postId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("postId");
        if (postId != null) {
            try {
//                pfm = pbl.getPostById(Integer.valueOf(postId.split("/?")[0]));

                Response res = api.getPostById(Response.class, postId.split("/?")[0]);
                System.out.println(res.toString());

                pfm = res.readEntity(new GenericType<PostFeedMst>() {
                });
            } catch (Exception e) {
                e.printStackTrace();

            }

        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
            } catch (IOException ex) {
                Logger.getLogger(FaqDetailBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pfm;
    }

    public void setPfm(PostFeedMst pfm) {
        this.pfm = pfm;
    }

    public Collection<PostComments> getComments() {
        Response res = api.getPostComments(Response.class, postId.split("/?")[0]);
        comments = res.readEntity(new GenericType<Collection<PostComments>>() {});
//        comments = pbl.getPostComments(Integer.valueOf(postId));
        return comments;
    }

    public void setComments(Collection<PostComments> comments) {
        this.comments = comments;
    }

    public Collection<PostLikes> getLikes() {

        Response res = api.getPostLikes(Response.class, postId.split("/?")[0]);
        likes = res.readEntity(new GenericType<Collection<PostLikes>>() {
        });
        return likes;
    }

    public void setLikes(Collection<PostLikes> likes) {
        this.likes = likes;
    }

    public String doLike() {

        System.out.println("LIKED");        pbl.likeOnPost(pfm.getId(), keepRecord.getUsername());
//        getPfm();
        return ""; // No navigation, stay on the same page
    }
    
    public String doComment(){
        if(!comment.isEmpty()){
            PostComments pc = new PostComments();
            Users user =new  Users();
            user.setEmail(keepRecord.getUsername());
            pc.setUserId(user);
            pc.setPostId(pfm);
            pc.setText(comment);
            api.doCommentOnPost(pc);  
            comment = "";
            getComments();
        }
        return "";
    }

    public PostBeanLocal getPbl() {
        return pbl;
    }

    public void setPbl(PostBeanLocal pbl) {
        this.pbl = pbl;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public boolean isPostLiked() {
        return pbl.getPostLikes(pfm.getId()).stream()
                .anyMatch(like -> like.getUserId().getEmail().equals(keepRecord.getUsername()));
    }
    
        public int getLikesCount() {
        if (pfm != null) {
            return pbl.getPostLikes(pfm.getId()).size();
        }
        return 0;
    }
    

}

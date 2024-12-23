/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi.profile;

import api.PostApi;
import api.UserApi;
import auth.KeepRecord;
import cdi.Utility;
import com.techsavvy.mindsmeet.entity.CommunityMst;
import com.techsavvy.mindsmeet.entity.FaqMst;
import com.techsavvy.mindsmeet.entity.Notes;
import com.techsavvy.mindsmeet.entity.PostFeedMst;
import com.techsavvy.mindsmeet.entity.Users;
import ejb.CommunityBeanLocal;
import ejb.FaqBeanLocal;
import ejb.PostBeanLocal;
import ejb.UserBeanLocal;
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
import javax.json.Json;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import utils.Utils;

/**
 *
 * @author Acer
 */
@Named(value = "myProfile")
@SessionScoped
public class MyProfileBean implements Serializable {
    @Inject
    KeepRecord keepRecord;

    private Users user;
    
    private UserApi api = new UserApi();
    @EJB UserBeanLocal ubl;
    @EJB FaqBeanLocal fbl;
    @EJB PostBeanLocal pbl;
    @EJB CommunityBeanLocal cbl;
    
    private Collection<Notes> notes;
    private Collection<FaqMst> faqs;
    private Collection<PostFeedMst> posts;
    private Collection<CommunityMst> communities;

    /**
     * Creates a new instance of MyProfileBean
     */
    public MyProfileBean() {
    }

    public Users getUser() {
        if(keepRecord.getUsername() != null){
            user = ubl.getUserByEmail(keepRecord.getUsername());
            System.out.println(user.getFaqAnswersCollection().toString());
        }else{
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/auth/login.jsf");
            } catch (IOException ex) {
                Logger.getLogger(MyProfileBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public UserApi getApi() {
        return api;
    }

    public void setApi(UserApi api) {
        this.api = api;
    }

    public Collection<Notes> getNotes() {
        notes =  ubl.getNotesForUsers(keepRecord.getUsername());
        return notes;
    }

    public void setNotes(Collection<Notes> notes) {
        this.notes = notes;
    }

    public Collection<FaqMst> getFaqs() {
        faqs = fbl.getFaqsForUsers(keepRecord.getUsername());
        return faqs;
    }

    public void setFaqs(Collection<FaqMst> faqs) {
        this.faqs = faqs;
    }
    
    public String navigateToDetailFaq(Integer faqId) {
        return "/user/faq/FaqDetails.jsf?faces-redirect=true&faqId=" + faqId;
    }

    public String navigateToDetailNotes(Integer nId) {
        return "/user/notes/NoteDetails.jsf?faces-redirect=true&noteId=" + nId;
    }

    public String navigateToDetailPosts(Integer nId) {
        return "/user/posts/PostDetails.jsf?faces-redirect=true&postId=" + nId;
    }

    public UserBeanLocal getUbl() {
        return ubl;
    }

    public void setUbl(UserBeanLocal ubl) {
        this.ubl = ubl;
    }

    public FaqBeanLocal getFbl() {
        return fbl;
    }

    public void setFbl(FaqBeanLocal fbl) {
        this.fbl = fbl;
    }

    public Collection<PostFeedMst> getPosts() {
        posts = ubl.getUserByEmail(keepRecord.getUsername()).getPostFeedMstCollection();
        return posts;
    }

    public void setPosts(Collection<PostFeedMst> posts) {
        this.posts = posts;
    }

    public PostBeanLocal getPbl() {
        return pbl;
    }

    public void setPbl(PostBeanLocal pbl) {
        this.pbl = pbl;
    }

    public CommunityBeanLocal getCbl() {
        return cbl;
    }

    public void setCbl(CommunityBeanLocal cbl) {
        this.cbl = cbl;
    }

    public Collection<CommunityMst> getCommunities() {
        communities = cbl.getMyCommunities(keepRecord.getUsername());
        return communities;
    }

    public void setCommunities(Collection<CommunityMst> communities) {
        this.communities = communities;
    }
    
    
    
    
}

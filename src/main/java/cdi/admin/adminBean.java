/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi.admin;

import api.CommunityApi;
import api.FaqApi;
import api.PostApi;
import api.UserApi;
import auth.KeepRecord;
import com.techsavvy.mindsmeet.entity.CommunityMst;
import com.techsavvy.mindsmeet.entity.FaqMst;
import com.techsavvy.mindsmeet.entity.MsgMst;
import com.techsavvy.mindsmeet.entity.Notes;
import com.techsavvy.mindsmeet.entity.PostFeedMst;
import com.techsavvy.mindsmeet.entity.Users;
import ejb.UserBeanLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Acer
 */
@Named(value = "admin")
@SessionScoped
public class adminBean implements Serializable {
    @Inject KeepRecord keepRecord;
//    private UserApi api = new UserApi(keepRecord.getToken());
    private UserApi api = new UserApi();
    private FaqApi faqApi = new FaqApi();
    private PostApi postApi = new PostApi();
    private CommunityApi communityApi = new CommunityApi();
    
    
    private Collection<Users> users ;
    private Collection<PostFeedMst> posts;
    private Collection<FaqMst> faqs;
    private Collection<CommunityMst> communities;
    private Collection<Notes> notes;

    /**
     * Creates a new instance of adminBean
     */
    public adminBean() {
    }

    public Collection<Users> getUsers() {
        Response res = api.getAllUsers(Response.class);
        users = res.readEntity(new GenericType<Collection<Users>>(){});
        return users;
    }

    public void setUsers(Collection<Users> users) {
        this.users = users;
    }

    public UserApi getApi() {
        return api;
    }

    public void setApi(UserApi api) {
        this.api = api;
    }

    public Collection<PostFeedMst> getPosts() {
        Response res = postApi.viewPosts(Response.class);
        posts = res.readEntity(new GenericType<Collection<PostFeedMst>>(){});
        return posts;
    }

    public void setPosts(Collection<PostFeedMst> posts) {
        this.posts = posts;
    }

    public Collection<FaqMst> getFaqs() {
        Response res =  faqApi.getAllFaqs(Response.class);
        faqs = res.readEntity(new GenericType<Collection<FaqMst>>(){});
        return faqs;
    }

    public void setFaqs(Collection<FaqMst> faqs) {
        this.faqs = faqs;
    }

    public Collection<CommunityMst> getCommunities() {
        Response res = communityApi.getAllCommunities(Response.class);
        communities = res.readEntity(new GenericType<Collection<CommunityMst>>(){});
        return communities;
    }

    public void setCommunities(Collection<CommunityMst> communities) {
        this.communities = communities;
    }

    public Collection<Notes> getNotes() {
        Response res = api.getAllNotes(Response.class);
        notes = res.readEntity(new GenericType<Collection<Notes>>(){});
        return notes;
    }

    public void setNotes(Collection<Notes> notes) {
        this.notes = notes;
    }
    
    
    
}

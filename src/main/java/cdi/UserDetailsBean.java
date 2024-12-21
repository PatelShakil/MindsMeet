/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import api.UserApi;
import com.techsavvy.mindsmeet.entity.Users;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;
import utils.Utils;

/**
 *
 * @author M.SHAKIL PATEL
 */
@Named(value = "userDetailsBean")
@SessionScoped
public class UserDetailsBean implements Serializable {
    private UserApi api;
    private Users user;
    private String username;

    /**
     * Creates a new instance of UserDetailsBean
     */
    public UserDetailsBean() {
        api = new UserApi();
        getUsername();
    }

    public Users getUser() {
        username = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
        if (username != null) {
            try {
                Response res = api.getUserByUsername(Response.class, username);
                user = res.readEntity(Users.class);
                user.setProfile(Utils.IMAGES_URL + user.getProfile());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage("No User Found!!!", new FacesMessage("No User Found!!!"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("No User Found!!!", new FacesMessage("No User Found!!!"));

        }
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
}

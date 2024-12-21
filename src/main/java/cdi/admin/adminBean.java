/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi.admin;

import api.UserApi;
import com.techsavvy.mindsmeet.entity.Users;
import ejb.UserBeanLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Acer
 */
@Named(value = "admin")
@SessionScoped
public class adminBean implements Serializable {
    @EJB UserBeanLocal ubl;
    private UserApi api = new UserApi();
    
    private Collection<Users> users ;

    /**
     * Creates a new instance of adminBean
     */
    public adminBean() {
    }

    public UserBeanLocal getUbl() {
        return ubl;
    }

    public void setUbl(UserBeanLocal ubl) {
        this.ubl = ubl;
    }

    public Collection<Users> getUsers() {
        Response res = api.getAllUsers(Response.class);
        users = res.readEntity(new GenericType<Collection<Users>>(){});
        return users;
    }

    public void setUsers(Collection<Users> users) {
        this.users = users;
    }
    
}

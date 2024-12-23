/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import auth.KeepRecord;
import com.techsavvy.mindsmeet.entity.Users;
import ejb.UserBeanLocal;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import utils.Utils;

/**
 *
 * @author M.SHAKIL PATEL
 */
@Named(value = "utility")
@SessionScoped
public class Utility implements Serializable {
    private Utils util = new Utils();
    @EJB UserBeanLocal ubl;
    @Inject KeepRecord keepRecord;
    
    
    private String APP_PATH = Utils.APP_URL;
    private String IMAGES_PATH = Utils.IMAGES_URL;
    
    private String role;
   
    public String getRole() {
        role  = keepRecord.getRoles().stream().findFirst().get();
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    

    public UserBeanLocal getUbl() {
        return ubl;
    }

    public void setUbl(UserBeanLocal ubl) {
        this.ubl = ubl;
    }

    public String getIMAGES_PATH() {
        return IMAGES_PATH;
    }

    public void setIMAGES_PATH(String IMAGES_PATH) {
        this.IMAGES_PATH = IMAGES_PATH;
    }
    
    
    
    private String username;
    
    private Users user;

    public Users getUser() {
        if(keepRecord.getUsername() != null){
            user = ubl.getUserByEmail(keepRecord.getUsername());
            user.setProfile(Utils.IMAGES_URL + user.getProfile());
        }
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    

    public String getUsername() {
        username = keepRecord.getUsername();
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    

    public String getAPP_PATH() {
        return APP_PATH;
    }

    public void setAPP_PATH(String APP_PATH) {
        this.APP_PATH = APP_PATH;
    }
    
    


    
    

    /**
     * Creates a new instance of Utility
     */
    public Utility() {
    }

    public Utils getUtil() {
        return util;
    }

    public void setUtil(Utils util) {
        this.util = util;
    }
    
    public int getListSize(Collection<Object> c){
        System.out.println(c.toString());
        return c.size();
    }
    
}

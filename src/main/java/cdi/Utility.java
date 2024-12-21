/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import auth.KeepRecord;
import com.techsavvy.mindsmeet.entity.Users;
import ejb.UserBeanLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import utils.Utils;

/**
 *
 * @author M.SHAKIL PATEL
 */
@Named(value = "utility")
@ApplicationScoped
public class Utility {
    private Utils util = new Utils();
    @EJB UserBeanLocal ubl;
    
    private String token = KeepRecord.getToken();
    
    private String APP_PATH = Utils.APP_URL;
    private String IMAGES_PATH = Utils.IMAGES_URL;

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
    
    
    
    private String username = KeepRecord.getUsername();
    
    private Users user;

    public Users getUser() {
        if(KeepRecord.getUsername() != null){
            user = ubl.getUserByEmail(KeepRecord.getUsername());
            user.setProfile(Utils.IMAGES_URL + user.getProfile());
        }
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    

    public String getUsername() {
        username = KeepRecord.getUsername();
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
    
    

    public String getToken() {
        token = KeepRecord.getToken();
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
    
}

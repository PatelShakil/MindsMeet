/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi.chat;

import auth.KeepRecord;
import com.techsavvy.mindsmeet.entity.Users;
import ejb.UserBeanLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Acer
 */
@Named(value = "chat")
@ViewScoped
public class ChatBean implements Serializable {
    
    @EJB UserBeanLocal ubl;
    
    private Collection<Users> users ;
    
    private Users sender;
    private Users receiver;
    
    private String searchText;
    
    

    /**
     * Creates a new instance of ChatBean
     */
    public ChatBean() {
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
    
    
    
    public void onSearch(AjaxBehaviorEvent event) {
        System.out.println(searchText);
        if (searchText != null && !searchText.isEmpty()) {
            users = users.stream()
                    .filter(user -> user.getUsername().toLowerCase().contains(searchText.toLowerCase()))
                    .collect(Collectors.toList()); // Terminal operation to collect results
        } else {
            // Reload all users if searchText is empty
            users = getUsers(); // Replace with your method to reload the original list
        }
    }


    public UserBeanLocal getUbl() {
        return ubl;
    }

    public void setUbl(UserBeanLocal ubl) {
        this.ubl = ubl;
    }

    public Collection<Users> getUsers() {
        users = (Collection<Users>) ubl.getAllUsers().getEntity();
        users.remove(sender);
        return users;
    }

    public void setUsers(Collection<Users> users) {
        this.users = users;
    }

    public Users getSender() {
        sender = ubl.getUserByEmail(KeepRecord.getUsername());
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    public Users getReceiver() {
        return receiver;
    }

    public void setReceiver(Users receiver) {
        this.receiver = receiver;
    }
    
    
    
}

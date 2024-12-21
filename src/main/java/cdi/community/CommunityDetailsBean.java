/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi.community;

import api.UserApi;
import auth.KeepRecord;
import com.techsavvy.mindsmeet.entity.CommunityMembers;
import com.techsavvy.mindsmeet.entity.CommunityMst;
import com.techsavvy.mindsmeet.entity.Users;
import ejb.CommunityBeanLocal;
import ejb.UserBean;
import ejb.UserBeanLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Acer
 */
@Named(value = "comDetails")
@SessionScoped
public class CommunityDetailsBean implements Serializable {

    private CommunityMst com;

    private UserApi api = new UserApi();
    
    private Users sender ;

    @EJB
    CommunityBeanLocal cbl;
    @EJB
    UserBeanLocal ubl;

    private Collection<Users> allUsers;
    private Collection<Integer> selectedUsers;

    private Collection<CommunityMembers> members;

    private Collection<CommunityMembers> addedMembers;

    /**
     * Creates a new instance of CommunityDetailsBean
     */
    public CommunityDetailsBean() {
    }

    public CommunityMst getCom() {
        String comId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("comId");
        if (comId.contains("?")) {
            comId = comId.split("/?")[0];
        }
        if (comId != null) {
            com = cbl.getCommunity(Integer.valueOf(comId));
        }
        return com;
    }

    public void setCom(CommunityMst com) {
        this.com = com;
    }

    public CommunityBeanLocal getCbl() {
        return cbl;
    }

    public void setCbl(CommunityBeanLocal cbl) {
        this.cbl = cbl;
    }

    public Collection<CommunityMembers> getMembers() {
        getSender();
        // Fetch members and ensure it's not null
        members = cbl.getAllMembers(com.getId());
        if (members == null) {
            members = Collections.emptyList(); // Replace null with an empty list
        }

        // Check if sender is associated with the community
        if ((members.isEmpty() && !isSenderAssociatedWithCommunity())
                || (!members.stream().anyMatch(cm -> {
                    if (cm == null || cm.getUserId() == null || sender == null || sender.getId() == null) {
                        return false; // Skip null checks
                    }
                    return Objects.equals(cm.getUserId().getId(), sender.getId());
                }) && !isSenderAssociatedWithCommunity())) {
            redirectToIndex();
        }

        return members;
    }

// Helper method to check if sender is associated with the community
    private boolean isSenderAssociatedWithCommunity() {
        if (com == null || com.getUserId() == null || sender == null) {
            return false;
        }
        return Objects.equals(com.getUserId().getId(), sender.getId());
    }

// Centralized redirection logic
    private void redirectToIndex() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf?");
        } catch (IOException ex) {
            Logger.getLogger(CommunityDetailsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setMembers(Collection<CommunityMembers> members) {
        this.members = members;
    }

    public Collection<CommunityMembers> getAddedMembers() {
        return addedMembers;
    }

    public void setAddedMembers(Collection<CommunityMembers> addedMembers) {
        this.addedMembers = addedMembers;
    }

    public UserBeanLocal getUbl() {
        return ubl;
    }

    public void setUbl(UserBeanLocal ubl) {
        this.ubl = ubl;
    }

    public Collection<Users> getAllUsers() {
        Response res = api.getAllUsers(Response.class);
        allUsers = res.readEntity(new GenericType<Collection<Users>>() {
        });
        if (!allUsers.isEmpty()) {
            allUsers.remove(com.getUserId());
            for (CommunityMembers cm : members) {
                allUsers.remove(cm.getUserId());
            }
        }
        return allUsers;
    }

    public void setAllUsers(Collection<Users> allUsers) {
        this.allUsers = allUsers;
    }

    public Collection<Integer> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(Collection<Integer> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public String addMembers() {
        if (!selectedUsers.isEmpty()) {
            addedMembers = new ArrayList<>();
            System.out.println(selectedUsers.toString());
            for (Integer u : selectedUsers) {
                CommunityMembers cm = new CommunityMembers();
                Users u1 = new Users();
                u1.setId(u);
                cm.setUserId(u1);
                cm.setCommunityId(com);
                addedMembers.add(cm);
            }

            if (!addedMembers.isEmpty()) {
                cbl.addMembersToCommunity(addedMembers);
            }
        }

        return "Community.jsf?faces-redirect=true&comId=" + com.getId();
    }

    public Boolean isOwner() {
        return (KeepRecord.getUsername() == null ? com.getUserId().getEmail() == null : KeepRecord.getUsername().equals(com.getUserId().getEmail()));
    }

    public UserApi getApi() {
        return api;
    }

    public void setApi(UserApi api) {
        this.api = api;
    }

    public Users getSender() {
        sender = ubl.getUserByEmail(KeepRecord.getUsername());
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }
    
    

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi.notes;

import api.UserApi;
import auth.KeepRecord;
import com.techsavvy.mindsmeet.entity.Notes;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 *
 * @author M.SHAKIL PATEL
 */
@Named(value = "viewNotesBean")
@ViewScoped
public class ViewNotesBean implements Serializable {

    /**
     * Creates a new instance of ViewNotesBean
     */
   
    @Inject KeepRecord keepRecord;
    
    private Collection<Notes> notes;
    private UserApi userApi;

    public ViewNotesBean() {
        // Initialize UserApi
//        userApi = new UserApi(keepRecord.getToken()); // Ensure proper dependency injection if applicable
        userApi = new UserApi(); // Ensure proper dependency injection if applicable

        // Load notes from UserApi
        try {
            notes = userApi.getAllNotes(Response.class).readEntity(Collection.class); // Adjust 'getData()' to match your Response structure
        } catch (Exception e) {
            e.printStackTrace(); // Log or handle errors as needed
        }
    }

    public Collection<Notes> getNotes() {
        return notes;
    }

    
    public String navigateToDetail(Integer id) {
       
        return "NoteDetail.xhtml?faces-redirect=true&noteId=" + id;
    }

    
}

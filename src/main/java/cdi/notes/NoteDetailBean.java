/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi.notes;

import api.UserApi;
import com.techsavvy.mindsmeet.entity.Notes;
import com.techsavvy.mindsmeet.entity.NotesText;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.Response;
import utils.Utils;

/**
 *
 * @author M.SHAKIL PATEL
 */
@Named(value = "noteDetailBean")
@ViewScoped
public class NoteDetailBean implements Serializable {

    private Notes note;
    private UserApi userApi;
    private List<String> textPages;
    private int currentPage;
    private int totalPages;
    private InputStream file;

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }
    

    public List<String> getTextPages() {
        return textPages;
    }

    public void setTextPages(List<String> textPages) {
        this.textPages = textPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    
    
    public String getCurrentPageText() {
        return currentPage > 0 && currentPage <= totalPages ? textPages.get(currentPage - 1) : "";
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
        }
    }

    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
        }
    }

 
    public NoteDetailBean() {
        userApi = new UserApi() ; // Ensure proper dependency injection if applicable

        String noteId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("noteId");
        if (noteId != null) {
            try {
                note = userApi.getNotesById(Response.class,noteId).readEntity(Notes.class);
                note.setFile(Utils.PDF_URL+ note.getFile());// Replace with the actual method to fetch note by ID
//                file = new URL(note.getFile()).openStream();
                    note.setNotesTextCollection(note.getNotesTextCollection().stream()
                        .sorted(Comparator.comparingInt(NotesText::getPageNo))
                        .collect(Collectors.toList()));
                    this.textPages =note.getNotesTextCollection().stream()
                        .map(NotesText::getContent)
                        .collect(Collectors.toList()); // Replace with method that splits the content into pages
                this.currentPage = 1;
                this.totalPages = textPages.size();
            } catch (Exception e) {
                e.printStackTrace(); // Log or handle errors as needed
            }
        }
    }

    public Notes getNote() {
        return note;
    }
    
    
    
}

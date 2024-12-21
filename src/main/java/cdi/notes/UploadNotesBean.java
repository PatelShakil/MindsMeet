/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi.notes;

import api.UserApi;
import auth.KeepRecord;
import com.techsavvy.mindsmeet.entity.Notes;
import com.techsavvy.mindsmeet.entity.NotesText;
import com.techsavvy.mindsmeet.entity.Users;
import ejb.UserBeanLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.Response;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import utils.Utils;

/**
 *
 * @author M.SHAKIL PATEL
 */
@Named(value = "uploadNotesBean")
@ViewScoped
public class UploadNotesBean implements Serializable{

    private String title;
    private String description;
    private Boolean isPublic;
    private String file;
    private Collection<NotesText> textList;
    
    private UploadedFile uploadedFile;
    /**
     * Creates a new instance of UploadNotesBean
     */
    public UploadNotesBean() {
    }
    
    private final UserApi api = new UserApi();

    public Collection<NotesText> getTextList() {
        return textList;
    }

    public void setTextList(Collection<NotesText> textList) {
        this.textList = textList;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
    
    public String upload(FileUploadEvent event){
        uploadedFile = event.getFile();
        System.out.println(uploadedFile.getFileName());

        if (uploadedFile == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No file uploaded!", null));
            return "";
        }

        try {
            String resourcesFolderPath = Utils.PDF_PATH;
            String sanitizedFileName = Utils.getFormattedDate("ddMMMyyyyhhmmssa") + "_"
                    + uploadedFile.getFileName().replaceAll(" ", "").replaceAll("[<>:\"/\\\\|?*]", "_");
            File targetFile = new File(resourcesFolderPath, sanitizedFileName);

            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }

            try (InputStream input = uploadedFile.getInputStream(); FileOutputStream output = new FileOutputStream(targetFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }

            file = targetFile.getName();
            textList = Utils.getTextFromPdf(Utils.PDF_PATH + file);

        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "File upload failed: " + e.getMessage(), null));
            e.printStackTrace();
        }
        return "";
    }

    public String submit() {
        if(file == null){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "File upload failed " , null));

            return "";
        }
            Notes note = new Notes();
            note.setTitle(title);
            note.setDescription(description);
            note.setFile(file);
            note.setNotesTextCollection(textList);
            note.setIsPublic(isPublic);

            Users user = new Users();
            user.setEmail(KeepRecord.getUsername());
            note.setUserId(user);

            Response res = api.uploadNote(note, Response.class);
            String message = res.readEntity(String.class);

            if (res.getStatus() == 200) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }

        return "ViewNotes.jsf"; // Stay on the same page
    }

    
    
    
}

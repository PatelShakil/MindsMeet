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
    
    /**
     * Creates a new instance of UploadNotesBean
     */
    public UploadNotesBean() {
    }
    
    private final UserApi api = new UserApi();

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
    
    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();

        if (uploadedFile == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload failed: File is null", null));
            return;
        }

        try {
            String resourcesFolderPath = Utils.PDF_PATH;
            System.out.println("Resource path: " + resourcesFolderPath);

            String sanitizedFileName = Utils.getFormattedDate("ddMMMyyyyhhmmssa") + "_" + uploadedFile.getFileName().replaceAll("[<>:\"/\\\\|?*]", "_");
            File targetFile = new File(resourcesFolderPath, sanitizedFileName);

            if (!targetFile.exists()) {
                if (!targetFile.createNewFile()) {
                    throw new IOException("File could not be created");
                }
            }

            try (InputStream input = uploadedFile.getInputStream(); FileOutputStream output = new FileOutputStream(targetFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }

            // Store the uploaded file path for display
            file = targetFile.getName();
            textList = Utils.getTextFromPdf(Utils.PDF_URL + file);
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload failed: " + e.getMessage(), null));
        }
    }
    
    public void submit(){
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
            
            if(res.getStatus() == 200){
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,res.readEntity(String.class) , null));
            }else{
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, res.readEntity(String.class), null));
            }
    }

    
    
    
}

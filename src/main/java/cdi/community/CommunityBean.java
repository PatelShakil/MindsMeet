/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi.community;

import auth.KeepRecord;
import com.techsavvy.mindsmeet.entity.CommunityMst;
import com.techsavvy.mindsmeet.entity.Users;
import ejb.CommunityBeanLocal;
import ejb.UserBeanLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import utils.Utils;

/**
 *
 * @author Acer
 */
@Named(value = "community")
@SessionScoped
public class CommunityBean implements Serializable {
    @Inject
    KeepRecord keepRecord;

    @EJB
    CommunityBeanLocal cbl;
    @EJB UserBeanLocal ubl;

    private Collection<CommunityMst> joinedCommunities;
    private Collection<CommunityMst> myCommunities;
    private Collection<CommunityMst> allCommunities;

    private String name;
    private String photo;
    private String description;
    
    private UploadedFile file;


    /**
     * Creates a new instance of CommunityBean
     */
    public CommunityBean() {
    }

    public CommunityBeanLocal getCbl() {
        return cbl;
    }

    public void setCbl(CommunityBeanLocal cbl) {
        this.cbl = cbl;
    }

    public Collection<CommunityMst> getJoinedCommunities() {
        joinedCommunities = cbl.getJoinedCommunities(keepRecord.getUsername());
        return joinedCommunities;
    }

    public void setJoinedCommunities(Collection<CommunityMst> joinedCommunities) {
        this.joinedCommunities = joinedCommunities;
    }

    public Collection<CommunityMst> getMyCommunities() {
        myCommunities = cbl.getMyCommunities(keepRecord.getUsername());
        return myCommunities;
    }

    public void setMyCommunities(Collection<CommunityMst> myCommunities) {
        this.myCommunities = myCommunities;
    }

    public Collection<CommunityMst> getAllCommunities() {
        allCommunities = cbl.getAllCommunities();
        for(CommunityMst mc : myCommunities){
            allCommunities.remove(mc);
        }
        
        for(CommunityMst jc : joinedCommunities){
            allCommunities.remove(jc);
        }
        
        return allCommunities;
    }

    public void setAllCommunities(Collection<CommunityMst> allCommunities) {
        this.allCommunities = allCommunities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String addCommunity() {
        if (!photo.isEmpty() && !name.isEmpty() && !description.isEmpty()) {
            CommunityMst cm = new CommunityMst();
            cm.setName(name);
            cm.setProfile(photo);
            cm.setDescription(description);
            Users user = new Users();
            user.setEmail(keepRecord.getUsername());
            cm.setUserId(user);
            cbl.createCommunity(cm);
            name= "";
            photo="";
            description = "";
        }else{
            //Show Error
            System.out.println("Please enter data");
        }
        return "index.jsf?faces-redirect=true";
    }
    
    public void uploadFile(FileUploadEvent event) {
        file = event.getFile();

        try {
            if (file == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload failed: File is null", null));
            }
            String resourcesFolderPath = Utils.IMAGES_PATH;

            String sanitizedFileName = Utils.getFormattedDate("ddMMMyyyyhhmmssa") + "_" + file.getFileName().replaceAll(" ", "_").replaceAll("[<>:\"/\\\\|?*]", "_");
            File targetFile = new File(resourcesFolderPath, sanitizedFileName);

            if (!targetFile.exists()) {
                if (!targetFile.createNewFile()) {
                    throw new IOException("File could not be created");
                }
            }

            try (InputStream input = file.getInputStream(); FileOutputStream output = new FileOutputStream(targetFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }

            // Store the uploaded file path for display
            photo = targetFile.getName();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "File uploaded successfully", null));

        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload failed: " + e.getMessage(), null));
        }

    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public String joinCommunity(Integer cId){
        Users sender = ubl.getUserByEmail(keepRecord.getUsername());
        cbl.joinCommunity(cId, sender.getId());
        return "index.jsf?faces-redirect=true";
    }
    

}

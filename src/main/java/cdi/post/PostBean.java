/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi.post;

import api.PostApi;
import auth.KeepRecord;
import com.techsavvy.mindsmeet.entity.PostFeedMst;
import com.techsavvy.mindsmeet.entity.Users;
import ejb.PostBeanLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import utils.Utils;

/**
 *
 * @author Acer
 */
@Named(value = "post")
@ViewScoped
public class PostBean implements Serializable {

    private PostApi api = new PostApi();
    @EJB PostBeanLocal pbl;

    private Collection<PostFeedMst> posts;

    private UploadedFile file;

    private String photo;
    private String caption;
    private String location;

    /**
     * Creates a new instance of PostBean
     */
    public PostBean() {
    }

    public PostApi getApi() {
        return api;
    }

    public void setApi(PostApi api) {
        this.api = api;
    }

    public Collection<PostFeedMst> getPosts() {
//        Response res = api.viewPosts(Response.class);
//        posts = res.readEntity(new GenericType<Collection<PostFeedMst>>() {});
        posts = pbl.viewPosts();
        return posts;
    }

    public void setPosts(Collection<PostFeedMst> posts) {
        this.posts = posts;
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

    public String addPost() {
        PostFeedMst pfm = new PostFeedMst();
        pfm.setCaption(caption);
        pfm.setLocation(location);
        pfm.setPhoto(photo);
        Users user = new Users();
        user.setEmail(KeepRecord.getUsername());
        pfm.setUserId(user);
        
        api.doPost(pfm);
       
        
        photo = "";
        file = null;
        location = "";
        caption = "";
        
        return "index.jsf?faces-redirect=true";
    }


    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String doLike(Integer postId) {
        pbl.likeOnPost(postId, KeepRecord.getUsername());
        posts = pbl.viewPosts(); // Reload posts to reflect the updated state
        return null; // No navigation, stay on the same page
    }

    public int getLikesCount(PostFeedMst post) {
        if (post != null) {
            return pbl.getPostLikes(post.getId()).size();
        }
        return 0;
    }

    public boolean isPostLiked(PostFeedMst post) {
        return pbl.getPostLikes(post.getId()).stream()
                .anyMatch(like -> like.getUserId().getEmail().equals(KeepRecord.getUsername()));
    }
    
    


    
}

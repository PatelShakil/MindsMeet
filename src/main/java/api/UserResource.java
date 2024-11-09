/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package api;

import org.glassfish.jersey.media.multipart.FormDataParam;
import com.techsavvy.mindsmeet.entity.Notes;
import com.techsavvy.mindsmeet.entity.UserSettings;
import com.techsavvy.mindsmeet.entity.Users;
import ejb.UserBeanLocal;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import jwt.TokenProvider;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import utils.Resource;

/**
 * REST Web Service
 *
 * @author M.SHAKIL PATEL
 */
@Path("v1")
@RequestScoped
public class UserResource {
    /**
     * Creates a new instance of UserResource
     */
    @Inject
    IdentityStoreHandler handler;
    
    CredentialValidationResult result;
    AuthenticationStatus status;
    
    @Inject
    TokenProvider tokenProvider;
    
    @Context
    private HttpServletRequest request;
    
        @Inject
    private Pbkdf2PasswordHash passwordHasher;

    public UserResource() {
    }
 
    @EJB UserBeanLocal ubl;
    
    
    @POST
    @Path("user/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Resource<String> doLogin(Users user){
        Resource<String> res = new Resource<>(null, "", false);
        Credential credential = new UsernamePasswordCredential(user.getEmail(), new Password(user.getPassword()));
        result = handler.validate(credential);
        if (result.getStatus() == CredentialValidationResult.Status.VALID) {
            String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), false);
            // Assuming you have a method to generate JWT tokens
            res.setObj(jwt);
            res.setMessage("Login successful");
            res.setStatus(true);
        } else {
            res.setMessage("Email or Password Wrong!!!");
        }
        return res;
    }
    
    @POST
    @Path("user/signup")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Resource<Users> doSignup(Users user){
        System.out.print(user);
        return ubl.doSignup(user);
    }
    
   
    @GET
    @Path("all-users")
    @Produces(MediaType.APPLICATION_JSON)
    public Resource<Collection<Users>> getAllUsers(){
        return ubl.getAllUsers();
    }
    
    @POST
    @Path("user/update-setting")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Resource<Boolean> updateSetting(UserSettings userSetting){
        System.out.println(userSetting.toString());
        UserSettings userSettings = ubl.getSetting(userSetting.getId()).getObj();
        userSettings.setIsChatEnabled(userSetting.getIsChatEnabled());
        userSettings.setIsCommunityEnabled(userSetting.getIsCommunityEnabled());
        userSettings.setIsFeedEnabled(userSetting.getIsFeedEnabled());
        userSettings.setIsNotesEnabled(userSetting.getIsNotesEnabled());
        userSettings.setIsPrivate(userSetting.getIsPrivate());
        return ubl.updateSetting(userSettings);
    }
    
    @PUT
    @Path("user/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Resource<Boolean> updateSetting(Users user){
        Users mUser = ubl.getUser(user.getId()).getObj();
        mUser.setName(user.getName());
        mUser.setUsername(user.getUsername());
        mUser.setPhone(user.getPhone());
        mUser.setProfile(user.getProfile());
        return ubl.updateUser(mUser);
    }
    
    @POST
    @Path("note/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Resource<Boolean> uploadNote(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @FormDataParam("title") String title,
            @FormDataParam("description") String description) {

        // Define the public directory path
        String uploadDir = "D:ICT/JAVA/MindsMeet/uploads";
        String filePath = uploadDir + File.separator + fileDetail.getFileName();

        // Save the file
        saveFile(uploadedInputStream, filePath);

        // Create and persist the Notes object
        Notes note = new Notes();
        note.setTitle(title);
        note.setDescription(description);
        note.setFile(filePath); // Save the file path or URL

        Resource<Boolean> res = new Resource<>(true, "Note uploaded successfully", true);
        return res;
    }
    
    // Method to handle the file upload
    public void saveFile(InputStream uploadedInputStream, String filePath) {
        try {
            File targetFile = new File(filePath);
            Files.copy(uploadedInputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception properly
        }
    }

}

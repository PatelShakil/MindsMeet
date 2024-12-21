/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package api;

import chat.ChatBean;
import com.techsavvy.mindsmeet.entity.FaqMst;
import com.techsavvy.mindsmeet.entity.MsgMst;
import com.techsavvy.mindsmeet.entity.NoteComments;
import com.techsavvy.mindsmeet.entity.NoteReplies;
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
import javax.ws.rs.core.Response;
import jwt.TokenProvider;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
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

    @EJB
    UserBeanLocal ubl;
    @EJB ChatBean cb;

    @POST
    @Path("user/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doLogin(Users user) {
        Resource<String> res = new Resource<>(null, "", false);
        Users u = ubl.getUserByEmail(user.getEmail());
        Pbkdf2PasswordHashImpl ph = new Pbkdf2PasswordHashImpl();
        if (u != null && u.getPassword() == ph.generate(user.getPassword().toCharArray())) {
            String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), true);
            // Assuming you have a method to gddenerate JWT tokens
            res.setObj(jwt);
            res.setMessage("Login successful");
            res.setStatus(true);
        } else {
            res.setMessage("Email or Password Wrong!!!");
        }
        return Response.ok(res).build();
    }

    @POST
    @Path("user/signup")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doSignup(Users user) {
        System.out.print(user);
        user.setIsActive(Boolean.TRUE);
        user.setIsBlocked(false);
        return ubl.doSignup(user);
    }

    @GET
    @Path("all-users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        return ubl.getAllUsers();
    }

    @POST
    @Path("user/update-setting")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSetting(UserSettings userSetting) {
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
    public Response updateSetting(Users user) {
        Users mUser = ubl.getUser(user.getId()).getObj();
        mUser.setName(user.getName());
        mUser.setUsername(user.getUsername());
        mUser.setPhone(user.getPhone());
        mUser.setProfile(user.getProfile());
        return ubl.updateUser(mUser);
    }
    
    @GET
    @Path("user/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUsername(@PathParam("username") String username){
        return ubl.getUserByUsername(username);
    }

    @POST
    @Path("note/upload")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadNote(Notes note) {
        note.setIsCommentable(Boolean.TRUE);
        note.setIsTranslatable(Boolean.TRUE);
        note.setIsVerified(false);
        try {
            Users user = ubl.getUserByEmail(note.getUserId().getEmail());
            if (user != null) {
                note.setUserId(user);
            }else{
                throw new Exception();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

        }
        return ubl.uploadNote(note);
    }

//    @POST
//    @Path("note/upload")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Resource<Boolean> uploadNote(
//            @FormDataParam("file") InputStream uploadedInputStream,
//            @FormDataParam("file") FormDataContentDisposition fileDetail,
//            @FormDataParam("title") String title,
//            @FormDataParam("description") String description) {
//
//        // Define the public directory path
//        String uploadDir = "D:ICT/JAVA/MindsMeet/uploads";
//        String filePath = uploadDir + File.separator + fileDetail.getFileName();
//
//        // Save the file
//        saveFile(uploadedInputStream, filePath);
//
//        // Create and persist the Notes object
//        Notes note = new Notes();
//        note.setTitle(title);
//        note.setDescription(description);
//        note.setFile(filePath); // Save the file path or URL
//        return ubl.uploadNote(note);
//    }
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

    @GET
    @Path("note/get-all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNotes() {
        return ubl.viewNotes();
    }

    @GET
    @Path("note/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotesById(@PathParam("id") Integer id) {
        return ubl.getNoteById(id);
    }

    @POST
    @Path("note/comment/{note_id}/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doCommentOnNote(NoteComments nc, @PathParam("note_id") Integer noteId, @PathParam("user_id") Integer userId) {
        System.out.print(userId);
        return ubl.doCommentOnNotes(nc.getCommentText(), noteId, userId);
    }

    @POST
    @Path("note/comment/reply/{comment_id}/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doCommentReply(NoteReplies nr, @PathParam("comment_id") Integer commentId, @PathParam("user_id") Integer userId) {
        NoteComments nc = ubl.getNoteComment(commentId).readEntity(NoteComments.class);
        nr.setCommentId(nc);
        Users user = ubl.getUser(userId).getObj();
        nr.setUserId(user);
        return ubl.replyNotesComments(nr);
    }

  

    
    @GET
    @Path("chat/history/{senderId}/{receiverId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<MsgMst> getAllMessages1(@PathParam("receiverId")Integer id,@PathParam("senderId")Integer sId){
        return cb.getMessages(sId, id);
    }
    
    @GET
    @Path("chat/history/{receiverId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<MsgMst> getAllMessages(@PathParam("receiverId")Integer id){
        return cb.getMessagesForReceiver(id);
    }

}

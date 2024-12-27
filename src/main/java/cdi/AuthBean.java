/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import api.UserApi;
import auth.KeepRecord;
import com.techsavvy.mindsmeet.entity.Users;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.Principal;
import java.util.Set;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import utils.Resource;
import utils.Utils;

/**
 *
 * @author M.SHAKIL PATEL
 */
@Named(value = "authBean")
@SessionScoped
public class AuthBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject KeepRecord keepRecord;
    //security
    @Inject
    SecurityContext ctx;
    private AuthenticationStatus status;
    private Set<String> roles;
    private String errorstatus;

    private String email;
    private String password;
    private String uploadedFilePath;
    private String profilePath;
    //registration
    private String phone;
    private String name;
    private String username;
    private String isrememberme;

    private UserApi api = new UserApi();
    
    
    public AuthBean(){
    
    }

    private UploadedFile uploadedFile;

    public String getIsrememberme() {
        return isrememberme;
    }

    public void setIsrememberme(String isrememberme) {
        this.isrememberme = isrememberme;
    }

    public SecurityContext getCtx() {
        return ctx;
    }

    public void setCtx(SecurityContext ctx) {
        this.ctx = ctx;
    }

    public UserApi getApi() {
        return api;
    }

    public void setApi(UserApi api) {
        this.api = api;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public AuthenticationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthenticationStatus status) {
        this.status = status;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getErrorstatus() {
        return errorstatus;
    }

    public void setErrorstatus(String errorstatus) {
        this.errorstatus = errorstatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public String getUploadedFilePath() {
        return uploadedFilePath;
    }

    public String handleFileUpload(FileUploadEvent event) {
        uploadedFile = event.getFile();

        if (uploadedFile == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload failed: File is null", null));
            return "register.jsf";
        }

        try {
            String resourcesFolderPath = Utils.IMAGES_PATH;
            System.out.println("Resource path: " + resourcesFolderPath);

            String sanitizedFileName = Utils.getFormattedDate("ddMMMyyyyhhmmssa") + "_" + uploadedFile.getFileName().replaceAll(" ", "_").replaceAll("[<>:\"/\\\\|?*]", "_");
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
            uploadedFilePath = targetFile.getName();
            profilePath = Utils.IMAGES_URL + uploadedFilePath;

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "File uploaded successfully", null));
            System.out.println(profilePath);
            System.out.println(uploadedFilePath);

        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload failed: " + e.getMessage(), null));
        }
        return "register.jsf";
    }

    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

public String onRegister() {
    Users user = new Users();
    user.setEmail(email);
    user.setPassword(password);
    user.setName(name);
    user.setUsername(username);
    user.setPhone(phone);
    user.setProfile(getUploadedFilePath()); // Ensure this returns a valid, sanitized path
    
    if(user.getEmail().isEmpty() || user.getPassword().isEmpty() || user.getUsername().isEmpty() || user.getName().isEmpty()){
// Handle failed authentication after registration
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration Failed", "Unable to perform registration. Please Enter all fields !!!"));
        return "/auth/login.jsf";    }

    try {
        // Perform the registration API call
        Response res = api.doSignup(user, Response.class);
        api.close();

        // Check if the registration was successful
//        if (res == null || res.getStatus() == 200) {
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration Failed", "Unable to register user. Please try again."));
//            return "/auth/register.jsf";
//        }

        // Perform automatic login
        Credential credential = new UsernamePasswordCredential(email, new Password(password));
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        status = ctx.authenticate(request, response, withParams().credential(credential));

        if (status == AuthenticationStatus.SUCCESS) {
            roles = ctx.isCallerInRole("Admin") ? Set.of("Admin") : Set.of("User");

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration Successful", "Welcome back!"));

            // Redirect based on role
            if (roles.contains("Admin")) {
                return "/admin/index.jsf";
            } else if (roles.contains("User")) {
                return "/user/index.jsf?jsf-redirect=true;";
            }
        }

        // Handle failed authentication after registration
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed", "Unable to log in after registration. Please log in manually."));
        return "/auth/login.jsf";

    } catch (Exception e) {
        e.printStackTrace();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An unexpected error occurred: " + e.getMessage()));
        return "/auth/register.jsf";
    } finally {
        if (api != null) {
            api.close();
        }
    }
}


public String onLogin() {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Email and Password must not be empty"));
            return "/auth/login.jsf";
        }

        try {
            System.out.println("Attempting login with email: " + email);

            Credential credential = new UsernamePasswordCredential(email, new Password(password));
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            status = ctx.authenticate(request, response, withParams().credential(credential));
            System.out.println("Authentication Status: " + status);

            if (status == AuthenticationStatus.SUCCESS) {
                Principal principal = ctx.getCallerPrincipal();
                System.out.println("Principal: " + principal);

                roles = ctx.isCallerInRole("Admin") ? Set.of("Admin") : Set.of("User");
                System.out.println("Roles: " + roles);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Successful", "Welcome back!"));

                if (roles.contains("Admin")) {
                    username = "";
                    password = "";
                    return "/admin/index.jsf?faces-redirect=true";
                } else if (roles.contains("User")) {                  
                    username = "";
                    password = "";
                    return "/user/index.jsf?faces-redirect=true";
                } else {
                    return "/auth/login.jsf";
                }
            } else if (status == AuthenticationStatus.SEND_FAILURE) {
                System.out.println("Authentication failed. Invalid credentials.");
                request.getSession().invalidate(); // Invalidate session
                logout();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed", "Invalid Credentials! Please try again."));
                return "/auth/login.jsf";
            }

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed", "Unexpected error. Please try again."));
            return "/auth/login.jsf";

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An unexpected error occurred: " + e.getMessage()));
            return "/auth/login.jsf";
        } finally {
            if (api != null) {
                api.close();
            }
        }
    }



    public String logout() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession().invalidate();
            request.logout();
            keepRecord.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/auth/login.jsf";
    }

    public void setCookie(String name, String value, int maxAgeInDays) {
        // Get the HttpServletResponse object
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();

        // Create a new Cookie object
        Cookie cookie = new Cookie(name, value);

        // Set the max age of the cookie (30 days in seconds)
        cookie.setMaxAge(maxAgeInDays * 24 * 60 * 60);


        // Set the path of the cookie to be available for the entire application
        cookie.setPath("/");

        // Add the cookie to the response
        response.addCookie(cookie);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import api.UserApi;
import com.fasterxml.jackson.core.type.TypeReference;
import com.techsavvy.mindsmeet.entity.Users;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
@ViewScoped
public class AuthBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String email;
    private String password;
    private String uploadedFilePath;
    private String profilePath;
    //registration
    private String phone;
    private String name;
    private String username;
    
    private UserApi api = new UserApi();
    

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

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();

        if (uploadedFile == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload failed: File is null", null));
            return;
        }

        try {
            String resourcesFolderPath = Utils.IMAGES_PATH;
            System.out.println("Resource path: " + resourcesFolderPath);

            String sanitizedFileName = Utils.getFormattedDate("ddMMMyyyyhhmmssa") + "_" + uploadedFile.getFileName().replaceAll("[<>:\"/\\\\|?*]", "_")  ;
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
//            System.out.print(getTextFromPdf(targetFile.getAbsolutePath()));

        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload failed: " + e.getMessage(), null));
        }
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
    
    public String onRegister(){
        Users user = new Users();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setUsername(username);
        user.setPhone(phone);
        System.out.println(this.uploadedFilePath);
        user.setProfile(getUploadedFilePath());
        
        try {
            // Perform the login call
            Response response = api.doSignup(user, Response.class);
            Resource<Users> res = response.readEntity(Resource.class);
            api.close();

            // Simulate checking the response (update this with your actual response check logic)
            if (res != null && res.getStatus()) {
                System.out.println(res.getMessage());
                // Add a success message
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration Successful", "Welcome back!"));
                setCookie("user_id", res.getObj().getId().toString(), 30);
                return "/";
            } else {
                System.out.println(res.getMessage());
//                 Add an error message for failed login
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration Failed", "Invalid credentials."));
                return "/auth/register.jsf";
            }
        } catch (Exception e) {
            api.close();
            e.printStackTrace();    
            // Add an error message for exceptions
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An unexpected error occurred: " + e.getMessage()));
                return "/auth/register.jsf";        
        }
    }
        
//    }
    
    public void onLogin() {
        System.out.println("Login Clicked");
        Users user = new Users();
        user.setEmail(email);
        user.setPassword(password);

        try {
            // Perform the login call
            Response res = api.doLogin(user, Response.class);

            // Simulate checking the response (update this with your actual response check logic)
            if (res != null && res.hasEntity()) {
                Resource<Users> us = res.readEntity(Resource.class);
                System.out.print(us);
                // Add a success message
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Successful", "Welcome back!"));
            } else {
//                System.out.println(res.getMessage());
                // Add an error message for failed login
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed", res.getEntity().toString()));
            }
            api.close();
        } catch (Exception e) {
            api.close();
            e.printStackTrace();    
            // Add an error message for exceptions
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An unexpected error occurred: " + e.getMessage()));
//        
        }
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
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package api;

import com.techsavvy.mindsmeet.entity.Users;
import ejb.UserBeanLocal;
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
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
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
    public UserResource() {
    }
 
    @EJB UserBeanLocal ubl;
    
    
    @POST
    @Path("user/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Resource<Users> doLogin(Users user){
        return ubl.doLogin(user.getEmail(), user.getPassword());
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
}

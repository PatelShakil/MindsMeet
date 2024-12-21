/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package api;

import auth.KeepRecord;
import com.techsavvy.mindsmeet.entity.FaqAnswers;
import com.techsavvy.mindsmeet.entity.FaqMst;
import com.techsavvy.mindsmeet.entity.Users;
import ejb.FaqBeanLocal;
import ejb.UserBeanLocal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import jwt.JWTCredential;
import jwt.TokenProvider;

/**
 * REST Web Service
 *
 * @author Acer
 */
@Path("faq")
@RequestScoped
public class FaqResource {
    @EJB UserBeanLocal ubl;
    @EJB FaqBeanLocal fbl;
    
    @Context
    private HttpServletRequest request;  
    
    @Inject
    private TokenProvider provider;
    
    @GET
    @Path("faq/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFaqById(@PathParam("id") Integer id) {
        return fbl.getFaqById(id);
    }

    @GET
    @Path("faq/get-all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFaqs() {
        return fbl.viewFaqs();
    }   
    @POST
    @Path("answer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response answerFaq(FaqAnswers faq){
        return fbl.answerFaq(faq);
    }
    
        
    @POST
    @Path("faq/upload")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response uploadFaq(FaqMst faq) {
        return fbl.uploadFaq(faq);
    }
}

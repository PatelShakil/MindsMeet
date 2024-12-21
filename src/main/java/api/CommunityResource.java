/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package api;

import com.techsavvy.mindsmeet.entity.CommunityMsg;
import ejb.CommunityBeanLocal;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Acer
 */
@Path("community")
public class CommunityResource {

    @EJB CommunityBeanLocal cbl;

    /**
     * Creates a new instance of CommunityResource
     */
    public CommunityResource() {
    }
    
    @GET
    @Path("get-messages/{communityId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CommunityMsg> getAllMessages(@PathParam("communityId")Integer communityId){
        return cbl.loadCommunityMsg(communityId);
    }
    
    
}

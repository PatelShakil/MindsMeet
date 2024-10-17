package com.techsavvy.mindsmeet.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("rest")
public class JakartaEE8Resource {
    
    @GET
    @Produces("application/json")
    public Response ping(){
        return Response
                .ok("{id:1,username:shakil}")
                .build();
    }
}

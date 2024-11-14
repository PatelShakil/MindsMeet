package com.techsavvy.mindsmeet;

import java.util.HashSet;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("api")
public class JakartaRestConfiguration extends Application {
//    @Override
//    public HashSet<Class<?>> getClasses() {
//        HashSet<Class<?>> resources = new HashSet<>();
//        // Register MultiPartFeature to handle multipart/form-data
//        resources.add(MultiPartFeature.class);
//
//        // Add other resource classes as needed
//        // resources.add(YourResourceClass.class);
//        return resources;
//    }
}

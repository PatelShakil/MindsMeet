package rest;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import javax.inject.Inject;
import auth.KeepRecord;

@Provider
public class MyRestFilter implements ClientRequestFilter {
    String token;
//
//    public MyRestFilter(String token) {
//        this.token = token;
//    }
//    
//    

    @Inject
    KeepRecord keepRecord;  // This will handle token retrieval if needed

    public static String mytoken;

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        // Log to ensure the filter is being invoked
        System.out.println("In MyRestFilter: Checking if token exists.");

        // Retrieve the token from KeepRecord or cookies (if KeepRecord is used)
//            token = keepRecord.getToken();

        // If KeepRecord is used to manage token, we retrieve it from there
        if (keepRecord != null) {
            System.out.println("Token from KeepRecord: " + token);
        }

        // If KeepRecord is not available, try getting the token from cookies
        System.out.println(requestContext.getCookies());
        if (token == null) {
            Cookie authCookie = requestContext.getCookies().get("token");  // Use the cookie name that stores the token
            if (authCookie != null) {
                token = authCookie.getValue();
                System.out.println("Token from Cookie: " + token);
            } else {
                System.out.println("No token found in cookies.");
            }
        }

        // If a token is found, add it as a Bearer token to the Authorization header
        if (token != null && !token.isEmpty()) {
            requestContext.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            System.out.println("Token added to request headers.");
        } else {
            System.out.println("No valid token to add to request.");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import cdi.AuthBean;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import java.io.Serializable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.CredentialValidationResult.Status;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static jwt.Constants.AUTHORIZATION_HEADER;
import static jwt.Constants.BEARER;
import jwt.JWTCredential;
import jwt.TokenProvider;
import utils.Utils;

/**
 *
 * @author root
 */
@Named
@RequestScoped
public class SecureAuthentication implements HttpAuthenticationMechanism, Serializable {

    @Inject
    IdentityStoreHandler handler;
    CredentialValidationResult result;
    @Inject
    TokenProvider tokenProvider;
    @Inject
    AuthBean lbean;
    
    @Inject KeepRecord keepRecord;
    
    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext ctx) throws AuthenticationException {
        if (request.getRequestURI().contains("api")) {
            // For API requests, skip token validation and proceed
//            ctx.notifyContainerAboutLogin("patelsakib95@gmail.com",Set.of("User"));
            return ctx.doNothing();
        }
        
        try {
            
//            if(request.getSession().isNew())
//            {
//                keepRecord.reset();
//                String loginPage = "/auth/login.jsf"; // Ensure the path is correct
//                Logger.getLogger(SecureAuthentication.class.getName()).log(Level.INFO, "Forwarding to login page: {0}", loginPage);
//
//                request.getRequestDispatcher(loginPage).forward(request, response);
//                
//            }

            System.out.println("Session Id : " + request.getSession().getId());
            
            
                       
            
            String token = extractToken(ctx);
            System.out.println("Token: " + keepRecord.getToken());

            // Admin access validation
            if (request.getRequestURI().contains("admin") && (keepRecord.getToken() == null || !keepRecord.getRoles().contains("Admin"))) {
                return sendUnauthorized(ctx, "Unauthorized access to admin section.");
            }
            
            

            // User access validation
            if (request.getRequestURI().contains("user") && keepRecord.getToken() == null) {
                forwardToLogin(request, response);
                return ctx.doNothing();
            }

            // Token-based authentication
            if (token != null) {
                return validateToken(token, ctx);
            }

            // Credential-based authentication
            if (keepRecord.getToken() == null && lbean.getEmail() != null) {
                return processCredentials(ctx);
            }

            // Revalidate existing credentials
            if (keepRecord.getToken() != null) {
                return revalidateCredentials(ctx);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(SecureAuthentication.class.getName()).log(Level.SEVERE, "Authentication error: {0}", e.getMessage());
        }

        // Default response for protected resources
        if (ctx.isProtected()) {
            return ctx.responseUnauthorized();
        }
        
        
        

        return ctx.doNothing();
    }

    private AuthenticationStatus processCredentials(HttpMessageContext ctx) {
        try {
            Credential credential = new UsernamePasswordCredential(lbean.getEmail(), new Password(lbean.getPassword()));
            result = handler.validate(credential);
            if (result.getStatus() == Status.VALID) {
                keepRecord.setUsername(lbean.getEmail());
                keepRecord.setPassword(lbean.getPassword());
                keepRecord.setPrincipal(result.getCallerPrincipal());
                keepRecord.setRoles(result.getCallerGroups());
                lbean.setRoles(result.getCallerGroups());
                return createToken(result, ctx);
            } else {
                lbean.setErrorstatus("Invalid username or password.");
                return AuthenticationStatus.SEND_FAILURE;
            }
        } catch (Exception e) {
            Logger.getLogger(SecureAuthentication.class.getName()).log(Level.SEVERE, "Credential processing error: {0}", e.getMessage());
            return AuthenticationStatus.SEND_FAILURE;
        }
    }

    private AuthenticationStatus revalidateCredentials(HttpMessageContext ctx) {
        try {
            Credential credential = new UsernamePasswordCredential(keepRecord.getUsername(), new Password(keepRecord.getPassword()));
            result = handler.validate(credential);
            if (result.getStatus() == Status.VALID) {
                return createToken(result, ctx);
            } else {
                return ctx.responseUnauthorized();
            }
        } catch (Exception e) {
            Logger.getLogger(SecureAuthentication.class.getName()).log(Level.SEVERE, "Revalidation error: {0}", e.getMessage());
            return ctx.responseUnauthorized();
        }
    }

    private void forwardToLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            String loginPage = "/auth/login.jsf"; // Ensure the path is correct
            Logger.getLogger(SecureAuthentication.class.getName()).log(Level.INFO, "Forwarding to login page: {0}", loginPage);

            request.getRequestDispatcher(loginPage).forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(SecureAuthentication.class.getName()).log(Level.SEVERE, "Error forwarding to login page: {0}", ex.getMessage());
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to forward to login page.");
            } catch (IOException e) {
                Logger.getLogger(SecureAuthentication.class.getName()).log(Level.SEVERE, "Error sending error response: {0}", e.getMessage());
            }
        }
    }

    private AuthenticationStatus sendUnauthorized(HttpMessageContext ctx, String message) {
        Logger.getLogger(SecureAuthentication.class.getName()).log(Level.WARNING, message);
        return ctx.responseUnauthorized();
    }

    private AuthenticationStatus validateToken(String token, HttpMessageContext context) {
        try {
            if (tokenProvider.validateToken(token)) {
                JWTCredential credential = tokenProvider.getCredential(token);
                // System.out.println("JWTAuthenticationMechanism-Token Validated");
                return context.notifyContainerAboutLogin(credential.getPrincipal(), credential.getAuthorities());

            }
            // if token invalid, response with unauthorized status
            return context.responseUnauthorized();
        } catch (ExpiredJwtException eje) {
            //LOGGER.log(Level.INFO, "Security exception for user {0} - {1}", new String[]{eje.getClaims().getSubject(), eje.getMessage()});
            return context.responseUnauthorized();
        }
    }

    /**
     * Create the JWT using CredentialValidationResult received from
     * IdentityStoreHandler
     *
     * @param result the result from validation of UsernamePasswordCredential
     * @param context
     * @return the AuthenticationStatus to notify the container
     */
    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
        if (!isRememberMe(context)) {
            // if (true) {
            String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), false);
            //context.getRequest().getSession().setAttribute("token", jwt);
            keepRecord.setToken(jwt);
            context.getResponse().addHeader(AUTHORIZATION_HEADER, BEARER + jwt);
//            System.out.println("Token Value" + jwt);

            Cookie cookie = new Cookie("token", jwt);
            context.getResponse().addCookie(cookie);

        }
        System.out.println("JWTAuthenticationMechanism - Token Created");

        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
    }

    /**
     * To extract the JWT from Authorization HTTP header
     *
     * @param context
     * @return The JWT access tokens
     */
    private String extractToken(HttpMessageContext context) {
        String authorizationHeader = context.getRequest().getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            String token = authorizationHeader.substring(BEARER.length(), authorizationHeader.length());
//            System.out.println("JWTAuthenticationMechanism - Extract Tokens");
            return token;
        }
        return null;
    }

    /**
     * this function invoked using RememberMe.isRememberMeExpression EL
     * expression
     *
     * @param context
     * @return The remember me flag
     */
    public Boolean isRememberMe(HttpMessageContext context) {
        return Boolean.valueOf(context.getRequest().getParameter("rememberme"));
    }
}

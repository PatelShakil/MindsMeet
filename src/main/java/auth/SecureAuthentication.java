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

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext ctx) throws AuthenticationException {
        String token = extractToken(ctx);
        System.out.println("Token : " + KeepRecord.getToken());
        System.out.println("Rest Token : " + token);

        if (request.getRequestURI().contains("user") && KeepRecord.getToken() == null) {
            try {
                //            ctx.responseUnauthorized();
                System.out.println("auth.SecureAuthentication.validateRequest()");
                request.getRequestDispatcher(Utils.APP_URL+"auth/login.jsf").forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(SecureAuthentication.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SecureAuthentication.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return ctx.doNothing();
        }
        if (request.getRequestURI().contains("admin") && KeepRecord.getToken() == null) {
            ctx.responseUnauthorized();
            return ctx.doNothing();
        }

        try {

            if (token == null && lbean.getEmail() != null) {
                String username = lbean.getEmail();
                String password = lbean.getPassword();
                Credential credential = new UsernamePasswordCredential(username, new Password(password));
                result = handler.validate(credential);
                if (result.getStatus() == Status.VALID) {
                    createToken(result, ctx);

                    ctx.notifyContainerAboutLogin(result);
                    KeepRecord.setUsername(username);
                    KeepRecord.setPassword(password);
                    KeepRecord.setPrincipal(result.getCallerPrincipal());
                    KeepRecord.setRoles(result.getCallerGroups());

                    lbean.setRoles(result.getCallerGroups());
                    return AuthenticationStatus.SUCCESS;

                } else {
                    lbean.setErrorstatus("User or Password is not correct !");
                    lbean.setStatus(AuthenticationStatus.SEND_FAILURE);
                    return AuthenticationStatus.SEND_FAILURE;
                }
            }
            if (KeepRecord.getToken() != null) {
                Credential credential1 = new UsernamePasswordCredential(KeepRecord.getUsername(), new Password(KeepRecord.getPassword()));
                result = handler.validate(credential1);
                AuthenticationStatus status = createToken(result, ctx);
                ctx.notifyContainerAboutLogin(KeepRecord.getPrincipal(), KeepRecord.getRoles());
                return AuthenticationStatus.SUCCESS;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (token != null) {
            // validation of the jwt credential

            return validateToken(token, ctx);
        } else if (ctx.isProtected()) {
            // A protected resource is a resource for which a constraint has been defined.
            // if there are no credentials and the resource is protected, we response with unauthorized status
            return ctx.responseUnauthorized();
        }
        return ctx.doNothing();
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
            KeepRecord.setToken(jwt);
            context.getResponse().addHeader(AUTHORIZATION_HEADER, BEARER + jwt);
            System.out.println("Token Value" + jwt);

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
            System.out.println("JWTAuthenticationMechanism - Extract Tokens");
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

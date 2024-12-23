/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import java.io.Serializable;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.identitystore.CredentialValidationResult;

/**
 *
 * @author root
 */

@SessionScoped
@Named
public class KeepRecord implements Serializable {

    private  CredentialValidationResult result;
    private  CallerPrincipal principal;
    private  Set<String> roles;
    private  String token;
    private  String username;
    private  String password;

    public  String getUsername() {
        return username;
    }

    public  void setUsername(String username) {
        this.username = username;
    }

    public  String getPassword() {
        return password;
    }

    public  void setPassword(String password) {
        this.password = password;
    }

    public  CredentialValidationResult getResult() {
        return result;
    }

    public  void setResult(CredentialValidationResult result) {
        this.result = result;
    }

    public  CallerPrincipal getPrincipal() {
        return principal;
    }

    public  void setPrincipal(CallerPrincipal principal) {
        this.principal = principal;
    }

    public  Set<String> getRoles() {
        return roles;
    }

    public  void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public  String getToken() {
        return token;
    }

    public  void setToken(String token) {
        this.token = token;
    }

    public  void reset() {

        principal = null;
        token = null;
    }

}

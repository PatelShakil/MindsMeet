
package jwt;

import java.io.Serializable;
import java.util.Set;
import javax.enterprise.context.SessionScoped;
import javax.security.enterprise.credential.Credential;

//@SessionScoped
public class JWTCredential implements Credential, Serializable {

    private final String principal;//email
    private final Set<String> authorities;//roles

    public JWTCredential(String principal, Set<String> authorities) {
        this.principal = principal;
        this.authorities = authorities;
    }

    public String getPrincipal() {
        return principal;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

}

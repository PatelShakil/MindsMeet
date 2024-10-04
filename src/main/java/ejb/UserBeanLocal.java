/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import com.techsavvy.mindsmeet.entity.Users;
import javax.ejb.Local;

/**
 *
 * @author M.SHAKIL PATEL
 */
@Local
public interface UserBeanLocal {
    
    public Users doLogin(String email,String password);
    public Users doSignup(String email,String password,String username);
}

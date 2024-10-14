/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.techsavvy.mindsmeet.entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import ejb.UserBeanLocal;
import java.util.Collection;
import java.util.Date;
import utils.Resource;

/**
 *
 * @author M.SHAKIL PATEL
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/test"})
public class TestServlet extends HttpServlet {
    
    @EJB UserBeanLocal ubl;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("application/json;charset=UTF-8");
    
    try (PrintWriter out = response.getWriter()) {
        Users user = new Users();
        user.setId(1);
        user.setToken("abcd");
        user.setEmail("abc@gmail.com");
        user.setPassword("12345678");
        user.setName("ABC");
        user.setUsername("abc");
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.setIsActive(true);   // Assume new users are active
        user.setIsBlocked(false); // New users are not blocked
        user.setPhone("1234567890");
        user.setBlockedUntil(new Date());
        user.setProfile("abc");


//        Resource<Users> res = ubl.doSignup(user);
//        Resource<Users> res = ubl.doLogin("abc@gmail.com","12345678");
        Resource<Collection<Users>> res = ubl.getAllUsers();
        
        // Convert the result  JSON and write to the response
        
        
        out.print(res.toString()); // Send the JSON response back to the client
        out.flush(); // Ensure the response is sent
    }
}


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

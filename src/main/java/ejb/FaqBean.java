/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import auth.KeepRecord;
import com.techsavvy.mindsmeet.entity.FaqAnswers;
import com.techsavvy.mindsmeet.entity.FaqMst;
import com.techsavvy.mindsmeet.entity.FaqVotes;
import com.techsavvy.mindsmeet.entity.Notes;
import com.techsavvy.mindsmeet.entity.Users;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import jwt.JWTCredential;
import jwt.TokenProvider;
import utils.Utils;

/**
 *
 * @author Acer
 */
@Stateless
public class FaqBean implements FaqBeanLocal {
    
    @PersistenceContext(name="my_mindsmeet_pu")
    private EntityManager em;
    
  
    
    @Inject
    private TokenProvider provider;
    
    @EJB UserBeanLocal ubl;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Response uploadFaq(FaqMst faq) {
        
        Users user = ubl.getUserByEmail(faq.getUserId().getEmail());
        faq.setUserId(user);
             
        
        
        try {
            em.persist(faq);
            if (!faq.getFaqScreenshotCollection().isEmpty()) {
                faq.getFaqScreenshotCollection().forEach((f) -> {
                    f.setFaqId(faq);
                    em.persist(f);
                });
            }
            return Response.ok("Faq Uploaded").build();
        } catch (Exception e) {
            return Utils.getCatch(e);
        }

    }

    @Override
    public Response viewFaqs() {
        try {
            return Response.ok(em.createNamedQuery("FaqMst.findAll").getResultList()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Override
    public Response answerFaq(FaqAnswers answer) {
        try {
            System.out.print(answer.getAnswer());
            // Retrieve the FAQ by ID
            System.out.print(answer.getFaqId());
            FaqMst faq = em.find(FaqMst.class, answer.retreiveFaqId());
            if (faq == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("FAQ not found").build();
            }
            Users user = ubl.getUserByEmail(answer.getUserId().getEmail());
            answer.setUserId(user);
                       
            // Set user and FAQ
            answer.setFaqId(faq);

            // Persist the answer
            em.persist(answer);

            return Response.ok("FAQ Answered Successfully!").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Utils.getCatch(e);
        }
    }


    @Override
    public Response voteFaq(Integer answerId, Boolean isUp, Integer userId) {
        try {
            FaqAnswers fa = em.find(FaqAnswers.class, answerId);
            FaqVotes fv = new FaqVotes();
//            fv.setFaqId(faqId);
            return Response.ok().build();
        } catch (Exception e) {
            return Utils.getCatch(e);
        }
    }

    @Override
    public Response editFaq(FaqMst fm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Response deleteFaq(Integer fId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Response getFaqById(Integer id) {
        try {
            FaqMst faq = em.find(FaqMst.class, id);
            if (faq != null) {
                return Response.ok(faq).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

        }
    }

    @Override
    public Collection<FaqMst> getFaqsForUsers(String email) {
        Users user = ubl.getUserByEmail(email);

        Collection<FaqMst> faqs = em.createQuery("select n from FaqMst n where n.userId = :u").setParameter("u", user).getResultList();
        return faqs;
    }

    @Override
    public Collection<FaqAnswers> getFaqAnswers(Integer id) {
        FaqMst faq = em.find(FaqMst.class,id);
        Collection<FaqAnswers> ans = em.createQuery("select a from FaqAnswers a where a.faqId.id = :faq order by a.createdAt DESC").setParameter("faq", id).getResultList();
        return ans;
    }
}

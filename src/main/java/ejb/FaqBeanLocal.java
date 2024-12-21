/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import com.techsavvy.mindsmeet.entity.FaqAnswers;
import com.techsavvy.mindsmeet.entity.FaqMst;
import com.techsavvy.mindsmeet.entity.Notes;
import java.util.Collection;
import javax.ejb.Local;
import javax.ws.rs.core.Response;

/**
 *
 * @author Acer
 */
@Local
public interface FaqBeanLocal {
    
    //faqs
    public Response viewFaqs();

    public Response uploadFaq(FaqMst faq);

    public Response answerFaq(FaqAnswers answer);

    public Response voteFaq(Integer answerId, Boolean isUp, Integer userId);

    public Response editFaq(FaqMst fm);

    public Response deleteFaq(Integer fId);

    public Response getFaqById(Integer id);
    
    public Collection<FaqMst> getFaqsForUsers(String email);
    
    public Collection<FaqAnswers> getFaqAnswers(Integer id);

}

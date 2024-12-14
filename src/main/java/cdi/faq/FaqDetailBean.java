/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi.faq;

import api.FaqApi;
import api.UserApi;
import com.techsavvy.mindsmeet.entity.FaqAnswers;
import com.techsavvy.mindsmeet.entity.FaqMst;
import ejb.FaqBeanLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;

/**
 *
 * @author Acer
 */
@Named(value = "faqDetailBean")
@SessionScoped
public class FaqDetailBean implements Serializable {
    
    @EJB FaqBeanLocal fbl;
    
    private FaqApi api = new FaqApi();
    private FaqMst faq;
    private String faqId;
            
    private String answer;
    private String code;
    private boolean showDialog = false;

    // Other fields and logic are the same as above
    public void openDialog() {
        this.showDialog = true;
    }

    public void closeDialog() {
        this.showDialog = false;
    }

    /**
     * Creates a new instance of FaqDetailBean
     */
    public FaqDetailBean() {
        faqId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("faqId");
        if(faqId != null){
            Response res = api.getFaqById(Response.class, faqId);
            faq = res.readEntity(FaqMst.class);
            
        }
        
    }

    public FaqMst getFaq() {
        return faq;
    }

    public void setFaq(FaqMst faq) {
        this.faq = faq;
    }

    public String getFaqId() {
        return faqId;
    }

    public void setFaqId(String faqId) {
        this.faqId = faqId;
    }
    
    public void submitComment() {
        FaqAnswers ans = new FaqAnswers();
        ans.setAnswer(answer);
        ans.setCode(code);
        ans.setIsAccepted(false);
        ans.setFaqId(faq);
        
        try{        
//                    Response res = api.answerFaq(ans, Response.class);

            Response res = fbl.answerFaq(ans);
        System.out.print(res);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, res.toString(), null));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
    
}

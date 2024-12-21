/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi.faq;

import api.FaqApi;
import api.UserApi;
import auth.KeepRecord;
import com.techsavvy.mindsmeet.entity.FaqMst;
import com.techsavvy.mindsmeet.entity.Users;
import java.io.Serializable;
import java.util.Collection;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author M.SHAKIL PATEL
 */
@Named(value = "faqBean")
@ViewScoped
public class FaqBean implements Serializable {

    private FaqApi api;
    
    private Collection<FaqMst> faqs;

    private String question;
    private String description;
    private String code;

    /**
     * Creates a new instance of FaqBean
     */
    public FaqBean() {
        api = new FaqApi();
    }

    public String uploadFaq() {
        FaqMst faq = new FaqMst();
        faq.setCode(code);
        faq.setQue(question);
        faq.setDescription(description);
        Users userId = new Users();
        userId.setEmail(KeepRecord.getUsername());
        faq.setUserId(userId);
        try {
            Response res = api.uploadFaq(faq, Response.class);
            String msg = res.readEntity(String.class);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
                code = "";
                question = "";
                description = "";
                return "ViewFaqs.jsf";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            return "";
        }
        

    }
    
    public String navigateToDetail(Integer faqId){
        return "FaqDetails.jsf?faces-redirect=true&faqId=" + faqId;
    }

    public Collection<FaqMst> getFaqs() {
        Response res = api.getAllFaqs(Response.class);
        faqs = res.readEntity(new GenericType<Collection<FaqMst>>(){});
        return faqs;
    }

    public void setFaqs(Collection<FaqMst> faqs) {
        this.faqs = faqs;
    }
    
    
    

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}

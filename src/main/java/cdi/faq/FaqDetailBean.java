/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi.faq;

import api.FaqApi;
import api.UserApi;
import auth.KeepRecord;
import com.techsavvy.mindsmeet.entity.FaqAnswers;
import com.techsavvy.mindsmeet.entity.FaqMst;
import com.techsavvy.mindsmeet.entity.Users;
import ejb.FaqBeanLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 *
 * @author Acer
 */
@Named(value = "faqDetailBean")
@SessionScoped
public class FaqDetailBean implements Serializable {

    @Inject
    KeepRecord keepRecord;

    @EJB
    FaqBeanLocal fbl;

    private FaqApi api = new FaqApi();
    private FaqMst faq;
    private String faqId;

    private String answer;
    private String code;
    private boolean showDialog = false;

    public FaqBeanLocal getFbl() {
        return fbl;
    }

    public void setFbl(FaqBeanLocal fbl) {
        this.fbl = fbl;
    }

    public FaqApi getApi() {
        return api;
    }

    public void setApi(FaqApi api) {
        this.api = api;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

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

    }

    public FaqMst getFaq() {
        faqId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("faqId");
        if (faqId != null) {
            try {
                Response res = api.getFaqById(Response.class, faqId);
                faq = res.readEntity(FaqMst.class);
                Collection<FaqAnswers> answers = fbl.getFaqAnswers(Integer.valueOf(faqId));
                if (answers != null && !answers.isEmpty()) {
                    faq.setFaqAnswersCollection(fbl.getFaqAnswers(Integer.valueOf(faqId)));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        } else {
//            try {
//                FacesContext.getCurrentInstance().getExternalContext().redirect("ViewFaqs.jsf");
//            } catch (IOException ex) {
//                Logger.getLogger(FaqDetailBean.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
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

    public String submitComment() {
        System.out.println("SUBMIT CLICKED");
        FaqAnswers ans = new FaqAnswers();
        ans.setAnswer(answer);
        ans.setCode(code);
        ans.setIsAccepted(false);
        FaqMst f = new FaqMst();
        f.setId(this.faq.getId());
        ans.insertFaqId(faq);
        Users userId = new Users();
        userId.setEmail(keepRecord.getUsername());
        ans.setUserId(userId);

        try {
//                    Response res = api.answerFaq(ans, Response.class);

            Response res = fbl.answerFaq(ans);
            answer = "";
            code = "";
//        getFaq();
            System.out.println(res.toString());

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, res.toString(), null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "FaqDetails.jsf?faqId=" + faqId;
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

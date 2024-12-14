/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techsavvy.mindsmeet.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author M.SHAKIL PATEL
 */
@Entity
@Table(name = "faq_mst")
@NamedQueries({
    @NamedQuery(name = "FaqMst.findAll", query = "SELECT f FROM FaqMst f"),
    @NamedQuery(name = "FaqMst.findById", query = "SELECT f FROM FaqMst f WHERE f.id = :id"),
    @NamedQuery(name = "FaqMst.findByCreatedAt", query = "SELECT f FROM FaqMst f WHERE f.createdAt = :createdAt"),
    @NamedQuery(name = "FaqMst.findByUpdatedAt", query = "SELECT f FROM FaqMst f WHERE f.updatedAt = :updatedAt")})
public class FaqMst implements Serializable {

    @Lob
    @Size(max = 2147483647)
    @Column(name = "code")
    private String code;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "que")
    private String que;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;
    @OneToMany(mappedBy = "faqId")
    private Collection<FaqScreenshot> faqScreenshotCollection;
    @OneToMany(mappedBy = "faqId")
    private Collection<FaqAnswers> faqAnswersCollection;

    public FaqMst() {
        this.createdAt = createdAt != null ? createdAt : new Date();
        this.updatedAt = updatedAt != null ? updatedAt : new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Collection<FaqScreenshot> getFaqScreenshotCollection() {
        return faqScreenshotCollection;
    }

    public void setFaqScreenshotCollection(Collection<FaqScreenshot> faqScreenshotCollection) {
        this.faqScreenshotCollection = faqScreenshotCollection;
    }

    public Collection<FaqAnswers> getFaqAnswersCollection() {
        return faqAnswersCollection;
    }

    public void setFaqAnswersCollection(Collection<FaqAnswers> faqAnswersCollection) {
        this.faqAnswersCollection = faqAnswersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaqMst)) {
            return false;
        }
        FaqMst other = (FaqMst) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.techsavvy.mindsmeet.entity.FaqMst[ id=" + id + " ]";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }


    
}

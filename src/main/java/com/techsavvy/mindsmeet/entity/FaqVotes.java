/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techsavvy.mindsmeet.entity;

import java.io.Serializable;
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
@Table(name = "faq_votes")
@NamedQueries({
    @NamedQuery(name = "FaqVotes.findAll", query = "SELECT f FROM FaqVotes f"),
    @NamedQuery(name = "FaqVotes.findById", query = "SELECT f FROM FaqVotes f WHERE f.id = :id"),
    @NamedQuery(name = "FaqVotes.findByCreatedAt", query = "SELECT f FROM FaqVotes f WHERE f.createdAt = :createdAt"),
    @NamedQuery(name = "FaqVotes.findByUpdatedAt", query = "SELECT f FROM FaqVotes f WHERE f.updatedAt = :updatedAt")})
public class FaqVotes implements Serializable {

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "faq_ans_id", referencedColumnName = "id")
    @ManyToOne
    private FaqAnswers ansId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;
    @Column(name = "is_up_vote")
    private Boolean isUpVote;
    

    public FaqVotes() {
        this.createdAt = createdAt == null ? new Date() : createdAt;
        this.updatedAt = updatedAt == null ? new Date() : updatedAt;
    }

    public Boolean getIsUpVote() {
        return isUpVote;
    }

    public void setIsUpVote(Boolean isUpVote) {
        this.isUpVote = isUpVote;
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

    @JsonbTransient
    public FaqAnswers getAns() {
        return ansId;
    }

    public void setAns(FaqAnswers faqId) {
        this.ansId = faqId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
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
        if (!(object instanceof FaqVotes)) {
            return false;
        }
        FaqVotes other = (FaqVotes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.techsavvy.mindsmeet.entity.FaqVotes[ id=" + id + " ]";
    }

     
}

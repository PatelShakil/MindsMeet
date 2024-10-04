/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techsavvy.mindsmeet.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "faq_screenshot")
@NamedQueries({
    @NamedQuery(name = "FaqScreenshot.findAll", query = "SELECT f FROM FaqScreenshot f"),
    @NamedQuery(name = "FaqScreenshot.findById", query = "SELECT f FROM FaqScreenshot f WHERE f.id = :id"),
    @NamedQuery(name = "FaqScreenshot.findByFile", query = "SELECT f FROM FaqScreenshot f WHERE f.file = :file"),
    @NamedQuery(name = "FaqScreenshot.findByCreatedAt", query = "SELECT f FROM FaqScreenshot f WHERE f.createdAt = :createdAt"),
    @NamedQuery(name = "FaqScreenshot.findByUpdatedAt", query = "SELECT f FROM FaqScreenshot f WHERE f.updatedAt = :updatedAt")})
public class FaqScreenshot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "file")
    private String file;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "faq_id", referencedColumnName = "id")
    @ManyToOne
    private FaqMst faqId;

    public FaqScreenshot() {
    }

    public FaqScreenshot(Integer id) {
        this.id = id;
    }

    public FaqScreenshot(Integer id, Date createdAt, Date updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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

    public FaqMst getFaqId() {
        return faqId;
    }

    public void setFaqId(FaqMst faqId) {
        this.faqId = faqId;
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
        if (!(object instanceof FaqScreenshot)) {
            return false;
        }
        FaqScreenshot other = (FaqScreenshot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.techsavvy.mindsmeet.entity.FaqScreenshot[ id=" + id + " ]";
    }
    
}

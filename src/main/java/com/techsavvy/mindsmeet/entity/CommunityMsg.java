/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techsavvy.mindsmeet.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
@Table(name = "community_msg")
@NamedQueries({
    @NamedQuery(name = "CommunityMsg.findAll", query = "SELECT c FROM CommunityMsg c"),
    @NamedQuery(name = "CommunityMsg.findById", query = "SELECT c FROM CommunityMsg c WHERE c.id = :id"),
    @NamedQuery(name = "CommunityMsg.findByCreatedAt", query = "SELECT c FROM CommunityMsg c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "CommunityMsg.findByUpdatedAt", query = "SELECT c FROM CommunityMsg c WHERE c.updatedAt = :updatedAt")})
public class CommunityMsg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "message")
    private String message;
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
    @OneToMany(mappedBy = "communityMsgId")
    private Collection<CommunityReply> communityReplyCollection;
    @JoinColumn(name = "community_id", referencedColumnName = "id")
    @ManyToOne
    private CommunityMst communityId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;

    public CommunityMsg() {
    }

    public CommunityMsg(Integer id) {
        this.id = id;
    }

    public CommunityMsg(Integer id, Date createdAt, Date updatedAt) {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Collection<CommunityReply> getCommunityReplyCollection() {
        return communityReplyCollection;
    }

    public void setCommunityReplyCollection(Collection<CommunityReply> communityReplyCollection) {
        this.communityReplyCollection = communityReplyCollection;
    }

    public CommunityMst getCommunityId() {
        return communityId;
    }

    public void setCommunityId(CommunityMst communityId) {
        this.communityId = communityId;
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
        if (!(object instanceof CommunityMsg)) {
            return false;
        }
        CommunityMsg other = (CommunityMsg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.techsavvy.mindsmeet.entity.CommunityMsg[ id=" + id + " ]";
    }
    
}

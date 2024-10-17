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

/**
 *
 * @author M.SHAKIL PATEL
 */
@Entity
@Table(name = "community_members")
@NamedQueries({
    @NamedQuery(name = "CommunityMembers.findAll", query = "SELECT c FROM CommunityMembers c"),
    @NamedQuery(name = "CommunityMembers.findById", query = "SELECT c FROM CommunityMembers c WHERE c.id = :id"),
    @NamedQuery(name = "CommunityMembers.findByCreatedAt", query = "SELECT c FROM CommunityMembers c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "CommunityMembers.findByUpdatedAt", query = "SELECT c FROM CommunityMembers c WHERE c.updatedAt = :updatedAt")})
public class CommunityMembers implements Serializable {

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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;
    @JoinColumn(name = "community_id", referencedColumnName = "id")
    @ManyToOne
    private CommunityMst communityId;

    public CommunityMembers() {
    }

    public CommunityMembers(Integer id) {
        this.id = id;
    }

    public CommunityMembers(Integer id, Date createdAt, Date updatedAt) {
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

    public CommunityMst getCommunityId() {
        return communityId;
    }

    public void setCommunityId(CommunityMst communityId) {
        this.communityId = communityId;
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
        if (!(object instanceof CommunityMembers)) {
            return false;
        }
        CommunityMembers other = (CommunityMembers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.techsavvy.mindsmeet.entity.CommunityMembers[ id=" + id + " ]";
    }

}

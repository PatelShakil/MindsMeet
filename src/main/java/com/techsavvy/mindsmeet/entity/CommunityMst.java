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
@Table(name = "community_mst")
@NamedQueries({
    @NamedQuery(name = "CommunityMst.findAll", query = "SELECT c FROM CommunityMst c order by c.createdAt DESC"),
    @NamedQuery(name = "CommunityMst.findById", query = "SELECT c FROM CommunityMst c WHERE c.id = :id"),
    @NamedQuery(name = "CommunityMst.findByName", query = "SELECT c FROM CommunityMst c WHERE c.name = :name"),
    @NamedQuery(name = "CommunityMst.findByIsActive", query = "SELECT c FROM CommunityMst c WHERE c.isActive = :isActive"),
    @NamedQuery(name = "CommunityMst.findByCreatedAt", query = "SELECT c FROM CommunityMst c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "CommunityMst.findByUpdatedAt", query = "SELECT c FROM CommunityMst c WHERE c.updatedAt = :updatedAt")})
public class CommunityMst implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "is_active")
    private Boolean isActive;
    @Lob
    @Size(max = 65535)
    @Column(name = "profile")
    private String profile;
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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;
    @OneToMany(mappedBy = "communityId")
    private Collection<CommunityMembers> communityMembersCollection;
    @OneToMany(mappedBy = "communityId")
    private Collection<CommunityMsg> communityMsgCollection;

    public CommunityMst() {
        this.createdAt = createdAt != null ? createdAt : new Date();
        this.updatedAt = updatedAt != null ? updatedAt : new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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

    @JsonbTransient
    public Collection<CommunityMembers> getCommunityMembersCollection() {
        return communityMembersCollection;
    }

    public void setCommunityMembersCollection(Collection<CommunityMembers> communityMembersCollection) {
        this.communityMembersCollection = communityMembersCollection;
    }

    @JsonbTransient
    public Collection<CommunityMsg> getCommunityMsgCollection() {
        return communityMsgCollection;
    }

    public void setCommunityMsgCollection(Collection<CommunityMsg> communityMsgCollection) {
        this.communityMsgCollection = communityMsgCollection;
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
        if (!(object instanceof CommunityMst)) {
            return false;
        }
        CommunityMst other = (CommunityMst) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.techsavvy.mindsmeet.entity.CommunityMst[ id=" + id + " ]";
    }
    
}

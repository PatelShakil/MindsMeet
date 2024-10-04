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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author M.SHAKIL PATEL
 */
@Entity
@Table(name = "user_settings")
@NamedQueries({
    @NamedQuery(name = "UserSettings.findAll", query = "SELECT u FROM UserSettings u"),
    @NamedQuery(name = "UserSettings.findById", query = "SELECT u FROM UserSettings u WHERE u.id = :id"),
    @NamedQuery(name = "UserSettings.findByIsNotesEnabled", query = "SELECT u FROM UserSettings u WHERE u.isNotesEnabled = :isNotesEnabled"),
    @NamedQuery(name = "UserSettings.findByIsCommunityEnabled", query = "SELECT u FROM UserSettings u WHERE u.isCommunityEnabled = :isCommunityEnabled"),
    @NamedQuery(name = "UserSettings.findByIsFeedEnabled", query = "SELECT u FROM UserSettings u WHERE u.isFeedEnabled = :isFeedEnabled"),
    @NamedQuery(name = "UserSettings.findByIsChatEnabled", query = "SELECT u FROM UserSettings u WHERE u.isChatEnabled = :isChatEnabled"),
    @NamedQuery(name = "UserSettings.findByIsPrivate", query = "SELECT u FROM UserSettings u WHERE u.isPrivate = :isPrivate"),
    @NamedQuery(name = "UserSettings.findByCreatedAt", query = "SELECT u FROM UserSettings u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "UserSettings.findByUpdatedAt", query = "SELECT u FROM UserSettings u WHERE u.updatedAt = :updatedAt")})
public class UserSettings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "is_notes_enabled")
    private Boolean isNotesEnabled;
    @Column(name = "is_community_enabled")
    private Boolean isCommunityEnabled;
    @Column(name = "is_feed_enabled")
    private Boolean isFeedEnabled;
    @Column(name = "is_chat_enabled")
    private Boolean isChatEnabled;
    @Column(name = "is_private")
    private Boolean isPrivate;
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
    @OneToOne
    private Users user;

    public UserSettings() {
    }

    public UserSettings(Integer id) {
        this.id = id;
    }

    public UserSettings(Integer id, Date createdAt, Date updatedAt) {
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

    public Boolean getIsNotesEnabled() {
        return isNotesEnabled;
    }

    public void setIsNotesEnabled(Boolean isNotesEnabled) {
        this.isNotesEnabled = isNotesEnabled;
    }

    public Boolean getIsCommunityEnabled() {
        return isCommunityEnabled;
    }

    public void setIsCommunityEnabled(Boolean isCommunityEnabled) {
        this.isCommunityEnabled = isCommunityEnabled;
    }

    public Boolean getIsFeedEnabled() {
        return isFeedEnabled;
    }

    public void setIsFeedEnabled(Boolean isFeedEnabled) {
        this.isFeedEnabled = isFeedEnabled;
    }

    public Boolean getIsChatEnabled() {
        return isChatEnabled;
    }

    public void setIsChatEnabled(Boolean isChatEnabled) {
        this.isChatEnabled = isChatEnabled;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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
        if (!(object instanceof UserSettings)) {
            return false;
        }
        UserSettings other = (UserSettings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.techsavvy.mindsmeet.entity.UserSettings[ id=" + id + " ]";
    }
    
}

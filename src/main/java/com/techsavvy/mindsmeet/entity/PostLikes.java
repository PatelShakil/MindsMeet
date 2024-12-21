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
@Table(name = "post_likes")
@NamedQueries({
    @NamedQuery(name = "PostLikes.findAll", query = "SELECT p FROM PostLikes p"),
    @NamedQuery(name = "PostLikes.findById", query = "SELECT p FROM PostLikes p WHERE p.id = :id"),
    @NamedQuery(name = "PostLikes.findByCreatedAt", query = "SELECT p FROM PostLikes p WHERE p.createdAt = :createdAt"),
    @NamedQuery(name = "PostLikes.findByUpdatedAt", query = "SELECT p FROM PostLikes p WHERE p.updatedAt = :updatedAt")})
public class PostLikes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @ManyToOne
    private PostFeedMst postId;

    public PostLikes() {
        this.createdAt = createdAt == null ? new Date() : createdAt;
        this.updatedAt = updatedAt == null ? new Date() : updatedAt;
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

    
    @JsonbTransient
    public PostFeedMst getPostId() {
        return postId;
    }

    public void setPostId(PostFeedMst postId) {
        this.postId = postId;
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
        if (!(object instanceof PostLikes)) {
            return false;
        }
        PostLikes other = (PostLikes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.techsavvy.mindsmeet.entity.PostLikes[ id=" + id + " ]";
    }
    
}

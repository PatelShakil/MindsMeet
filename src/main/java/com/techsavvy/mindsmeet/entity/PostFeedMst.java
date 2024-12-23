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
import javax.persistence.FetchType;
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
@Table(name = "post_feed_mst")
@NamedQueries({
    @NamedQuery(name = "PostFeedMst.findAll", query = "SELECT p FROM PostFeedMst p"),
    @NamedQuery(name = "PostFeedMst.findById", query = "SELECT p FROM PostFeedMst p WHERE p.id = :id"),
    @NamedQuery(name = "PostFeedMst.findByPhoto", query = "SELECT p FROM PostFeedMst p WHERE p.photo = :photo"),
    @NamedQuery(name = "PostFeedMst.findByLocation", query = "SELECT p FROM PostFeedMst p WHERE p.location = :location"),
    @NamedQuery(name = "PostFeedMst.findByCreatedAt", query = "SELECT p FROM PostFeedMst p WHERE p.createdAt = :createdAt"),
    @NamedQuery(name = "PostFeedMst.findByUpdatedAt", query = "SELECT p FROM PostFeedMst p WHERE p.updatedAt = :updatedAt")})
public class PostFeedMst implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "photo")
    private String photo;
    @Size(max = 65535)
    @Column(name = "caption")
    private String caption;
    @Size(max = 255)
    @Column(name = "location")
    private String location;
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
    
    @OneToMany(mappedBy = "postId",fetch = FetchType.EAGER)
    private Collection<PostComments> postCommentsCollection;
    
    @OneToMany(mappedBy = "postId",fetch = FetchType.EAGER)
    private Collection<PostLikes> postLikesCollection;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;


    public PostFeedMst() {
        this.createdAt = createdAt == null ? new Date() : createdAt;
        this.updatedAt = updatedAt == null ? new Date() : updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
    public Collection<PostComments> getPostCommentsCollection() {
        return postCommentsCollection;
    }

    public void setPostCommentsCollection(Collection<PostComments> postCommentsCollection) {
        this.postCommentsCollection = postCommentsCollection;
    }

    
    public Collection<PostLikes> getPostLikesCollection() {
        return postLikesCollection;
    }

    public void setPostLikesCollection(Collection<PostLikes> postLikesCollection) {
        this.postLikesCollection = postLikesCollection;
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
        if (!(object instanceof PostFeedMst)) {
            return false;
        }
        PostFeedMst other = (PostFeedMst) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.techsavvy.mindsmeet.entity.PostFeedMst[ id=" + id + " ]";
    }
    
}

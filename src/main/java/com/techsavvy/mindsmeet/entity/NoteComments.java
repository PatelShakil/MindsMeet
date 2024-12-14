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
@Table(name = "note_comments")
@NamedQueries({
    @NamedQuery(name = "NoteComments.findAll", query = "SELECT n FROM NoteComments n"),
    @NamedQuery(name = "NoteComments.findById", query = "SELECT n FROM NoteComments n WHERE n.id = :id"),
    @NamedQuery(name = "NoteComments.findByCreatedAt", query = "SELECT n FROM NoteComments n WHERE n.createdAt = :createdAt")})
public class NoteComments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "comment_text")
    private String commentText;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(mappedBy = "commentId")
    private Collection<NoteReplies> noteRepliesCollection;
    @JoinColumn(name = "note_id", referencedColumnName = "id")
    @ManyToOne
    private Notes noteId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;

    

    public NoteComments() {
        this.createdAt = createdAt == null ? new Date() : createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Collection<NoteReplies> getNoteRepliesCollection() {
        return noteRepliesCollection;
    }

    public void setNoteRepliesCollection(Collection<NoteReplies> noteRepliesCollection) {
        this.noteRepliesCollection = noteRepliesCollection;
    }

    @JsonbTransient
    public Notes getNoteId() {
        return noteId;
    }

    public void setNoteId(Notes noteId) {
        this.noteId = noteId;
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
        if (!(object instanceof NoteComments)) {
            return false;
        }
        NoteComments other = (NoteComments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.techsavvy.mindsmeet.entity.NoteComments[ id=" + id + " ]";
    }
    
}

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
@Table(name = "notes")
@NamedQueries({
    @NamedQuery(name = "Notes.findAll", query = "SELECT n FROM Notes n order by n.createdAt DESC"),
    @NamedQuery(name = "Notes.findById", query = "SELECT n FROM Notes n WHERE n.id = :id"),
    @NamedQuery(name = "Notes.findByTitle", query = "SELECT n FROM Notes n WHERE n.title = :title"),
    @NamedQuery(name = "Notes.findByFile", query = "SELECT n FROM Notes n WHERE n.file = :file"),
    @NamedQuery(name = "Notes.findByIsTranslatable", query = "SELECT n FROM Notes n WHERE n.isTranslatable = :isTranslatable"),
    @NamedQuery(name = "Notes.findByIsCommentable", query = "SELECT n FROM Notes n WHERE n.isCommentable = :isCommentable"),
    @NamedQuery(name = "Notes.findByIsPublic", query = "SELECT n FROM Notes n WHERE n.isPublic = :isPublic"),
    @NamedQuery(name = "Notes.findByIsListenable", query = "SELECT n FROM Notes n WHERE n.isListenable = :isListenable"),
    @NamedQuery(name = "Notes.findByIsVerified", query = "SELECT n FROM Notes n WHERE n.isVerified = :isVerified"),
    @NamedQuery(name = "Notes.findByCreatedAt", query = "SELECT n FROM Notes n WHERE n.createdAt = :createdAt"),
    @NamedQuery(name = "Notes.findByUpdatedAt", query = "SELECT n FROM Notes n WHERE n.updatedAt = :updatedAt")})
public class Notes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "file")
    private String file;
    @Column(name = "is_translatable")
    private Boolean isTranslatable;
    @Column(name = "is_commentable")
    private Boolean isCommentable;
    @Column(name = "is_public")
    private Boolean isPublic;
    @Column(name = "is_listenable")
    private Boolean isListenable;
    @Column(name = "is_verified")
    private Boolean isVerified;
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
    @OneToMany(mappedBy = "notesId")
    private Collection<NotesView> notesViewCollection;
    @OneToMany(mappedBy = "noteId")
    private Collection<NoteComments> noteCommentsCollection;
    @OneToMany(mappedBy = "notesId")
    private Collection<NotesText> notesTextCollection;


    public Notes() {
        this.createdAt = createdAt != null ? createdAt : new Date();
        this.updatedAt = updatedAt != null ? updatedAt : new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Boolean getIsTranslatable() {
        return isTranslatable;
    }

    public void setIsTranslatable(Boolean isTranslatable) {
        this.isTranslatable = isTranslatable;
    }

    public Boolean getIsCommentable() {
        return isCommentable;
    }

    public void setIsCommentable(Boolean isCommentable) {
        this.isCommentable = isCommentable;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Boolean getIsListenable() {
        return isListenable;
    }

    public void setIsListenable(Boolean isListenable) {
        this.isListenable = isListenable;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
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

    public Collection<NotesView> getNotesViewCollection() {
        return notesViewCollection;
    }

    public void setNotesViewCollection(Collection<NotesView> notesViewCollection) {
        this.notesViewCollection = notesViewCollection;
    }

    public Collection<NoteComments> getNoteCommentsCollection() {
        return noteCommentsCollection;
    }

    public void setNoteCommentsCollection(Collection<NoteComments> noteCommentsCollection) {
        this.noteCommentsCollection = noteCommentsCollection;
    }

    public Collection<NotesText> getNotesTextCollection() {
        return notesTextCollection;
    }

    public void setNotesTextCollection(Collection<NotesText> notesTextCollection) {
        this.notesTextCollection = notesTextCollection;
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
        if (!(object instanceof Notes)) {
            return false;
        }
        Notes other = (Notes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.techsavvy.mindsmeet.entity.Notes[ id=" + id + " ]";
    }
    
}

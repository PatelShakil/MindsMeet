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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByToken", query = "SELECT u FROM Users u WHERE u.token = :token"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
    @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "Users.findByUpdatedAt", query = "SELECT u FROM Users u WHERE u.updatedAt = :updatedAt"),
    @NamedQuery(name = "Users.findByIsActive", query = "SELECT u FROM Users u WHERE u.isActive = :isActive"),
    @NamedQuery(name = "Users.findByIsBlocked", query = "SELECT u FROM Users u WHERE u.isBlocked = :isBlocked"),
    @NamedQuery(name = "Users.findByBlockedUntil", query = "SELECT u FROM Users u WHERE u.blockedUntil = :blockedUntil"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "token")
    private String token;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 15)
    @Column(name = "phone")
    private String phone;
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
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "is_blocked")
    private Boolean isBlocked;
    @Column(name = "blocked_until")
    @Temporal(TemporalType.TIMESTAMP)
    private Date blockedUntil;
    @Lob
    @Size(max = 65535)
    @Column(name = "profile")
    private String profile;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "username")
    private String username;
    @OneToMany(mappedBy = "userId")
    private Collection<GroupUsers> groupUsersCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<FaqMst> faqMstCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Notes> notesCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<NotesView> notesViewCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<FaqVotes> faqVotesCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<NoteReplies> noteRepliesCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<PostComments> postCommentsCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<NoteComments> noteCommentsCollection;
    @OneToMany(mappedBy = "senderId")
    private Collection<MsgMst> msgMstCollection;
    @OneToMany(mappedBy = "receiverId")
    private Collection<MsgMst> msgMstCollection1;
    @OneToMany(mappedBy = "userId")
    private Collection<CommunityMst> communityMstCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<CommunityMembers> communityMembersCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<CommunityReply> communityReplyCollection;
    @OneToOne(mappedBy = "user")
    private UserSettings userSettings;
    @OneToMany(mappedBy = "userId")
    private Collection<FaqAnswers> faqAnswersCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<PostLikes> postLikesCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<PostFeedMst> postFeedMstCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<CommunityMsg> communityMsgCollection;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String token, String email, String password, Date createdAt, Date updatedAt) {
        this.id = id;
        this.token = token;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Date getBlockedUntil() {
        return blockedUntil;
    }

    public void setBlockedUntil(Date blockedUntil) {
        this.blockedUntil = blockedUntil;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<GroupUsers> getGroupUsersCollection() {
        return groupUsersCollection;
    }

    public void setGroupUsersCollection(Collection<GroupUsers> groupUsersCollection) {
        this.groupUsersCollection = groupUsersCollection;
    }

    public Collection<FaqMst> getFaqMstCollection() {
        return faqMstCollection;
    }

    public void setFaqMstCollection(Collection<FaqMst> faqMstCollection) {
        this.faqMstCollection = faqMstCollection;
    }

    public Collection<Notes> getNotesCollection() {
        return notesCollection;
    }

    public void setNotesCollection(Collection<Notes> notesCollection) {
        this.notesCollection = notesCollection;
    }

    public Collection<NotesView> getNotesViewCollection() {
        return notesViewCollection;
    }

    public void setNotesViewCollection(Collection<NotesView> notesViewCollection) {
        this.notesViewCollection = notesViewCollection;
    }

    public Collection<FaqVotes> getFaqVotesCollection() {
        return faqVotesCollection;
    }

    public void setFaqVotesCollection(Collection<FaqVotes> faqVotesCollection) {
        this.faqVotesCollection = faqVotesCollection;
    }

    public Collection<NoteReplies> getNoteRepliesCollection() {
        return noteRepliesCollection;
    }

    public void setNoteRepliesCollection(Collection<NoteReplies> noteRepliesCollection) {
        this.noteRepliesCollection = noteRepliesCollection;
    }

    public Collection<PostComments> getPostCommentsCollection() {
        return postCommentsCollection;
    }

    public void setPostCommentsCollection(Collection<PostComments> postCommentsCollection) {
        this.postCommentsCollection = postCommentsCollection;
    }

    public Collection<NoteComments> getNoteCommentsCollection() {
        return noteCommentsCollection;
    }

    public void setNoteCommentsCollection(Collection<NoteComments> noteCommentsCollection) {
        this.noteCommentsCollection = noteCommentsCollection;
    }

    public Collection<MsgMst> getMsgMstCollection() {
        return msgMstCollection;
    }

    public void setMsgMstCollection(Collection<MsgMst> msgMstCollection) {
        this.msgMstCollection = msgMstCollection;
    }

    public Collection<MsgMst> getMsgMstCollection1() {
        return msgMstCollection1;
    }

    public void setMsgMstCollection1(Collection<MsgMst> msgMstCollection1) {
        this.msgMstCollection1 = msgMstCollection1;
    }

    public Collection<CommunityMst> getCommunityMstCollection() {
        return communityMstCollection;
    }

    public void setCommunityMstCollection(Collection<CommunityMst> communityMstCollection) {
        this.communityMstCollection = communityMstCollection;
    }

    public Collection<CommunityMembers> getCommunityMembersCollection() {
        return communityMembersCollection;
    }

    public void setCommunityMembersCollection(Collection<CommunityMembers> communityMembersCollection) {
        this.communityMembersCollection = communityMembersCollection;
    }

    public Collection<CommunityReply> getCommunityReplyCollection() {
        return communityReplyCollection;
    }

    public void setCommunityReplyCollection(Collection<CommunityReply> communityReplyCollection) {
        this.communityReplyCollection = communityReplyCollection;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

    public Collection<FaqAnswers> getFaqAnswersCollection() {
        return faqAnswersCollection;
    }

    public void setFaqAnswersCollection(Collection<FaqAnswers> faqAnswersCollection) {
        this.faqAnswersCollection = faqAnswersCollection;
    }

    public Collection<PostLikes> getPostLikesCollection() {
        return postLikesCollection;
    }

    public void setPostLikesCollection(Collection<PostLikes> postLikesCollection) {
        this.postLikesCollection = postLikesCollection;
    }

    public Collection<PostFeedMst> getPostFeedMstCollection() {
        return postFeedMstCollection;
    }

    public void setPostFeedMstCollection(Collection<PostFeedMst> postFeedMstCollection) {
        this.postFeedMstCollection = postFeedMstCollection;
    }

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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id=" + id + "\nname=" + name +"\nemail=" + email;
    }
    
}

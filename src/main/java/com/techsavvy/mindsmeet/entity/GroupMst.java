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
@Table(name = "group_mst")
@NamedQueries({
    @NamedQuery(name = "GroupMst.findAll", query = "SELECT g FROM GroupMst g"),
    @NamedQuery(name = "GroupMst.findById", query = "SELECT g FROM GroupMst g WHERE g.id = :id"),
    @NamedQuery(name = "GroupMst.findByType", query = "SELECT g FROM GroupMst g WHERE g.type = :type"),
    @NamedQuery(name = "GroupMst.findByCreatedAt", query = "SELECT g FROM GroupMst g WHERE g.createdAt = :createdAt"),
    @NamedQuery(name = "GroupMst.findByUpdatedAt", query = "SELECT g FROM GroupMst g WHERE g.updatedAt = :updatedAt")})
public class GroupMst implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "type")
    private String type;
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
    @OneToMany(mappedBy = "groupId")
    private Collection<GroupUsers> groupUsersCollection;

    public GroupMst() {
    }

    public GroupMst(Integer id) {
        this.id = id;
    }

    public GroupMst(Integer id, String type, Date createdAt, Date updatedAt) {
        this.id = id;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Collection<GroupUsers> getGroupUsersCollection() {
        return groupUsersCollection;
    }

    public void setGroupUsersCollection(Collection<GroupUsers> groupUsersCollection) {
        this.groupUsersCollection = groupUsersCollection;
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
        if (!(object instanceof GroupMst)) {
            return false;
        }
        GroupMst other = (GroupMst) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.techsavvy.mindsmeet.entity.GroupMst[ id=" + id + " ]";
    }
    
}

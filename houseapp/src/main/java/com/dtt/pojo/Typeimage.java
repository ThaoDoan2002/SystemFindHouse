/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author doant
 */
@Entity
@Table(name = "typeimage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typeimage.findAll", query = "SELECT t FROM Typeimage t"),
    @NamedQuery(name = "Typeimage.findById", query = "SELECT t FROM Typeimage t WHERE t.id = :id"),
    @NamedQuery(name = "Typeimage.findByType", query = "SELECT t FROM Typeimage t WHERE t.type = :type")})
public class Typeimage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "type")
    private String type;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeId")
    private Set<Imageprofile> imageprofileSet;

    public Typeimage() {
    }

    public Typeimage(Integer id) {
        this.id = id;
    }

    public Typeimage(Integer id, String type) {
        this.id = id;
        this.type = type;
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

    @XmlTransient
    public Set<Imageprofile> getImageprofileSet() {
        return imageprofileSet;
    }

    public void setImageprofileSet(Set<Imageprofile> imageprofileSet) {
        this.imageprofileSet = imageprofileSet;
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
        if (!(object instanceof Typeimage)) {
            return false;
        }
        Typeimage other = (Typeimage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtt.pojo.Typeimage[ id=" + id + " ]";
    }
    
}

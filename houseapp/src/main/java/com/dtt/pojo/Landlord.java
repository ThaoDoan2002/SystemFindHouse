/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.pojo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author doant
 */
@Entity
@Table(name = "landlord")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Landlord.findAll", query = "SELECT l FROM Landlord l"),
    @NamedQuery(name = "Landlord.findById", query = "SELECT l FROM Landlord l WHERE l.id = :id"),
    @NamedQuery(name = "Landlord.findByPhoneNumber", query = "SELECT l FROM Landlord l WHERE l.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Landlord.findByAddress", query = "SELECT l FROM Landlord l WHERE l.address = :address")})
public class Landlord implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Pattern(regexp = "^[0-9]{10,11}$")
    @Size(max = 15)
    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;
    @Size(max = 255)
    @NotNull
    @Column(name = "address")
    private String address;
  
 
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private User userId;


    public Landlord() {
    }

    public Landlord(Integer id) {
        this.id = id;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }




    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
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
        if (!(object instanceof Landlord)) {
            return false;
        }
        Landlord other = (Landlord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtt.pojo.Landlord[ id=" + id + " ]";
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
}

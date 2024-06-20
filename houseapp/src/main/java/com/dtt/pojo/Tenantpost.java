/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;

/**
 *
 * @author doant
 */
@Entity
@Table(name = "tenantpost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tenantpost.findAll", query = "SELECT t FROM Tenantpost t"),
    @NamedQuery(name = "Tenantpost.findById", query = "SELECT t FROM Tenantpost t WHERE t.id = :id"),
    @NamedQuery(name = "Tenantpost.findByAddress", query = "SELECT t FROM Tenantpost t WHERE t.address = :address"),
    @NamedQuery(name = "Tenantpost.findByLatitude", query = "SELECT t FROM Tenantpost t WHERE t.latitude = :latitude"),
    @NamedQuery(name = "Tenantpost.findByLongitude", query = "SELECT t FROM Tenantpost t WHERE t.longitude = :longitude")})

public class Tenantpost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @NotNull
    @Column(name = "max_occupants")
    private BigDecimal maxOccupants;
    @NotNull
    @Column(name = "min_price")
    private BigDecimal minPrice;
     @NotNull
    @Column(name = "max_price")
    private BigDecimal maxPrice;
    @NotNull
    @Column(name = "area")
    private BigDecimal area;
    @NotNull
    @Column(name = "scope")
    private BigDecimal scope;

    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @OneToOne(optional = false)

    private Post postId;

    public Tenantpost() {
    }

    public Tenantpost(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
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
        if (!(object instanceof Tenantpost)) {
            return false;
        }
        Tenantpost other = (Tenantpost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtt.pojo.Tenantpost[ id=" + id + " ]";
    }

    /**
     * @return the latitude
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the maxOccupants
     */
    public BigDecimal getMaxOccupants() {
        return maxOccupants;
    }

    /**
     * @param maxOccupants the maxOccupants to set
     */
    public void setMaxOccupants(BigDecimal maxOccupants) {
        this.maxOccupants = maxOccupants;
    }

    /**
     * @return the minPrice
     */
    public BigDecimal getMinPrice() {
        return minPrice;
    }

    /**
     * @param minPrice the minPrice to set
     */
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * @return the maxPrice
     */
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    /**
     * @param maxPrice the maxPrice to set
     */
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * @return the area
     */
    public BigDecimal getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    /**
     * @return the scope
     */
    public BigDecimal getScope() {
        return scope;
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(BigDecimal scope) {
        this.scope = scope;
    }

}

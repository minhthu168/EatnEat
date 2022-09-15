/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "Favorite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Favorite.findAll", query = "SELECT f FROM Favorite f"),
    @NamedQuery(name = "Favorite.findByFavoriteid", query = "SELECT f FROM Favorite f WHERE f.favoriteid = :favoriteid"),
    @NamedQuery(name = "Favorite.findByFoodid", query = "SELECT f FROM Favorite f WHERE f.foodid = :foodid"),
    @NamedQuery(name = "Favorite.findByEmpid", query = "SELECT f FROM Favorite f WHERE f.empid = :empid"),
    @NamedQuery(name = "Favorite.findByName", query = "SELECT f FROM Favorite f WHERE f.name = :name"),
    @NamedQuery(name = "Favorite.findByPrice", query = "SELECT f FROM Favorite f WHERE f.price = :price"),
    @NamedQuery(name = "Favorite.findByQuantity", query = "SELECT f FROM Favorite f WHERE f.quantity = :quantity"),
    @NamedQuery(name = "Favorite.findByImage", query = "SELECT f FROM Favorite f WHERE f.image = :image")})
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "favoriteid")
    private Integer favoriteid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "foodid")
    private int foodid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "empid")
    private String empid;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Integer price;
    @Column(name = "quantity")
    private Integer quantity;
    @Size(max = 200)
    @Column(name = "image")
    private String image;

    public Favorite() {
    }

    public Favorite(Integer favoriteid) {
        this.favoriteid = favoriteid;
    }

    public Favorite(Integer favoriteid, int foodid, String empid) {
        this.favoriteid = favoriteid;
        this.foodid = foodid;
        this.empid = empid;
    }

    public Integer getFavoriteid() {
        return favoriteid;
    }

    public void setFavoriteid(Integer favoriteid) {
        this.favoriteid = favoriteid;
    }

    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (favoriteid != null ? favoriteid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Favorite)) {
            return false;
        }
        Favorite other = (Favorite) object;
        if ((this.favoriteid == null && other.favoriteid != null) || (this.favoriteid != null && !this.favoriteid.equals(other.favoriteid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.EatnEat.entities.Favorite[ favoriteid=" + favoriteid + " ]";
    }
    
}

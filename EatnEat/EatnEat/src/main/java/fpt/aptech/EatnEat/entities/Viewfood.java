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
@Table(name = "Viewfood")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Viewfood.findAll", query = "SELECT v FROM Viewfood v"),
    @NamedQuery(name = "Viewfood.findByFoodid", query = "SELECT v FROM Viewfood v WHERE v.foodid = :foodid"),
    @NamedQuery(name = "Viewfood.findByFoodname", query = "SELECT v FROM Viewfood v WHERE v.foodname = :foodname"),
    @NamedQuery(name = "Viewfood.findByPrice", query = "SELECT v FROM Viewfood v WHERE v.price = :price"),
    @NamedQuery(name = "Viewfood.findByMenutypeid", query = "SELECT v FROM Viewfood v WHERE v.menutypeid = :menutypeid"),
    @NamedQuery(name = "Viewfood.findByType", query = "SELECT v FROM Viewfood v WHERE v.type = :type"),
    @NamedQuery(name = "Viewfood.findByImage", query = "SELECT v FROM Viewfood v WHERE v.image = :image"),
    @NamedQuery(name = "Viewfood.findByCategory", query = "SELECT v FROM Viewfood v WHERE v.category = :category")})
public class Viewfood implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @NotNull
    @Column(name = "Foodid")
    private int foodid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Foodname")
    private String foodname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private int price;
    @Column(name = "menutypeid")
    private Integer menutypeid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "type")
    private String type;
    @Size(max = 200)
    @Column(name = "image")
    private String image;
    @Size(max = 20)
    @Column(name = "category")
    private String category;

    public Viewfood() {
    }

    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getMenutypeid() {
        return menutypeid;
    }

    public void setMenutypeid(Integer menutypeid) {
        this.menutypeid = menutypeid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
}

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
@Table(name = "Vieworderdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vieworderdetail.findAll", query = "SELECT v FROM Vieworderdetail v"),
    @NamedQuery(name = "Vieworderdetail.findById", query = "SELECT v FROM Vieworderdetail v WHERE v.id = :id"),
    @NamedQuery(name = "Vieworderdetail.findByOrderid", query = "SELECT v FROM Vieworderdetail v WHERE v.orderid = :orderid"),
    @NamedQuery(name = "Vieworderdetail.findByFoodid", query = "SELECT v FROM Vieworderdetail v WHERE v.foodid = :foodid"),
    @NamedQuery(name = "Vieworderdetail.findByFoodname", query = "SELECT v FROM Vieworderdetail v WHERE v.foodname = :foodname"),
    @NamedQuery(name = "Vieworderdetail.findByPrice", query = "SELECT v FROM Vieworderdetail v WHERE v.price = :price"),
    @NamedQuery(name = "Vieworderdetail.findByImage", query = "SELECT v FROM Vieworderdetail v WHERE v.image = :image"),
    @NamedQuery(name = "Vieworderdetail.findByQuantity", query = "SELECT v FROM Vieworderdetail v WHERE v.quantity = :quantity"),
    @NamedQuery(name = "Vieworderdetail.findByTotalamount", query = "SELECT v FROM Vieworderdetail v WHERE v.totalamount = :totalamount")})
public class Vieworderdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @NotNull
    @Column(name = "id")
    private int id;
    @Column(name = "Orderid")
    private Integer orderid;
    @Column(name = "Foodid")
    private Integer foodid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Foodname")
    private String foodname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private int price;
    @Size(max = 200)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Totalamount")
    private int totalamount;

    public Vieworderdetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getFoodid() {
        return foodid;
    }

    public void setFoodid(Integer foodid) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(int totalamount) {
        this.totalamount = totalamount;
    }
    
}

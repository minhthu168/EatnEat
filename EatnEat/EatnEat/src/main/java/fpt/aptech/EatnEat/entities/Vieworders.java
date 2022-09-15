/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "Vieworders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vieworders.findAll", query = "SELECT v FROM Vieworders v"),
    @NamedQuery(name = "Vieworders.findByOrderid", query = "SELECT v FROM Vieworders v WHERE v.orderid = :orderid"),
    @NamedQuery(name = "Vieworders.findByEmpid", query = "SELECT v FROM Vieworders v WHERE v.empid = :empid"),
    @NamedQuery(name = "Vieworders.findByQuantity", query = "SELECT v FROM Vieworders v WHERE v.quantity = :quantity"),
    @NamedQuery(name = "Vieworders.findByTotalamount", query = "SELECT v FROM Vieworders v WHERE v.totalamount = :totalamount"),
    @NamedQuery(name = "Vieworders.findByOrderdate", query = "SELECT v FROM Vieworders v WHERE v.orderdate = :orderdate")})
public class Vieworders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Orderid")
    private Integer orderid;
    @Size(max = 10)
    @Column(name = "Empid")
    private String empid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Totalamount")
    private int totalamount;
    @Column(name = "orderdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderdate;

    public Vieworders() {
    }

    public Vieworders(Integer orderid) {
        this.orderid = orderid;
    }

    public Vieworders(Integer orderid, int quantity, int totalamount) {
        this.orderid = orderid;
        this.quantity = quantity;
        this.totalamount = totalamount;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
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

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderid != null ? orderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vieworders)) {
            return false;
        }
        Vieworders other = (Vieworders) object;
        if ((this.orderid == null && other.orderid != null) || (this.orderid != null && !this.orderid.equals(other.orderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.EatnEat.entities.Vieworders[ orderid=" + orderid + " ]";
    }
    
}

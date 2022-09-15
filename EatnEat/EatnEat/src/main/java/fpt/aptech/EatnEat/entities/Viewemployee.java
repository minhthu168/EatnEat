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
 * @author KIM DUNG
 */
@Entity
@Table(name = "Viewemployee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Viewemployee.findAll", query = "SELECT v FROM Viewemployee v"),
    @NamedQuery(name = "Viewemployee.findByEmpname", query = "SELECT v FROM Viewemployee v WHERE v.empname = :empname"),
    @NamedQuery(name = "Viewemployee.findByPhone", query = "SELECT v FROM Viewemployee v WHERE v.phone = :phone"),
    @NamedQuery(name = "Viewemployee.findByEmpid", query = "SELECT v FROM Viewemployee v WHERE v.empid = :empid")})
public class Viewemployee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "empname")
    private String empname;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 15)
    @Column(name = "phone")
    private String phone;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "empid")
    private String empid;

    public Viewemployee() {
    }

    public Viewemployee(String empid) {
        this.empid = empid;
    }

    public Viewemployee(String empid, String empname) {
        this.empid = empid;
        this.empname = empname;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empid != null ? empid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Viewemployee)) {
            return false;
        }
        Viewemployee other = (Viewemployee) object;
        if ((this.empid == null && other.empid != null) || (this.empid != null && !this.empid.equals(other.empid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.EatnEat.entities.Viewemployee[ empid=" + empid + " ]";
    }
    
}

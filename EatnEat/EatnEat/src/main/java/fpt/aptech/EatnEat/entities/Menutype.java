
package fpt.aptech.EatnEat.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "Menutype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menutype.findAll", query = "SELECT m FROM Menutype m"),
    @NamedQuery(name = "Menutype.findByMenutypeid", query = "SELECT m FROM Menutype m WHERE m.menutypeid = :menutypeid"),
    @NamedQuery(name = "Menutype.findByType", query = "SELECT m FROM Menutype m WHERE m.type = :type"),
    @NamedQuery(name = "Menutype.findByImg", query = "SELECT m FROM Menutype m WHERE m.typeimg = :typeimg")})
public class Menutype implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "type")
    private String type;
    @Size(max = 100)
    @Column(name = "typeimg")
    private String typeimg;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "menutypeid")
    private Integer menutypeid;

    public Menutype() {
    }

    public Menutype(Integer menutypeid) {
        this.menutypeid = menutypeid;
    }

    public Menutype(Integer menutypeid, String type) {
        this.menutypeid = menutypeid;
        this.type = type;
    }

    public Integer getMenutypeid() {
        return menutypeid;
    }

    public void setMenutypeid(Integer menutypeid) {
        this.menutypeid = menutypeid;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menutypeid != null ? menutypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menutype)) {
            return false;
        }
        Menutype other = (Menutype) object;
        if ((this.menutypeid == null && other.menutypeid != null) || (this.menutypeid != null && !this.menutypeid.equals(other.menutypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.EatnEat.entities.Menutype[ menutypeid=" + menutypeid + " ]";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeimg() {
        return typeimg;
    }

    public void setTypeimg(String typeimg) {
        this.typeimg = typeimg;
    }

}

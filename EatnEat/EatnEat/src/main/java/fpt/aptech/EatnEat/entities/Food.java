
package fpt.aptech.EatnEat.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "Food")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f"),
    @NamedQuery(name = "Food.findByFoodid", query = "SELECT f FROM Food f WHERE f.foodid = :foodid"),
    @NamedQuery(name = "Food.findByFoodname", query = "SELECT f FROM Food f WHERE f.foodname = :foodname"),
    @NamedQuery(name = "Food.findByPrice", query = "SELECT f FROM Food f WHERE f.price = :price"),
    @NamedQuery(name = "Food.findByImage", query = "SELECT f FROM Food f WHERE f.image = :image")})
public class Food implements Serializable {

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
    @Size(max = 50)
    @Column(name = "category")
    private String category;

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic(optional = false)
    @Column(name = "Foodid")
    private Integer foodid;
    @OneToMany(mappedBy = "foodid")
    private List<Receipe> receipeList;
    @OneToMany(mappedBy = "foodid")
    private List<Orderdetail> orderdetailList;
    @JoinColumn(name = "menutypeid", referencedColumnName = "menutypeid")
    @ManyToOne
    private Menutype menutypeid;

    public Food() {
    }

   

    public Food(Integer foodid) {
        this.foodid = foodid;
    }

    public Food(Integer foodid, String foodname, int price) {
        this.foodid = foodid;
        this.foodname = foodname;
        this.price = price;
    }

    public Integer getFoodid() {
        return foodid;
    }

    public void setFoodid(Integer foodid) {
        this.foodid = foodid;
    }

    @XmlTransient
    public List<Receipe> getReceipeList() {
        return receipeList;
    }

    public void setReceipeList(List<Receipe> receipeList) {
        this.receipeList = receipeList;
    }

    @XmlTransient
    public List<Orderdetail> getOrderdetailList() {
        return orderdetailList;
    }

    public void setOrderdetailList(List<Orderdetail> orderdetailList) {
        this.orderdetailList = orderdetailList;
    }

    public Menutype getMenutypeid() {
        return menutypeid;
    }

    public void setMenutypeid(Menutype menutypeid) {
        this.menutypeid = menutypeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foodid != null ? foodid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Food)) {
            return false;
        }
        Food other = (Food) object;
        if ((this.foodid == null && other.foodid != null) || (this.foodid != null && !this.foodid.equals(other.foodid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.EatnEat.entities.Food[ foodid=" + foodid + " ]";
    }
    public String getPriceFormat() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(price);
    }
    @Transient
    public String getImagePath() {
        if (image == null || foodid == null) {
            return null;
        }

        return "../images/" + image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

}

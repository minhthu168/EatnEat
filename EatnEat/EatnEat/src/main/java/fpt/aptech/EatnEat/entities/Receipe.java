
package fpt.aptech.EatnEat.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "Receipe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receipe.findAll", query = "SELECT r FROM Receipe r"),
    @NamedQuery(name = "Receipe.findByReceipeid", query = "SELECT r FROM Receipe r WHERE r.receipeid = :receipeid"),
    @NamedQuery(name = "Receipe.findByQuantity", query = "SELECT r FROM Receipe r WHERE r.quantity = :quantity"),
    @NamedQuery(name = "Receipe.findByUnit", query = "SELECT r FROM Receipe r WHERE r.unit = :unit")})
public class Receipe implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private double quantity;
    @Size(max = 10)
    @Column(name = "unit")
    private String unit;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Receipeid")
    private Integer receipeid;
    @JoinColumn(name = "Foodid", referencedColumnName = "Foodid")
    @ManyToOne
    private Food foodid;
    @JoinColumn(name = "Ingredientid", referencedColumnName = "Ingredientid")
    @ManyToOne
    private Ingredient ingredientid;

    public Receipe() {
    }

    public Receipe(Integer receipeid) {
        this.receipeid = receipeid;
    }

    public Receipe(Integer receipeid, double quantity) {
        this.receipeid = receipeid;
        this.quantity = quantity;
    }

    public Integer getReceipeid() {
        return receipeid;
    }

    public void setReceipeid(Integer receipeid) {
        this.receipeid = receipeid;
    }
    
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getQuantityFormat() {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(getQuantity());
    }

    public Food getFoodid() {
        return foodid;
    }

    public void setFoodid(Food foodid) {
        this.foodid = foodid;
    }

    public Ingredient getIngredientid() {
        return ingredientid;
    }

    public void setIngredientid(Ingredient ingredientid) {
        this.ingredientid = ingredientid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (receipeid != null ? receipeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receipe)) {
            return false;
        }
        Receipe other = (Receipe) object;
        if ((this.receipeid == null && other.receipeid != null) || (this.receipeid != null && !this.receipeid.equals(other.receipeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.EatnEat.entities.Receipe[ receipeid=" + receipeid + " ]";
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
}

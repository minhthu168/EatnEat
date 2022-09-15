
package fpt.aptech.EatnEat.entities;


import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "Ingredient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingredient.findAll", query = "SELECT i FROM Ingredient i"),
    @NamedQuery(name = "Ingredient.findByIngredientid", query = "SELECT i FROM Ingredient i WHERE i.ingredientid = :ingredientid"),
    @NamedQuery(name = "Ingredient.findByName", query = "SELECT i FROM Ingredient i WHERE i.name = :name")})
public class Ingredient implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Ingredientid")
    private Integer ingredientid;
    @OneToMany(mappedBy = "ingredientid")
 
    private List<Receipe> receipeList;

    public Ingredient() {
    }

    public Ingredient(Integer ingredientid) {
        this.ingredientid = ingredientid;
    }

    public Ingredient(Integer ingredientid, String name) {
        this.ingredientid = ingredientid;
        this.name = name;
    }

    public Integer getIngredientid() {
        return ingredientid;
    }

    public void setIngredientid(Integer ingredientid) {
        this.ingredientid = ingredientid;
    }
    @XmlTransient
    public List<Receipe> getReceipeList() {
        return receipeList;
    }

    public void setReceipeList(List<Receipe> receipeList) {
        this.receipeList = receipeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingredientid != null ? ingredientid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingredient)) {
            return false;
        }
        Ingredient other = (Ingredient) object;
        if ((this.ingredientid == null && other.ingredientid != null) || (this.ingredientid != null && !this.ingredientid.equals(other.ingredientid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.EatnEat.entities.Ingredient[ ingredientid=" + ingredientid + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}

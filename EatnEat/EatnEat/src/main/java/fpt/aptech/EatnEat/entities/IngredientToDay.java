/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.entities;

import java.text.DecimalFormat;

/**
 *
 * @author lenovo
 */
public class IngredientToDay {
    private String Ingredientname;
    private double quantity;    
    private String unit;
    private int price;    
    private int totalamount;
    
    public IngredientToDay() {
    }

    public IngredientToDay(String Ingredientname, double quantity, String unit, int price) {
        this.Ingredientname = Ingredientname;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        
    }

    public String getIngredientname() {
        return Ingredientname;
    }

    public void setIngredientname(String Ingredientname) {
        this.Ingredientname = Ingredientname;
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
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalamount() {
        return (int) (price*quantity);
    }
    public String getTotalFormat() {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(getTotalamount());
    }

  
    
    
    
}

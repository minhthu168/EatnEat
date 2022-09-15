/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.entities.models;

import fpt.aptech.EatnEat.entities.IngredientToDay;
import java.util.List;

public class FoodToDay {
    private int foodid;
    private String foodname;
    private int quantity;
    private List<IngredientToDay> listIngre;

    public List<IngredientToDay> getListIngre() {
        return listIngre;
    }

    public void setListIngre(List<IngredientToDay> listIngre) {
        this.listIngre = listIngre;
    }
    public FoodToDay(){
        
    }
    public FoodToDay(int foodid, String foodname, int quantity) {
        this.foodid = foodid;
        this.foodname = foodname;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   
    
}

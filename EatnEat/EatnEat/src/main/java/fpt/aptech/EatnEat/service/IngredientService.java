/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.service;

import fpt.aptech.EatnEat.entities.Food;
import fpt.aptech.EatnEat.entities.models.FoodToDay;
import fpt.aptech.EatnEat.entities.Ingredient;
import fpt.aptech.EatnEat.entities.IngredientToDay;
import fpt.aptech.EatnEat.entities.Orderdetail;
import fpt.aptech.EatnEat.entities.Receipe;
import fpt.aptech.EatnEat.repository.IngredientRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class IngredientService {

    @Autowired
    IngredientRepository repository;
    @Autowired
    OrderDetailService detailService;
    @Autowired
    FoodService foodService;
    

    public List<Ingredient> findAll() {
        return repository.findAll();
    }

    public void save(Ingredient ingredient) {
        repository.save(ingredient);
    }

    public void delete(Ingredient ingredient) {
        repository.delete(ingredient);
    }
   

    public Ingredient findOne(Integer id) {
        return repository.getById(id);
    }
    //tim danh sach so luong moi mon an trong ngay
    public HashMap<Integer, FoodToDay> getListFoodToDay(List<Orderdetail> orders) {
        HashMap<Integer, FoodToDay> listFood = new HashMap<>();
        for (Orderdetail order : orders) {
            Food food = foodService.findOneFood(order.getFoodid().getFoodid());
            FoodToDay foodToDay = new FoodToDay(food.getFoodid(), food.getFoodname(), order.getQuantity());
            foodToDay.setListIngre(getListIngredientforFoodToDay(foodToDay));
            if (listFood.get(foodToDay.getFoodid()) != null) {
                FoodToDay foodOld = listFood.get(foodToDay.getFoodid());
                foodOld.setQuantity(foodOld.getQuantity() + foodToDay.getQuantity());
                foodOld.setListIngre(getListIngredientforFoodToDay(foodOld));
            } else {
                listFood.put(foodToDay.getFoodid(), foodToDay);
            }
        }

        return listFood;
    }

    //tim so nguyen lieu can cho moi mon trong ngay
    public List<IngredientToDay> getListIngredientforFoodToDay(FoodToDay f) {
        List<IngredientToDay> ingreTDList = new ArrayList<>();
        Food food = foodService.findOneFood(f.getFoodid());
        for (Receipe receipe : food.getReceipeList()) {
            //lay tung receipe cua food roi cap nhat so luong can cua moi ingredient cho order theo: sl food da dat* sl nguyen lieu can cho mon do
            //luu vao list
            IngredientToDay ingre = new IngredientToDay();

            ingre.setIngredientname(receipe.getIngredientid().getName());
            ingre.setQuantity(receipe.getQuantity() * f.getQuantity());
           ingre.setUnit(receipe.getUnit());
            ingreTDList.add(ingre);
        }
        return ingreTDList;
    }
    //tim tong nguyen lieu 
    public HashMap<String, IngredientToDay> getListIngredientToDay(HashMap<Integer, FoodToDay> foodDayList) {
        HashMap<String, IngredientToDay> ingreTDList = new HashMap<>();
        for (FoodToDay f : foodDayList.values()) {
            //goi ham tim nguyen lieu cho moi mon sau do cho vong lap for de tong nguyen lieu lai
            for (IngredientToDay ingre : getListIngredientforFoodToDay(f)) {
                //neu ten nguyen lieu trung thi cong so luong lai
                if (ingreTDList.get(ingre.getIngredientname()) != null) {
                    IngredientToDay old = ingreTDList.get(ingre.getIngredientname());
                    old.setQuantity(old.getQuantity() + ingre.getQuantity());

                } else {
                    ingreTDList.put(ingre.getIngredientname(), ingre);
                }
            }           
        }

        return ingreTDList;
    }

    

}

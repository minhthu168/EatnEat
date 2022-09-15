/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.service;

import fpt.aptech.EatnEat.entities.Food;
import fpt.aptech.EatnEat.repository.FoodRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class FoodService {
    @Autowired
    FoodRepository foodRepository;
    
    public List<Food> findAllFood() {
        return foodRepository.findAll();
    }
    
    public Food findOneFood(Integer foodid) {
        return foodRepository.findByFoodid(foodid);
    }
    
    public Food saveFood(Food newFood) {
        return foodRepository.save(newFood);
    }
    
    public void deleteFood(Food delFood) {
        foodRepository.delete(delFood);
    }
    
    public List<Food> findByFoodname(String foodname) {
        return foodRepository.findByFoodname("%"+foodname+"%");
    }
    public List<Food> categoryFood(String cat){
        return foodRepository.categoryFood(cat);
    }
    public HashMap<Integer,Food> RandomFood(List<Food> list){
        HashMap<Integer,Food> fList = new HashMap<>();
        int count = 0;
        for (int i=0;i<list.size()*3;i++) {
            Random rd = new Random();
            int number1 = rd.nextInt(list.size());
             if (list.get(number1)!=null && count <= 9) {
                fList.put(number1,list.get(number1));
                count++;
            }
        }
        return fList;
    }
    
    
}

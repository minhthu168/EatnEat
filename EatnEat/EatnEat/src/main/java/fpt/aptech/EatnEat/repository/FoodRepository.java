/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.repository;

import fpt.aptech.EatnEat.entities.Food;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author lenovo
 */
public interface FoodRepository extends JpaRepository<Food, Integer> {
    @Query("SELECT f FROM Food f WHERE f.foodid = :foodid")
    Food findByFoodid(Integer foodid);
    
    @Query("SELECT f FROM Food f WHERE f.category = :cat")
    List<Food> categoryFood(String cat);
    
   
    
    @Query("SELECT f FROM Food f WHERE f.foodname LIKE :foodname")
    List<Food> findByFoodname(String foodname);
}

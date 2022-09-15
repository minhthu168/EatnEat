/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.repository;

import fpt.aptech.EatnEat.entities.Food;
import fpt.aptech.EatnEat.entities.Ingredient;
import fpt.aptech.EatnEat.entities.Receipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author lenovo
 */
public interface ReceipeRepository extends JpaRepository<Receipe, Integer> {
    @Query("SELECT r FROM Receipe r WHERE r.foodid = :foodid AND r.ingredientid = :ingredientid")
    Receipe findOne(Food foodid, Ingredient ingredientid);
}

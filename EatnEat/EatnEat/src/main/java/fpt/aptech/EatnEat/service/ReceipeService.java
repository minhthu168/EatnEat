/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.service;

import fpt.aptech.EatnEat.entities.Food;
import fpt.aptech.EatnEat.entities.Ingredient;
import fpt.aptech.EatnEat.entities.Receipe;
import fpt.aptech.EatnEat.repository.ReceipeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class ReceipeService {
    @Autowired
    ReceipeRepository repository;
    
    
    public List<Receipe> findAll(){
        return repository.findAll();
    }
    public Receipe save(Receipe receipe){
        return repository.save(receipe);
    }
    public void delete(Receipe receipe){
        repository.delete(receipe);
    }
    public Receipe findOne(Integer id){
        return repository.getById(id);
    }
    public Receipe IsExsist(Food foodid,Ingredient ingredientid){
        return repository.findOne(foodid, ingredientid);
    }
}

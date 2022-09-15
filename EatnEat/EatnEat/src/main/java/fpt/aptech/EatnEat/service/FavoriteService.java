/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.service;

import fpt.aptech.EatnEat.entities.Favorite;
import fpt.aptech.EatnEat.repository.FavoriteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class FavoriteService {
    @Autowired
    FavoriteRepository favoriteRepo;

    public List<Favorite> findAll() {
        return favoriteRepo.findAll();
    }

    public Favorite create(Favorite favor) {
        return favoriteRepo.save(favor);
    }
    public Favorite findOne(String empid,Integer foodid) {
        return favoriteRepo.findOne(empid,foodid);
    }

    public void deleteAll() {
        favoriteRepo.deleteAll();
    }
    public void delete(Favorite favor){
        favoriteRepo.delete(favor);
    }
    
    public List<Favorite> getFavoriteListByEmp(String empid){
        return favoriteRepo.getFavoriteListByEmp(empid);
    }

    
}

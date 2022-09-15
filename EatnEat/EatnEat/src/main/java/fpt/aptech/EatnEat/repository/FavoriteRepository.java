/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.repository;

import fpt.aptech.EatnEat.entities.Favorite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author lenovo
 */
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    @Query("SELECT f FROM Favorite f WHERE f.empid = :empid")
    List<Favorite> getFavoriteListByEmp(String empid);
    
    @Query("SELECT f FROM Favorite f WHERE f.empid = :empid AND f.foodid=:foodid")
    Favorite findOne(String empid,Integer foodid);

}

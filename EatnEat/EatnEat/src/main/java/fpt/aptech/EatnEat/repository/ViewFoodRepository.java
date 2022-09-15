/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.repository;

import fpt.aptech.EatnEat.entities.Viewfood;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author lenovo
 */
public interface ViewFoodRepository extends JpaRepository<Viewfood, Integer> {
      @Query("SELECT v FROM Viewfood v WHERE v.category = 'set'")
      List<Viewfood> setList();
      
      @Query("SELECT v FROM Viewfood v WHERE v.category = 'option'")
      List<Viewfood> optionList();
      
      @Query("SELECT v FROM Viewfood v WHERE v.menutypeid = :menuid AND v.category = 'set'")
      List<Viewfood> searchByCat_SetList(Integer menuid);
      @Query("SELECT v FROM Viewfood v WHERE v.menutypeid = :menuid AND v.category = 'option'")
      List<Viewfood> searchByCat_OptionList(Integer menuid);
      
       @Query("SELECT v FROM Viewfood v WHERE v.foodid = :foodid")
      Viewfood findOneFood(int foodid);
}

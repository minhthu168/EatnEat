/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.repository;

import fpt.aptech.EatnEat.entities.Menutype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author THINKPAD
 */
public interface MenutypeRepository extends JpaRepository<Menutype, Integer> {
    @Query("SELECT m FROM Menutype m WHERE m.menutypeid = :menutypeid")
    Menutype findByMenutypeid(Integer menutypeid);
}

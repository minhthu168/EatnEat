/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.repository;

import fpt.aptech.EatnEat.entities.Viewemployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author lenovo
 */
public interface ViewEmployeeRepository extends JpaRepository<Viewemployee, String> {
     @Query("SELECT e FROM Viewemployee e WHERE e.phone = :phone")
    Viewemployee checkPhone(String phone);
}

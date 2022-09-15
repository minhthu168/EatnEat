/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.repository;

import fpt.aptech.EatnEat.entities.*;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author lenovo
 */
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    
    //tim order cua employee trong ngay
    @Query("SELECT v FROM Orders v WHERE v.empid = :empid AND DATEDIFF(day, v.orderdate, GETDATE())=0")
    Orders findByEmpToDay(Employee empid);
    
    //hien thi tat ca ds order theo empid muon tim va sap xep theo orderdate giam dan
    @Query("SELECT o FROM Orders o WHERE o.empid = :empid ORDER BY o.orderdate DESC")
    List<Orders> findAllOrderByEmpid(Employee empid);
    
   
    
   //tim kiem order theo empid va orderdate la ngay hom nay
    @Query("SELECT o FROM Orders o WHERE o.empid = :empid AND DATEDIFF(day, o.orderdate, GETDATE())=0")
    List<Orders> searchOrder(Employee empid);
    
    @Query("SELECT v FROM Orders v WHERE DATEDIFF(day,v.orderdate,:searchday)=0")
    List<Orders> OrdersOfDayList(Date searchday);
    
    @Query("SELECT v FROM Orders v WHERE DATEDIFF(day,v.orderdate,:timeFrom)<=0 AND DATEDIFF(day,v.orderdate,:timeTo)>=0")
    List<Orders> SearchFromTo(Date timeFrom,Date timeTo);
      
    @Query("SELECT v FROM Orders v WHERE DATEDIFF(month,v.orderdate,:searchmonth)=0")
    List<Orders> OrdersOfMonthList(Date searchmonth);
    
    
    @Query("SELECT v FROM Orders v WHERE DATEDIFF(MONTH,v.orderdate,getdate())=0 AND v.empid = :Empid")
    List<Orders> orders_Emp_mounth(Employee Empid);
    
    
}

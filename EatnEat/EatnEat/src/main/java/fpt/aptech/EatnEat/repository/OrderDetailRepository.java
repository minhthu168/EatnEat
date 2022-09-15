/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.repository;

import fpt.aptech.EatnEat.entities.Orderdetail;
import fpt.aptech.EatnEat.entities.Orders;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author lenovo
 */
public interface OrderDetailRepository extends JpaRepository<Orderdetail, Integer> {

    @Query("SELECT v FROM Orderdetail v WHERE v.orderid = :orderid")
    List<Orderdetail> detail(Orders orderid);
    
    @Query("SELECT v FROM Orderdetail v WHERE v.orderid = :orderid")
    List<Orderdetail> detailOrder(Integer orderid);

    @Query("SELECT v FROM Orderdetail v WHERE DATEDIFF(day,v.date,:searchday)=0")
    List<Orderdetail> FoodsOfDayList(Date searchday);

    @Query("SELECT v FROM Orderdetail v WHERE DATEDIFF(day,v.date,:timeFrom)<=0 AND DATEDIFF(day,v.date,:timeTo)>=0")
    List<Orderdetail> SearchFromTo(Date timeFrom, Date timeTo);

    @Query("SELECT o FROM Orderdetail o WHERE DATEDIFF(day,o.date,getdate())=0")
    List<Orderdetail> OrdersOfDayList();
}

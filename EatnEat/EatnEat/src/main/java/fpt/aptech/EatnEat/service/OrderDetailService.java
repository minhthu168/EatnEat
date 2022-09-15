/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.service;

import fpt.aptech.EatnEat.entities.Orderdetail;
import fpt.aptech.EatnEat.entities.Orders;
import fpt.aptech.EatnEat.repository.*;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class OrderDetailService {
    @Autowired
    OrderDetailRepository detailRepository;
   
    public List<Orderdetail> detail(Orders orderid) {
        return detailRepository.detail(orderid);
    }
    public List<Orderdetail> detailOrder(Integer orderid) {
        return detailRepository.detailOrder(orderid);
    }
    public List<Orderdetail> FoodsOfDay(Date searchday) {
        return detailRepository.FoodsOfDayList(searchday);
    }
    public List<Orderdetail> SearchFromTo(Date timeFrom,Date timeTo) {
        return detailRepository.SearchFromTo(timeFrom, timeTo);
    }
    public int TotalAmountforDetail(Date searchdate) {
        int amount=0;
        for (Orderdetail vieworder : FoodsOfDay(searchdate)) {
            amount=amount+vieworder.getTotalamount();
        }
        return amount;
    }  
    
    public Orderdetail saveOrderDetail(Orderdetail orderdetail){
        return detailRepository.save(orderdetail);
    }
    
}

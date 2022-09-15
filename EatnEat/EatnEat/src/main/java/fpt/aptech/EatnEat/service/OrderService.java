/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.service;

import fpt.aptech.EatnEat.entities.Employee;
import fpt.aptech.EatnEat.entities.Orders;
import fpt.aptech.EatnEat.repository.OrderRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class OrderService {
    
    @Autowired
    OrderRepository orderRepo;
    
    public Orders create(Orders order) {
        return orderRepo.save(order);
    }
    public List<Orders> findAll(){
        return orderRepo.findAll(Sort.by("orderid").descending());
    }
    
    public Orders findOne(Integer orderid){
        return orderRepo.getById(orderid);
    }
     public Orders findByEmpToDay(Employee empid){
        return orderRepo.findByEmpToDay(empid);
    }
    
    public List<Orders> searchOrder(Employee empid) {
        return orderRepo.searchOrder(empid);
    }
    
     public List<Orders> findAllOrderByEmpid(Employee empid) {
        return orderRepo.findAllOrderByEmpid(empid);
    }
     
    
     
    public List<Orders> SearchFromTo(Date timeFrom,Date timeTo) {
        return orderRepo.SearchFromTo(timeFrom, timeTo);
    }
    
    public List<Orders> OrdersOfDay(Date searchday) {
        return orderRepo.OrdersOfDayList(searchday);
    }
    
    public int TotalAmount(List<Orders> orders){
        int amount=0;
        for (Orders order : orders) {
            amount=amount+order.getTotalamount();
        }
        return amount;
    }
    
    public List<Orders> OrdersOfMonth(Date searchmonth) {
        return orderRepo.OrdersOfMonthList(searchmonth);
    }
    public List<Orders> orders_emp_mounth(Employee Empid){
        return orderRepo.orders_Emp_mounth(Empid);
    }
    
}

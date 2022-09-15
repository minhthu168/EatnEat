/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.entities.models;

import fpt.aptech.EatnEat.entities.Employee;
import fpt.aptech.EatnEat.entities.Food;
import fpt.aptech.EatnEat.entities.Orders;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class OrdersModel {
    public Orders order;
    public Employee emp;
    public List<Food> food; 

    public OrdersModel(Orders order, Employee emp, List<Food> food) {
        this.order = order;
        this.emp = emp;
        this.food = food;
    }

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.entities.models;

import java.util.Date;

/**
 *
 * @author lenovo
 */
public class ViewOrders {
    private Integer orderid;
    private String empid;
    private String food;
    private int quantity;
    private int totalamount;
    private Date orderdate;

    public ViewOrders() {
    }

    public ViewOrders(Integer orderid, String empid, String food, int quantity, int totalamount, Date orderdate) {
        this.orderid = orderid;
        this.empid = empid;
        this.food = food;
        this.quantity = quantity;
        this.totalamount = totalamount;
        this.orderdate = orderdate;
    }
    

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(int totalamount) {
        this.totalamount = totalamount;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }
    
}

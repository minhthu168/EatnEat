/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.entities.models;

/**
 *
 * @author lenovo
 */
public class SalesEmp {
    private String Empid;
    private String Empname;
    private int orders;
    private int foods;
    private int totalamount;

    public SalesEmp() {
    }

    public SalesEmp(String Empid, String Empname, int orders, int foods, int totalamount) {
        this.Empid = Empid;
        this.Empname = Empname;
        this.orders = orders;
        this.foods = foods;
        this.totalamount = totalamount;
    }
    

    public String getEmpid() {
        return Empid;
    }

    public void setEmpid(String Empid) {
        this.Empid = Empid;
    }

    public String getEmpname() {
        return Empname;
    }

    public void setEmpname(String Empname) {
        this.Empname = Empname;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getFoods() {
        return foods;
    }

    public void setFoods(int foods) {
        this.foods = foods;
    }

    public int getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(int totalamount) {
        this.totalamount = totalamount;
    }

    
    
    
}

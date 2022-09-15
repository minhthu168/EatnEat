package fpt.aptech.eatneatapp.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

public class Vieworders implements Serializable{
    private int orderid;
    private String empid;
    private String food;
    private int quantity;
    private int totalamount;
    private Date orderdate;

    public Vieworders() {
    }


    public Vieworders(int orderid, String empid, String food, int quantity, int totalamount, Date orderdate) {
        this.orderid = orderid;
        this.empid = empid;
        this.food = food;
        this.quantity = quantity;
        this.totalamount = totalamount;
        this.orderdate = orderdate;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
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
    public String getTotalFormat() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(getTotalamount());
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

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }
}

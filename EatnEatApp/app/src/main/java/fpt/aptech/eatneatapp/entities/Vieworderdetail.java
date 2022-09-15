package fpt.aptech.eatneatapp.entities;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Vieworderdetail implements Serializable {
    private int id;
    private int orderid;
    private int foodid;
    private String foodname;
    private int price;
    private String image;
    private int quantity;
    private int totalamount;

    public Vieworderdetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }
    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getTotalFormat() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(getTotalamount());
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public void setTotalamont(int totalamount) {
        this.totalamount = totalamount;
    }
}

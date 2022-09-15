package fpt.aptech.eatneatapp.entities;

import java.text.DecimalFormat;

public class Item {
    private int foodid;
    private String foodname;
    private int price;
    private String image;
    private int quantity;

    public Item() {
    }

    public Item(int foodid, String foodname, int price, String image, int quantity) {
        this.foodid = foodid;
        this.foodname = foodname;
        this.price = price;
        this.image = image;
        this.quantity = quantity;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public String getPriceFormat() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(getPrice());
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



}

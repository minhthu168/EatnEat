package fpt.aptech.eatneatapp.entities;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Viewfood implements Serializable {
    private int foodid;
    private String foodname;
    private int price;
    private String category;
    private int menutypeid;
    private String type;
    private String image;


    public Viewfood() {
    }

    public Viewfood(int foodid, String foodname, int price, String category, int menutypeid, String type, String image) {
        this.foodid = foodid;
        this.foodname = foodname;
        this.price = price;
        this.category = category;
        this.menutypeid = menutypeid;
        this.type = type;
        this.image = image;
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
    public String getPriceFormat() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(price);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMenutypeid() {
        return menutypeid;
    }

    public void setMenutypeid(int menutypeid) {
        this.menutypeid = menutypeid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

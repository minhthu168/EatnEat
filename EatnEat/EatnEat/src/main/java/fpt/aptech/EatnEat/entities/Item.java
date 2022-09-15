package fpt.aptech.EatnEat.entities;


public class Item {
    private Food food;
    private String name;
    private int price;
    private int quantity;

    public Item() {
    }

    public Item(Food food, String name, int price, int quantity) {
        this.food = food;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}

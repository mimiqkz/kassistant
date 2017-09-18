package teymi15.model;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Ingredient {
    private int ID;
    private double price; //Using different currency
    private String name; //Name of the ingredient e.g cherry tomato, apple etc.
    private String location;
    private String store;

    public Ingredient(int id, double price, String name, String location, String store) {
        this.ID = id;
        this.price = price;
        this.name = name;
        this.location = location;
        this.store = store;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}

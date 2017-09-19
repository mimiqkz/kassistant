package teymi15.kassistant.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Ingredient {

    private int id;
    private double price; //Using different currency
    private String name; //Name of the ingredient e.g cherry tomato, apple etc.
    private String location;
    private String store;

    public Ingredient(int id, double price, String name, String location, String store) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.location = location;
        this.store = store;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
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
    @Override
    public String toString() {
        return String.format(
                "Ingredient[id=%d, name='%s']",
                id, name);
    }
}

package teymi15.kassistant.model;

import javax.persistence.*;

/**
 * The program allows user to search for recipe with the matching name.
 * For example, "Apple pie", "Brocoli soup", "Chocolate Cake" , etc.
 *
 * @author Nu Phan Quynh Do
 * @author  Alexander Freyr Sveinsson
 * @author Alexandra Mjöll Young
 * @version 1.0
 * @since   2017-09-20
 */

//The object class of ingredient
    @Entity
    @Table(name = "Ingredients")
public class Ingredient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price; //Using different currency
    private String name; //Name of the ingredient e.g cherry tomato, apple etc.
    private String location; //Location of the store e.g Granda, Kringlan etc
    private String store; //The name of the store
    private Recipe recipe; //vísa í Recipe

    public Ingredient(int id, double price, String name, String location, String store) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.location = location;
        this.store = store;
    }
    @Column(name = "id")
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "store")
    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setRecipe(Recipe recipe){
        this.recipe = recipe;
    }


    @Override
    public String toString() {
        return String.format(
                "Ingredient[id=%d, name='%s']",
                id, name);
    }
}

package teymi15.kassistant.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @Table(name = "Ingredient")
public class Ingredient {

    @Id
    @Column(name = "ingredientId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price; //Using different currency
    private String name; //Name of the ingredient e.g cherry tomato, apple etc.
    private String location; //Location of the store e.g Granda, Kringlan etc
    private String store; //The name of the store
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "ingredients")
    private Set<Recipe> recipes = new HashSet<>();

    public Ingredient(double price, String name, String location, String store,Set recipes) {
        this.price = price;
        this.name = name;
        this.location = location;
        this.store = store;
        this.recipes = recipes;
    }
    public  Ingredient(){}

    public  Ingredient( int id){
        this.id = id;
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

    @Override
    public String toString() {
        return String.format(
                "Ingredient[id=%d, name='%s']",
                id, name);
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipe(Recipe r){
        recipes.add(r);
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}

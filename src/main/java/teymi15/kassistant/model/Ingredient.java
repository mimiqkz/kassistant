package teymi15.kassistant.model;

import org.hibernate.annotations.GeneratorType;
import teymi15.kassistant.SQLsafety.SQLInjectionSafe;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The program allows user to search for recipe with the matching name. For
 * example, "Apple pie", "Brocoli soup", "Chocolate Cake" , etc.
 *
 * @author Nu Phan Quynh Do
 * @author Alexander Freyr Sveinsson
 * @author Alexandra Mjöll Young
 * @version 1.0
 * @since 2017-09-20
 */
//The object class of ingredient
//make this as an entetiy in the database
@Entity
@Table(name = "Ingredient")
public class Ingredient {

    //id is the primary key and can be generated by the
    //database
    @Id
    @Column(name = "ingredientId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price; //Using different currency
    private @SQLInjectionSafe String name; //Name of the ingredient e.g cherry tomato, apple etc.
    private @SQLInjectionSafe String location; //Location of the store e.g Granda, Kringlan etc
    private @SQLInjectionSafe String store; //The name of the store
    private String photoURL;

    //many to many connection between the recipes and ingredients
    //mapped by the Set ingredients in the Recipe controller
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recipe> recipes = new HashSet<>();

    public Ingredient(Integer id, double price, String name, String location, String store, Set recipes) {
        this.price = price;
        this.name = name;
        this.location = location;
        this.store = store;
        this.recipes = recipes;
        this.id = id;
    }

    public Ingredient() {
    }

    public Ingredient(Integer id) {
        this.id = id;
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

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipe(Recipe r) {
        recipes.add(r);
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
    @Column(name = "photo")
    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
}

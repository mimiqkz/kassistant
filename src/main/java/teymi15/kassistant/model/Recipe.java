package teymi15.kassistant.model;
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


import teymi15.kassistant.SQLsafety.SQLInjectionSafe;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


/**
 * The object class of recipe
 * is a entity in the data base
 */
@Entity
@Table (name = "Recipe")
public class Recipe {

    @Id
    @Column(name = "recipeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // the primary key in the recipe table
    private @SQLInjectionSafe String name; // string for the name of the recipe
    private @SQLInjectionSafe String instruction; // instruction string

    @ManyToOne
    @JoinColumn(name="userId")
    private User userCheater;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> userLiked;
    /**
    * connecting the to tables recipe and user with a many to many
     * relacion makes a table that conects the ingredients and recipes
    * */
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients = new HashSet<Ingredient>();

    public Recipe() {}
    public Recipe(String name, String instruction, Set ingredients, Integer id){
        this.ingredients = ingredients;
        this.name = name;
        this.instruction= instruction;
    }
    public Recipe(String name, String instruction){
        this.name = name;
        this.instruction= instruction;
        this.id = id;
    }
    public  Recipe(int id){
        this.id =id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "instruction")
    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Column(name = "ingredients")
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredients(Ingredient i){
        ingredients.add(i);
    }

    @Override
    public String toString() {
        return String.format(
                "Recipe[id=%d, name='%s', ingredients = '%s', instruction='%s']",
                id, name, ingredients, instruction);
    }

    @Column(name = "userCreater")
    public User getUserCheater() {
        return userCheater;
    }

    public void setUserCheater(User userCheater) {
        this.userCheater = userCheater;
    }

    @Column(name = "userLiked")
    public Set<User> getUserLiked() {
        return userLiked;
    }

    public void setUserLiked(Set<User> userLiked) {
        this.userLiked = userLiked;
    }
}

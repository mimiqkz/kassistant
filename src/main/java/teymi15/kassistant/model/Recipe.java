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
    private String name; // string for the name of the recipe
    private String instruction; // instruction string
    /**
    * connecting the to tables recipe and user with a many to many
     * relacion makes a table that conects the ingredients and recipes
    * */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "RIngridients",joinColumns = {
            @JoinColumn(name = "recipeId",nullable = false,updatable = false)
    }, inverseJoinColumns = { @JoinColumn(name = "ingredientId",
            nullable = false, updatable = false)})
    private Set<Ingredient> ingredients = new HashSet<Ingredient>();

    public Recipe() {}
    public Recipe(String name,String instruction,Set ingredients,int id){
        this.ingredients = ingredients;
        this.name = name;
        this.instruction= instruction;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}

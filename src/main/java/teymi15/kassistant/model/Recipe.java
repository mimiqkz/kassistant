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
 */
@Entity
@Table (name = "Recipe")
public class Recipe {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String instruction;
    @ManyToMany(mappedBy = "recipe",fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients = new HashSet<Ingredient>();

    public Recipe() {
        this.ingredients = new HashSet<Ingredient>();
    }
    public Recipe(String name,String instruction){
        this.ingredients = new HashSet<Ingredient>();
        this.name = name;
        this.instruction= instruction;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return String.format(
                "Recipe[id=%d, name='%s', ingredients = '%s', instruction='%s']",
                id, name ,ingredients, instruction);
    }
    public void addIngredient(Ingredient i){
        i.setRecipe(this);
        ingredients.add(i);
    }
}

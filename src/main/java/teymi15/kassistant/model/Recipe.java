package teymi15.kassistant.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;


public class Recipe {

    private int id;
    private String name;
    private String instruction;
    private List<Ingredient> ingredients;

    public Recipe(int id, String name, String instruction, List<Ingredient> in) {
        this.id = id;
        this.name = name;
        this.instruction = instruction;
        this.ingredients = in;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return String.format(
                "Recipe[id=%d, name='%s', ingredients = '%s', instruction='%s']",
                id, name, ingredients, instruction);
    }
}

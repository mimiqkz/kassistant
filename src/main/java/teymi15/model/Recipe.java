package teymi15.model;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Recipe {
    private int ID;
    private String name;
    private String instruction;
    private Ingredient[] ingredients;

    public Recipe(int ID, String name, String instruction, Ingredient[] in) {
        this.ID = ID;
        this.name = name;
        this.instruction = instruction;
        this.ingredients = in;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }
}

package teymi15.kassistant.service;

import teymi15.kassistant.model.Recipe;

import java.util.List;

public interface RecipeService {


    /**
     * Add the recipe
     *
     * @param k Recipe
     */
    public void addRecipe(Recipe k);

    /**
     * Return all of the matching recipe
     * 
     * @param k the name of the recipe
     * @return list of matching recipe
     */
    public List<Recipe> matchingRecipe(String k);
}

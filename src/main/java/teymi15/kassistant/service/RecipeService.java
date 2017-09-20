package teymi15.kassistant.service;
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
    /**
     * Return all of the recipes
     *
     *
     * @return list of matching recipe
     */


}

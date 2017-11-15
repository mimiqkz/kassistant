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
import org.springframework.stereotype.Service;
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.model.User;

import java.util.List;

/**
 * The service class for the recipe, getting data from the repository
 */
@Service
public interface RecipeService {


    /**
     * Add the recipe
     *
     * @param k Recipe
     */
    boolean addRecipe(Recipe k);

    /**
     * Return all of the matching recipe
     *
     * @param name the name of the recipe
     * @return list of matching recipe
     */
    List<Recipe> getMatchingRecipe(String name);

    /**
     * Get recipe by the id
     * @param id recipe id
     * @return the recipe
     */
    Recipe getRecipeById(int id);

    /**
     * To test if the service is alive
     * @return true if the service is alive
     */
    boolean isAlive();

    /**
     *
     * @param name
     * @param description
     * @param instructions
     * @param ingredients
     * @param pic
     * @param user
     * @return
     */
    Recipe createRecipe(String name, String description, String[] instructions, String[] ingredients, byte[] pic, User user);

    /**
     *
     * @param recipe
     * @param name
     * @param description
     * @param instructions
     * @param ingredients
     * @return
     */
    Recipe editRecipe(Recipe recipe, String name, String description, String[] instructions, String[] ingredients);

    /**
     *
     * @param recipe
     */
    void deleteRecipe(Recipe recipe);

    /**
     *
     * @param instructions
     * @return
     */
    String mergeInstructions(String[] instructions);

    String[] splitInstructions(String instruction);
    /**
     * @return boolean
     * @param user
     * @param recipe
     */
    boolean likeRecipe(User user, Recipe recipe);

    /**
     *
     * @param user
     * @param recipe
     * @return
     */
    boolean unlikeRecipe(User user, Recipe recipe);



}

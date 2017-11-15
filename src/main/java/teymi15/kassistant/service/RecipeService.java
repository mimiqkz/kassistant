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
     * Add recipe
     * @param k recipe
     * @return boolean
     */
    boolean addRecipe(Recipe k);

    /**
     * Return all of the matching recipe
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
     * Create recipe
     * @param name
     * @param description
     * @param instructions
     * @param ingredients
     * @param pic of the recipe
     * @param user who creates the recipe
     * @return the recipe
     */
    Recipe createRecipe(String name, String description, String[] instructions, String[] ingredients, byte[] pic, User user);

    /**
     * Edit the recipe
     * @param recipe
     * @param name
     * @param description
     * @param instructions
     * @param ingredients
     * @return the recipe
     */
    Recipe editRecipe(Recipe recipe, String name, String description, String[] instructions, String[] ingredients);

    /**
     * Delete selected recipe
     * @param recipe
     */
    void deleteRecipe(Recipe recipe);

    /**
     * Merging the intruction to store in the database
     * @param instructions
     * @return String of intructions
     */
    String mergeInstructions(String[] instructions);

    /**
     * Split the instructions
     * @param instruction
     * @return The array of splitted instructions
     */
    String[] splitInstructions(String instruction);

    /**
     * The recipe which has been liked by user
     * @param user
     * @param recipe
     * @return boolean
     */
    boolean likeRecipe(User user, Recipe recipe);

    /**
     * The recipe which was unliked by user
     * @param user
     * @param recipe
     * @return boolean
     */
    boolean unlikeRecipe(User user, Recipe recipe);



}

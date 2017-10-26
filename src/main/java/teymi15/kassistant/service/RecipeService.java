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
import teymi15.kassistant.model.Recipe;
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


}

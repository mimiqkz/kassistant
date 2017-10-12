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
import java.util.List;

@Service
public interface IngredientService {

    /**
     * Add the recipe
     *
     * @param k Ingredient
     */
    void addRecipe(Ingredient k);

    /**
     * Return all of the matching ingredient
     *
     * @param name the name of the ingredient
     * @return list
     */
    List<Ingredient> getMatchingIngredient(String name);

    /**
     * Get the ingredient by the id
     * @param id ingredient id
     * @return the ingredient
     */
    Ingredient getIngredientById(int id);

    /**
     * adding all the information of the recipe
     */
    void addAllData();

    /**
     * Getting all the ingredient
     * @return list of ingredient
     */
    List<Ingredient> getAllIngredient();

}
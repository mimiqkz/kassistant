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

import java.util.List;

/**
 * The service class for ingredient
 */
@Service
public interface IngredientService {


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
     * Getting all the ingredient
     * @return list of ingredient
     */
    List<Ingredient> getAllIngredient();

    /**
     * Get all matching ingredients by names
     * @param names
     * @return List of the ingredient with the matching names
     */
    List<Ingredient> getAllMatchingIngredients(String [] names);

    /**
     * Create an ingredient
     * @param name of the ingredient
     * @param description of the ingredient
     * @param bytes image of the ingredient
     * @return the created ingredient
     */
    Ingredient createIngredient(String name, String description, byte[] bytes);

    /**
     * Adding the ingredient
     * @param ingredient
     * @return boolean
     */
    boolean addIngredient(Ingredient ingredient);

    /**
     * Update the ingredient
     * @return boolean
     * @param store
     * @param location
     * @param price
     **/
    Ingredient updatePrice(Ingredient ingredient, String store, String location, int price);


}

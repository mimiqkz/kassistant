package teymi15.kassistant.control;
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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import teymi15.kassistant.service.IngredientServiceImp;
import teymi15.kassistant.service.RecipeServiceImp;

import teymi15.kassistant.model.Recipe;

/**
 * The class searches for the correct recipe
 */
public class SearchController {

    @Autowired
    RecipeServiceImp RecipeService;

    @Autowired
    IngredientServiceImp IngredientService;

    public List searchRecipeByName(String name){
        return RecipeService.getMatchingRecipe(name);
    }

    public Recipe getRecipebyID(int id){
        return RecipeService.getRecipeById(id);
    }

    public List searchIngredientByName(String name){
        return IngredientService.getMatchingIngredient(name);
    }

    public List getAllIngredients(){return  IngredientService.getAllIngredient();}

}

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
import java.util.ArrayList;
import java.util.List;

import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.model.Recipe;

import teymi15.kassistant.repository.*;
import teymi15.kassistant.service.RecipeServiceImp;

public class SearchManager {
    private RecipeServiceImp repo = new RecipeServiceImp();

    public List searchRecipeByName(String name){
        return repo.matchingRecipe(name);
    }


}

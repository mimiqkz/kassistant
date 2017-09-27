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

import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.service.RecipeServiceImp;

/**
 * The class searches for the correct recipe
 */
public class SearchController {
    private RecipeServiceImp repo = new RecipeServiceImp();

    public List searchRecipeByName(String name){
        return repo.matchingRecipe(name);
    }

    public Recipe getRecipebyID(int id){ return repo.getRecipeById(id);};


}

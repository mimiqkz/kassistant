
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.repository.RecipeRepository;

import java.util.List;

@Service
public class RecipeServiceImp implements RecipeService {

    // Connection to the recipe repository
    @Autowired
    RecipeRepository recipeRep;


    @Override
    public void addRecipe(Recipe k) {
        recipeRep.save(k);
    }

    //search for matching recipe by name
    @Override
    @ResponseBody
    public List<Recipe> getMatchingRecipe(String name) {
        return recipeRep.findAllByNameLikeIgnoreCase("%" +name+ "%");
    }


    //find recipe by its id
    @Override
    @ResponseBody
    public Recipe getRecipeById(int id) {
        return null;
    }
}

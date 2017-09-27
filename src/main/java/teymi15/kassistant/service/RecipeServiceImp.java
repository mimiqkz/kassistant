
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
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.repository.RecipeRepository;
import teymi15.kassistant.repository.RecipeRepositoryImp;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImp implements RecipeService{

    // Connection to the recipe repository
    @Autowired
    RecipeRepository recipeRep;

    // Add Recipe to the service
    @Override
    public void addRecipe(Recipe k) {
        recipeRep.add(k);
    }

    //search for matching recipe by name
    @Override
    public List<Recipe> matchingRecipe(String k) {
        List found = new ArrayList();
        recipeRep = new RecipeRepositoryImp();
        recipeRep.addAll();
        List<Recipe> everything = recipeRep.getAll();
        for(int i = 0;i<everything.size();i++){
            if(everything.get(i).getName().equals(k)){
                found.add(everything.get(i));
            }
        }

        return found;
    }

    @Override
    public Recipe getRecipeById(int id) {
        System.out.println("here " + id);
        List<Recipe> everything = recipeRep.getAll();
        for (int i = 0; i < everything.size(); i++) {
            if (everything.get(i).getID() == id) {
                return everything.get(i);
            }
        }
        return null;
    }


}

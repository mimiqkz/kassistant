
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
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.model.User;
import teymi15.kassistant.repository.IngredientRepository;
import teymi15.kassistant.repository.RecipeRepository;
import teymi15.kassistant.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class KassistantServiceImp implements KassistantService{



    // Connection to the recipe repository
    @Autowired
    RecipeRepository recipeRep;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IngredientRepository ingredientRepository;
    @Override
    public void addRecipe(Recipe k) {
        recipeRep.save(k);
    }

    //search for matching recipe by name
    @Override
    public List<Recipe> matchingRecipe(String k) {
        List found = new ArrayList();
      /*  recipeRep = new RecipeRepository();
        recipeRep.addAll();
        List<Recipe> everything = recipeRep.getAll();
        for(int i = 0;i<everything.size();i++){
            if(everything.get(i).getName().equals(k)){
                found.add(everything.get(i));
            }
        }
*/
        return found;
    }
    @Override
    public List<Ingredient> matchingIngredient(String k) {
        List found = new ArrayList();
        return found;
    }

    //find recipe by its id
    @Override
    public Recipe getRecipeById(int id) {
        /*List<Recipe> everything = recipeRep.getAll();
        for (int i = 0; i < everything.size(); i++) {
            if (everything.get(i).getID() == id) {
                return everything.get(i);
            }
        }*/
        return null;
    }

    @Override
    public void addAllData(){
        User u = new User("123456789","admin","admin",23,1);
        userRepository.save(u);
        Set s = new HashSet();
        Ingredient i = new Ingredient(120,"chick pees","reykjavík","bónus",s);
        Ingredient i2 = new Ingredient(400,"olive oil","reykjavík","bónus",s);
        Recipe recipe = new Recipe("hummus","lorum lipsum lal li boobbs",s,2);
        recipe.addIngredients(i);
        recipe.addIngredients(i2);
        i.addRecipe(recipe);
        ingredientRepository.save(i);
        ingredientRepository.save(i2);
        recipeRep.save(recipe);

    }
    @Override
    public List getAllIngredient(){
        return ingredientRepository.findAll();

    }


}

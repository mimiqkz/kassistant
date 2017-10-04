
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
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.model.User;
import teymi15.kassistant.repository.IngredientRepository;
import teymi15.kassistant.repository.RecipeRepository;
import teymi15.kassistant.repository.UserRepository;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    @ResponseBody
    public List<Recipe> getMatchingRecipe(String k) {
        List<Recipe> found = recipeRep.findAllByName("%" +k+ "%");
        if(found!=null){System.out.print(found.get(0));
            }
        return found;
    }
    @Override
    @ResponseBody
    public List<Ingredient> getMatchingIngredient(String k) {

        List<Ingredient> stuff = ingredientRepository.findByName("%"+k+"%");
        if(stuff != null){
            System.out.print(stuff.get(0));
        }
        return stuff;
    }

    //find recipe by its id
    @Override
    @ResponseBody
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
    @ResponseBody
    public void addAllData(){
        User u = new User("123456789","admin","admin",23);
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
    @ResponseBody
    public List getAllIngredient(){
        return ingredientRepository.findAll();

    }


}

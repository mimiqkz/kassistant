
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
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import java.util.List;
import java.util.Map;

@Service
public class RecipeServiceImp implements RecipeService {

    // Connection to the recipe repository
    @Autowired
    RecipeRepository recipeRep;


    @Override
    @ResponseBody
    public boolean addRecipe(Recipe k) {
        try{
            recipeRep.save(k);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
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
        return recipeRep.findById(id);
    }
}

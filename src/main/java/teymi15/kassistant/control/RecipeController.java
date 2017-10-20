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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import teymi15.kassistant.service.IngredientServiceImp;
import teymi15.kassistant.service.RecipeServiceImp;

import teymi15.kassistant.model.Recipe;

import javax.servlet.http.HttpSession;

/**
 * The class searches for the correct recipe
 */
@Controller
public class RecipeController {

    @Autowired
    RecipeServiceImp RecipeService;

    @Autowired
    IngredientServiceImp IngredientService;


    /**
     * Get the matching recipe that contains the searched name
     * @param name name of the recipe
     * @return List of the recipe
     */
    public List searchRecipeByName(String name){
        return RecipeService.getMatchingRecipe(name);
    }

    /**
     * Get the matching ingredient that contains the searched name
     * @param name name of the ingredient
     * @return List of the ingredient
     */
    public List searchIngredientByName(String name){
        return IngredientService.getMatchingIngredient(name);
    }

    @RequestMapping(value = "create-recipe", method = RequestMethod.GET)
    public String displayRecipeForm(HttpSession session, Model model) {
        displayLoggedInUser(session, model);
        return "createRecipe";
    }
    @RequestMapping(value = "submit-recipe")
    public String submitRecipe(HttpSession session, Model model) {
        displayLoggedInUser(session, model);
        return "homepage";
    }

    public void displayLoggedInUser(HttpSession session, Model model) {
        if(!session.isNew()) {
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("loggedIn", true);
        }
    }

}

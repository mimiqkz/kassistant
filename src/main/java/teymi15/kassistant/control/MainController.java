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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.model.Recipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import teymi15.kassistant.service.IngredientService;
import teymi15.kassistant.service.IngredientServiceImp;
import teymi15.kassistant.service.RecipeServiceImp;

/**
 * The class controls the main page to tells which route it should be rending
 * and what information it should be displayed
 */

@Controller
public class MainController {
    
    @Autowired
    RecipeServiceImp RecipeService;

    @Autowired
    IngredientServiceImp IngredientService;

    List<Recipe> mostPopular; //the most popular recipe



    /**
     * The function tells the search page to be displayed at route predefined
     * in the configuration application.properties
     *
     * @return String
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHomePage(HttpSession session, Model model) {
        displayLoggedInUser(session, model);
        System.out.println("Here " + session.getId());
        return "homepage";
    }


    /**
     * The function returns a string with the route which should be rendered. This
     *  is initiated when the user submits his/her input. The input from the user
     *  should then be displayed on the result page.
     * @param request getting request from the page
     * @param model model
     * @return String
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String submitSearch(HttpServletRequest request, HttpSession session, Model model) {
        String search = request.getParameter("search");
        String option = request.getParameter("select-option");
        displayLoggedInUser(session, model);
        if(option.equals("recipe")) {
            List<Recipe> recipes = RecipeService.getMatchingRecipe(search);
            model.addAttribute("recipeList", recipes);
        }else {
            List<Ingredient> ingredients = IngredientService.getMatchingIngredient(search);
            model.addAttribute("recipeList", ingredients);
        }

        return "resultpage";
    }


    /**
     * The function returns a string with the route which should be rendered. This
     *  is initiated when the user selects a link that represents a Recipe. This Recipe
     *  should then be displayed on the recipe page.
     * @param id int
     * @param model model
     * @return String
     */
    @RequestMapping(value="recipe/{id}", method = RequestMethod.GET)
    public String selectRecipe (@PathVariable int id, HttpSession session, Model model) {
        Recipe selected = RecipeService.getRecipeById(id);
        displayLoggedInUser(session, model);
        model.addAttribute("recipe", selected);
        return "recipe";
    }

    /**
     *  The function tells the login page to be displayed at path returned
     *  by the string
     * @return String
     *
     */
    @RequestMapping(value="login", method = RequestMethod.GET)
    public String displayLoginPage () {return "login";}

    public void displayLoggedInUser(HttpSession session, Model model) {
        if(!session.isNew()) {
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("loggedIn", true);
        }
    }

}


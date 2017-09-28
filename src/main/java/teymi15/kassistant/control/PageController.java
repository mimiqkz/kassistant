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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.model.Recipe;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * The class controls the main page to tells which route it should be rending
 * and what information it should be displayed
 */

@Controller
public class PageController {

    List<Recipe> results;

    SearchController searchController = new SearchController();
    UserController userController = new UserController();


    /**
     * The function tells the search page to be displayed at route predefined
     * in the configuration application.properties
     *
     * @return String
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHomePage(Model model) {
        if(userController.isUserLoggedIn()){
             model.addAttribute("user", userController.getCurrentUser());
             model.addAttribute("loggedIn", true);
        }

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
    public String submitSearch(HttpServletRequest request, Model model) {
        String search = request.getParameter("search");
        results = searchController.searchRecipeByName(search);
        for (Recipe recipe: results) {
            System.out.println(recipe.getID());
        }


        model.addAttribute("recipeList", results);
        return "resultpage";
    }

    //Select recipe
    @RequestMapping(value="recipe/{id}/details", method = RequestMethod.GET)
    public String selectRecipe (@PathVariable int id, Model model) {
        //1. use id to get recipe object
        Recipe selected = searchController.getRecipebyID(id);
        model.addAttribute("recipe", selected);
        return "recipe";
    }

    //Load log-in
    @RequestMapping(value="login", method = RequestMethod.GET)
    public String displayLoginPage () {return "login";}

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (userController.isLoginCorrect(username, password)) {
           //If login successful set the current user
            userController.setCurrentUser(username, password);
            //Have to do this twice at the moment until i can
            //think of something more clever
            model.addAttribute("user", userController.getCurrentUser());
            model.addAttribute("loggedIn", true);
            return "homepage";

        } else {
            model.addAttribute("loginError", true);

        }
        return "login";
    }


}

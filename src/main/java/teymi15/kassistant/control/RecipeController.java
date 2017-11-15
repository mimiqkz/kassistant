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
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.model.User;
import teymi15.kassistant.service.*;

import teymi15.kassistant.model.Recipe;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    PhotoServiceImp photoService;

    /**
     * The function returns a string with the route which should be rendered. This
     * is initiated when the user submits his/her input. The input from the user
     * should then be displayed on the result page.
     * @param request getting request from the page
     * @param model model
     * @return String
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String submitSearch(HttpServletRequest request, HttpSession session, Model model) {
        String search = request.getParameter("search");
        String option = request.getParameter("select-option");
        if(option.equals("recipe")) {
            List<Recipe> recipes = RecipeService.getMatchingRecipe(search);
            model.addAttribute("resultsList", recipes);
            model.addAttribute("isRecipe", true);
        } else {
            List<Ingredient> ingredients = IngredientService.getMatchingIngredient(search);
            model.addAttribute("resultsList", ingredients);
            model.addAttribute("numOfResults", ingredients.size());

        }
        displayLoggedInUser(session, model);
        model.addAttribute("searchvalue", search);

        return "resultpage";
    }


    /**
     * The method displays the recipe form. It allows user to fill in the form
     * in order to create a new recipe
     * @param session session
     * @param model model
     * @return String
     */
    @RequestMapping(value = "/create-recipe", method = RequestMethod.GET)
    public String displayRecipeForm(HttpSession session, Model model) {
        List <Ingredient> ingredients = IngredientService.getAllIngredient();
        model.addAttribute("ingredients", ingredients);
        displayLoggedInUser(session, model);
        if(session.isNew() || session.getAttribute("user") == null){
            return "homepage";
        }else {
            return "createRecipe";

        }
    }

    /**
     * The method allows user to submit the edited/created recipe to the database.
     * @param session session
     * @param request getting the request from the page
     * @param model model
     * @return String
     */
    @RequestMapping(value = "/create-recipe", method = RequestMethod.POST)
    public String createRecipe(HttpSession session, HttpServletRequest request, Model model,@RequestParam("file") MultipartFile file,
                               RedirectAttributes redirectAttributes) throws IOException, InterruptedException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] instructions = request.getParameterValues("instruction[]");
        String[] ingredients = request.getParameterValues("ingredient[]");
        User user = (User)session.getAttribute("user");
        byte [] pic = null;
        if(!file.isEmpty()){
            pic = file.getBytes();
        }
        Recipe recipe = RecipeService.createRecipe(name, description, instructions, ingredients, pic, user);
        displayRecipe(session, model, recipe);
        displayLoggedInUser(session, model);
        return "recipe";
    }



    /**
     * The function returns a string with the route which should be rendered. This
     *  is initiated when the user selects a link that represents a Recipe. This Recipe
     *  should then be displayed on the recipe page.
     * @param id int
     * @param model model
     * @return String
     */
    @RequestMapping(value="recipe/{id}")
    public String selectRecipe (@PathVariable int id, HttpSession session, Model model) {
        Recipe selected = RecipeService.getRecipeById(id);
        displayRecipe(session, model, selected);
        displayLoggedInUser(session, model);

        return "recipe";
    }

    /**
     * a function that sends user and recipe to the service class so thay
     * will be linked to gether in userLikedRecipes
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value="/saverecipe", method = RequestMethod.GET)
    public String saveRecipe (HttpSession session, Model model){
        RecipeService.likeRecipe((User) session.getAttribute("user"),
                    (Recipe) session.getAttribute("recipe"));

        int i = ((Recipe) session.getAttribute("recipe")).getId();
        Recipe recipe = RecipeService.getRecipeById(i);

        displayLoggedInUser(session, model);
        displayRecipe(session, model, recipe);
        return "recipe";
    }

    /**
     * a function that unlikes the recipe for the user
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value="/unsaverecipe", method = RequestMethod.GET)
    public String unsaveRecipe (HttpSession session, Model model){
        RecipeService.unlikeRecipe((User) session.getAttribute("user"),
                (Recipe) session.getAttribute("recipe"));

        int i = ((Recipe) session.getAttribute("recipe")).getId();
        Recipe recipe = RecipeService.getRecipeById(i);
        displayLoggedInUser(session, model);
        displayRecipe(session, model, recipe);
        return "recipe";
    }


    /**
     * a function that askes the servic class to delet an recipe
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value="delete-recipe", method = RequestMethod.GET)
    public String deleteRecipe (HttpSession session, Model model) {
        RecipeService.deleteRecipe((Recipe)session.getAttribute("recipe"));
        displayLoggedInUser(session, model);
        return "homepage";
    }

    /**
     * a function that displays the eddit recipe page
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value="edit-recipe", method = RequestMethod.GET)
    public String displayEditForm (HttpSession session, Model model) {
        displayRecipe(session, model, (Recipe)session.getAttribute("recipe"));
        displayLoggedInUser(session, model);
        return "edit-recipe";
    }

    /**
     * a function that gets information from the user and
     * sends to service so the recipe can be editit
     * @param session
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="edit-recipe", method = RequestMethod.POST)
    public String editRecipe (HttpSession session, Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] instructions = request.getParameterValues("instruction[]");
        String[] ingredients = request.getParameterValues("ingredient[]");
        User user = (User)session.getAttribute("user");
        Recipe recipe = RecipeService.editRecipe((Recipe)session.getAttribute("recipe"), name, description, instructions, ingredients);

        displayRecipe(session, model, recipe);
        displayLoggedInUser(session, model);
        return "recipe";
    }

    /**
     *
     * @param session
     * @param model
     * @param recipe
     */
    public void displayRecipe(HttpSession session, Model model, Recipe recipe) {
        session.setAttribute("recipe", recipe);
        model.addAttribute("recipe", recipe);

        //Instructions variable is stored as one string, use this regex to split it up
        String[] instructions = recipe.getInstruction().split("[!][!]");

        model.addAttribute("instructions", instructions);

        if(!session.isNew() && session.getAttribute("user")!=null) {
            // Check if author is the same as the one logged in
            User currentUser = (User)session.getAttribute("user");
            //Check if user has liked recipe
            if(userServiceImp.hasLikedRecipe(currentUser, recipe)) {
                model.addAttribute("liked", true);
            }
            //Check if user is viewing own recipe
            if(currentUser.getUsername().equals(recipe.getUserCreator().getUsername())) {
                model.addAttribute("sameUser", true);
            }
        }
    }

    /**
     * The method allows to display the logged in user so the user knows that
     * he/she is already being logged in
     * @param session session
     * @param model model
     */
    public void displayLoggedInUser(HttpSession session, Model model) {
        if(!session.isNew()) {
            if(!(session.getAttribute("user") == null)) {
                model.addAttribute("user", session.getAttribute("user"));
                model.addAttribute("loggedIn", true);
            }

        }
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/alive", method = RequestMethod.GET)
    public String alive(Model model) {
        Recipe a = new Recipe();
        model.addAttribute("recipe", a);
        if(RecipeService.isAlive())
            return "homepage";
        else
            return "errorpage";
    }
}

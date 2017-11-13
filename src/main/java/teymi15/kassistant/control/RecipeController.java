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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.model.User;
import teymi15.kassistant.service.IngredientServiceImp;
import teymi15.kassistant.service.PhotoServiceImp;
import teymi15.kassistant.service.RecipeServiceImp;

import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.service.UserServiceImp;

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
        return "createRecipe";
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
                               RedirectAttributes redirectAttributes) throws IOException {
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
    @RequestMapping(value="recipe/{id}", method = RequestMethod.GET)
    public String selectRecipe (@PathVariable int id, HttpSession session, Model model) {
        Recipe selected = RecipeService.getRecipeById(id);
        session.setAttribute("recipe",selected);
        displayRecipe(session, model, selected);
        displayLoggedInUser(session, model);


        return "recipe";
    }

    @RequestMapping(value="/saverecipe", method = RequestMethod.GET)
    public String saveRecipe (HttpSession session, Model model){
        Recipe selected = (Recipe) session.getAttribute("recipe");

        displayRecipe(session, model, selected);
        displayLoggedInUser(session, model);


        return "recipe";
    }
    @RequestMapping(value = "/saverecipe", method = RequestMethod.POST)
    public String savePost(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        Recipe selected = (Recipe) session.getAttribute("recipe");
        System.out.println("------------------------");
        System.out.println(user.getUsername());
        System.out.println("------------------------");
        if(!session.isNew()) {
            RecipeService.likeRecipe(user, selected);
        }
        return "recipe";
    }


    @RequestMapping(value="delete-recipe", method = RequestMethod.GET)
    public String deleteRecipe (HttpSession session, Model model) {
        Recipe recipe = (Recipe)session.getAttribute("recipe");
        RecipeService.deleteRecipe(recipe);
        return "homepage";
    }

    @RequestMapping(value="edit-recipe", method = RequestMethod.GET)
    public String displayEditForm (HttpSession session, Model model) {
        displayRecipe(session, model, (Recipe)session.getAttribute("recipe"));
        displayLoggedInUser(session, model);
        return "edit-recipe";
    }

    @RequestMapping(value="edit-recipe", method = RequestMethod.POST)
    public String editRecipe (HttpSession session, Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        System.out.println("Edited name " + name);
        String description = request.getParameter("description");
        String[] instructions = request.getParameterValues("instruction[]");
        String[] ingredients = request.getParameterValues("ingredient[]");
        User user = (User)session.getAttribute("user");
        Recipe recipe = RecipeService.editRecipe((Recipe)session.getAttribute("recipe"), name, description, instructions, ingredients);

        displayRecipe(session, model, recipe);
        displayLoggedInUser(session, model);
        return "recipe";
    }


    public void displayRecipe(HttpSession session, Model model, Recipe recipe) {
        session.setAttribute("recipe", recipe);
        model.addAttribute("recipe", recipe);
        model.addAttribute("author", recipe.getUserCreator());

        //Split instructions into substeps
        String[] instructions = recipe.getInstruction().split("[!][!]");
        for(int i = 0 ; i <instructions.length; i ++ ) System.out.println(instructions[i]);

        model.addAttribute("instructions", instructions);
        //model.addAttribute("liked", false);

        if(!session.isNew() && session.getAttribute("user")!=null) {
            // Check if author is the same as the one logged in
            User currentUser = (User)session.getAttribute("user");

            // Need to do this by id, but id's are all over the place right now
            System.out.println("1. " + currentUser.getUsername());
            System.out.println("2. " + recipe.getName());
            System.out.println("3. " + recipe.getUserCreator().getUsername());
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

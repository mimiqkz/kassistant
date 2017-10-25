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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teymi15.kassistant.model.Ingredient;
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
        displayLoggedInUser(session, model);
        if(option.equals("recipe")) {
            List<Recipe> recipes = RecipeService.getMatchingRecipe(search);
            model.addAttribute("resultsList", recipes);
            model.addAttribute("isRecipe", true);
        } else {
            List<Ingredient> ingredients = IngredientService.getMatchingIngredient(search);
            model.addAttribute("resultsList", ingredients);
        }


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
        model.addAttribute("ingredients",ingredients);
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
    public String submitRecipe(HttpSession session, HttpServletRequest request, Model model,@RequestParam("file") MultipartFile file,
                               RedirectAttributes redirectAttributes) throws IOException {
        String name = request.getParameter("name");
        String[] instructions = request.getParameterValues("instruction[]");
        String instruction = "";
        //will implement as seperate function
        //Inserting numbers into string to know where to separate e.g. "1. "
        for(int i = 0; i < instructions.length; i++) {
            if(i == instructions.length-1) {
                instruction+= instructions[i];
                break;
            }
            instruction += (instructions[i] + "!!");
        }

        String[] ingredientNames = request.getParameterValues("ingredient[]");

        Recipe recipe = new Recipe(name,instruction);
        List<Ingredient> ingredients = IngredientService.getAllMatchingIngredients(ingredientNames);
        for (Ingredient i: ingredients
             ) {
            recipe.addIngredients(i);
        }
        byte [] bytes = null;
        if(!file.isEmpty()){
            bytes = file.getBytes();
        }

        String pic = photoService.addPhoto(bytes);
        System.out.println(pic);
        recipe.setPhotoURL(pic);
        String s = session.getAttribute("user").toString();
        recipe.setUserCreator(userServiceImp.getUserByName(s));
        RecipeService.addRecipe(recipe);
        displayLoggedInUser(session, model); 

        return "homepage";
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
        model.addAttribute("author", selected.getUserCreator());
        // Check if author is the same as the one logged in
        if(session.getAttribute("user").equals(selected.getUserCreator().getUsername())) {
            model.addAttribute("sameUser", true);
        }

        //Splice instructions, delete first and last entry - not most efficient way right now
        String[] instructions = selected.getInstruction().split("[!][!]");

        model.addAttribute("instructions", instructions);
        return "recipe";
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

}

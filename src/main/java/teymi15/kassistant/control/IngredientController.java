package teymi15.kassistant.control;
/**
 * The program allows user to search for recipe with the matching name.
 * For example, "Apple pie", "Brocoli soup", "Chocolate Cake" , etc.
 *
 * @author Nu Phan Quynh Do
 * @author  Alexander Freyr Sveinsson
 * @author Alexandra Mjöll Young
 * @version 1.3
 * @since   2017-09-20
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.model.User;
import teymi15.kassistant.service.IngredientServiceImp;
import teymi15.kassistant.service.PhotoServiceImp;
import teymi15.kassistant.service.UserServiceImp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The controller which sees over the ingredients
 */
@Controller
public class IngredientController {
    @Autowired
    IngredientServiceImp ingredientService;
    @Autowired
    PhotoServiceImp photoService;
    @Autowired
    UserServiceImp userService;

    /**
     * The method displays the recipe form. It allows user to fill in the form
     * in order to create a new recipe
     * @param session session
     * @param model model
     * @return String
     */
    @RequestMapping(value = "/create-ingredient", method = RequestMethod.GET)
    public String displayIngredientForm(HttpSession session, Model model) {
        displayLoggedInUser(session, model);
        return "createIngredient";
    }

    /**
     * The function returns a string with the route which should be rendered. This
     *  is initiated when the user selects a link that represents a Recipe. This Recipe
     *  should then be displayed on the recipe page.
     * @param id int
     * @param model model
     * @return String
     */
    @RequestMapping(value="ingredient/{id}", method = RequestMethod.GET)
    public String selectIngredient (@PathVariable int id, HttpSession session, Model model) {
        Ingredient selected = ingredientService.getIngredientById(id);
        model.addAttribute("ingredient", selected);
        session.setAttribute("ingredient",selected);
        displayLoggedInUser(session, model);
        return "ingredient";
    }

    /**
     * The method allows user to submit the edited/created recipe to the database.
     * @param session session
     * @param request getting the request from the page
     * @param model model
     * @return String
     */
    @RequestMapping(value = "/create-ingredient", method = RequestMethod.POST)
    public String submitIngredient(HttpSession session, HttpServletRequest request, Model model, @RequestParam("file") MultipartFile file,
                               RedirectAttributes redirectAttributes) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        byte [] bytes = null;
        if(!file.isEmpty()){
            bytes = file.getBytes();
        }
        Ingredient ingredient = ingredientService.createIngredient(name, description, bytes);
        session.setAttribute("ingredient", ingredient);
        model.addAttribute("ingredient",ingredient);
        displayLoggedInUser(session, model);
        return "update-price";
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
            }else {
                model.addAttribute("loggedIn", false);
            }
        }
    }

    /**
     * The method displays the recipe form. It allows user to fill in the form
     * in order to create a new recipe
     * @param session session
     * @param model model
     * @return String
     */
    @RequestMapping(value = "/edit-ingredient", method = RequestMethod.GET)
    public String displayIngredientEditForm(HttpSession session, Model model) {
        displayLoggedInUser(session, model);
        Ingredient ingredient = (Ingredient) session.getAttribute("ingredient");
        model.addAttribute("ingredient",ingredient);
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        return "update-price";
    }

    /**
     * The method allows user to submit the edited/created recipe to the database.
     * @param session session
     * @param request getting the request from the page
     * @return String
     */
    @RequestMapping(value = "/edit-ingredient", method = RequestMethod.POST)
    public String submitPriceUpdate(HttpSession session, HttpServletRequest request,Model model) {
        String store = request.getParameter("store");
        String location = request.getParameter("location");
        int price = Integer.parseInt((request.getParameter("price")));
        Ingredient ingredient = ingredientService.updatePrice((Ingredient) session.getAttribute("ingredient"),
                store, location, price);
        displayLoggedInUser(session, model);
        model.addAttribute("ingredient", ingredient);
        return "ingredient";
    }



}
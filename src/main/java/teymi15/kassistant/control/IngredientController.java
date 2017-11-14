package teymi15.kassistant.control;

import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.service.IngredientServiceImp;
import teymi15.kassistant.service.PhotoServiceImp;
import teymi15.kassistant.service.UserServiceImp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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

        Ingredient ingredient = new Ingredient(name, description);

        byte [] bytes = null;
        if(!file.isEmpty()){
            bytes = file.getBytes();
        }

        String pic = photoService.addPhoto(bytes);
        ingredient.setPhotoURL(pic);
        String s = session.getAttribute("user").toString();
        ingredientService.addIngredient(ingredient);
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
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("loggedIn", true);
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
        Ingredient ingredient = (Ingredient) session.getAttribute("selectIngredient");
        model.addAttribute("ingredient",ingredient);
        return "editIngredient";
    }

    /**
     * The method allows user to submit the edited/created recipe to the database.
     * @param session session
     * @param request getting the request from the page
     * @return String
     */
    @RequestMapping(value = "/edit-ingredient", method = RequestMethod.POST)
    public String submitIngredientEdit(HttpSession session, HttpServletRequest request,Model model) {

        int price = Integer.parseInt((request.getParameter("price")));
        String location = request.getParameter("location");
        String store = request.getParameter("store");
        // public Ingredient(double price, String name, String location, String store, Set recipes) {
        Ingredient ingredient = (Ingredient) session.getAttribute("selectIngredient");
        ingredient.setPrice(price);
        ingredient.setLocation(location);
        ingredient.setStore(store);
        boolean b = ingredientService.addIngredient(ingredient);
        displayLoggedInUser(session, model);
        System.out.println(b);
        return "homepage";
    }


}
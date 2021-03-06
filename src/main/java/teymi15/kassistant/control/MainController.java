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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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


    /**
     * The function tells the search page to be displayed at route predefined
     * in the configuration application.properties
     * @return String
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHomePage(HttpSession session, Model model) {
        System.out.println("displayHomePage called");
        displayLoggedInUser(session, model);
        return "homepage";
    }



    /**
     * The function returns a gets the user in the current section and adds the
     * User attribute to the current page
     * @param session httpSession
     * @param model model
     */
    public void displayLoggedInUser(HttpSession session, Model model) {
        System.out.println("displayLoggedInUser called");
        if(!session.isNew()) {
            if(!(session.getAttribute("user") == null)) {
                model.addAttribute("user", session.getAttribute("user"));
                model.addAttribute("loggedIn", true);
            }else {
                model.addAttribute("loggedIn", false);
            }
        }else {
            model.addAttribute("loggedIn", false);
        }
    }

}


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

    List<Recipe> results = new ArrayList();


    SearchController searchController;


    /**
     * The function tells the search page to be displayed at route predefined
     * in the configuration application.properties
     *
     * @return String
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displaySearchPage() {
        return "searchpage";
    }


    /**
     * The function returns a string with the route which should be rendered. This
     *  is initiated when the user submits his/her input. The input from the user
     *  should then be displayed on the result page.
     * @param request getting request from the page
     * @param model model
     * @return String
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submitSearch(HttpServletRequest request, Model model) {


/*
        in.add(new Ingredient(1,65, "chickpeas", "Grandi", "Bónus"));
        in.add(new Ingredient(2,65, "Olive oil", "Grandi", "Bónus"));
        recipes[0] = new Recipe(1,"hummus","take chickpeas and cruz them and add oliv oil",in);
        res.add(new Recipe(1,"hummus","take chickpeas and cruz them and add oliv oil",in));
        in.clear();

        in.add(new Ingredient(1,65, "chickpeas", "Grandi", "Bónus"));
        in.add(new Ingredient(3,65, "Pasta", "Grandi", "Bónus"));
        res.add(new Recipe(1,"pasta with bean","just pasta bro",in));
        recipes[1] = new Recipe(1,"pasta with bean","just pasta bro",in);
        in.clear();
        in.add(new Ingredient(4,65, "vegitables", "Grandi", "Bónus"));
        in.add(new Ingredient(5,65, "tofu", "Grandi", "Bónus"));
        recipes[2] = new Recipe(1,"stirfry","just stir fry",in);
        res.add(new Recipe(1,"stirfry","just stir fry",in));


/*
        for(int i= 0; i<res.size(); i++){
            System.out.println(res.get(i).getName());
        }
*/
        searchController = new SearchController();

        String search = request.getParameter("search");
        results = searchController.searchRecipeByName(search);

        model.addAttribute("recipeList", results);
        return "resultpage";
    }
}

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {
    /**
     * The function tells the search page to be displayed at route predefined
     * in the configuration application.properties
     * @param
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
     * @param request
     * @param model
     * @return String
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submitSearch(HttpServletRequest request, Model model) {

        String search = request.getParameter("search");

        model.addAttribute("search", search);
        return "resultpage";
    }
}

package teymi15.kassistant.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String searchPage() {
        return "searchpage";
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String displaySearchResults(HttpServletRequest request, Model model) {

        String search = request.getParameter("search");

        model.addAttribute("search", search);
        return "resultpage";
    }
}

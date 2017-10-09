package teymi15.kassistant.control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.repository.IngredientRepository;

import java.util.List;
import java.util.Set;

@Controller
public class IngredientControler {
    /**
     * GET /create  --> Create a new user and save it in the database.
     */
    @RequestMapping("/create-ingredient")
    @ResponseBody
    public String create(double price, String name, String location, String store,Set recipes) {
        return "resultpage";
    }
}


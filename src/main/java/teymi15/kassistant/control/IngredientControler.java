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
        String userId = "";
        try {
            Ingredient ingredient = new Ingredient(price, name,location,store,recipes);
            ingredientRepository.save(ingredient);
            userId = String.valueOf(ingredient.getID());
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created with id = " + userId;
    }

    /**
     * GET /delete  --> Delete the user having the passed id.
     */
    @RequestMapping("/delete-ingredient")
    @ResponseBody
    public String delete(int id) {
        try {
            Ingredient user = new Ingredient(id);
            ingredientRepository.delete(user);
        }
        catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * GET /get-by-email  --> Return the id for the user having the passed
     * name.
     */
    @RequestMapping("/get-ingredient-by-name")
    @ResponseBody
    public String getByEmail(String name) {
        String userId = "";
        try {
            Ingredient ingredient = ingredientRepository.findByName(name);
            userId = String.valueOf(ingredient.getID());
        }
        catch (Exception ex) {
            return "Ingredient not found";
        }
        return "The ingredient id is: " + userId;
    }

    /**
     * GET /update  --> Update the ingredient list and the name for the user in the
     * database having the passed id.
     */
    @RequestMapping("/update-ingredient")
    @ResponseBody
    public String updateUser(long id, String location, String name) {
        try {
            Ingredient ingredient = ingredientRepository.findOne(id);
            ingredient.setLocation(location);
            ingredient.setName(name);
            ingredientRepository.save(ingredient);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }

    // Private fields

    @Autowired
    private IngredientRepository ingredientRepository;

}


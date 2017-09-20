
package teymi15.kassistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.repository.RecipeRepository;

import java.util.List;

/**
 *
 * @author Alexander Freyr Sveinsson
 * @date september 2017
 * HBV501G Hugbúnaðarverkefni 1
 * Háskóli Íslands
 */
@Service
public class RecipeServiceImp implements RecipeService{

    // Tenging yfir í safn af kennurum
    @Autowired
    RecipeRepository recipeRep;

    @Override
    public boolean isNameCorrect(String nafn) {
        return true;
    }

    @Override
    public void addRecipe(Recipe k) {
        recipeRep.add(k);
    }

    @Override
    public List<Recipe> allirKennarar() {
        return recipeRep.getAll();
    }

}

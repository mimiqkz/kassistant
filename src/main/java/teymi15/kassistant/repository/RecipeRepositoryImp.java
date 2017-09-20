package teymi15.kassistant.repository;
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
import org.springframework.stereotype.Repository;
import teymi15.kassistant.model.Recipe;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeRepositoryImp implements RecipeRepository {
    private final List<Recipe> recipes;

    public RecipeRepositoryImp() {
        this.recipes = new ArrayList<Recipe>();
    }

    @Override
    public List<Recipe> getAll() {
        return recipes;
    }

    @Override
    public void add (Recipe recipe) {
        recipes.add(recipe);
    }
}

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
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.model.Recipe;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeRepositoryImp implements RecipeRepository {
    //the List of recipes
    private final List<Recipe> recipes;

    public RecipeRepositoryImp() {
        this.recipes = new ArrayList<Recipe>();
    }

    // get all the recipes in the repository
    @Override
    public List<Recipe> getAll() {
        return recipes;
    }

    @Override
    public void addAll(){
        recipes.clear();
        List<Ingredient> in = new ArrayList<>();
        in.add(new Ingredient(1,65, "chickpeas", "Grandi", "Bónus"));
        in.add(new Ingredient(2,65, "Olive oil", "Grandi", "Bónus"));
        recipes.add(new Recipe(1,"hummus","take chickpeas and cruz them and add oliv oil",in));
        in.clear();
        in.add(new Ingredient(1,65, "chickpeas", "Grandi", "Bónus"));
        in.add(new Ingredient(3,65, "Pasta", "Grandi", "Bónus"));
        recipes.add(new Recipe(1,"pasta with bean","just pasta bro",in));
        in.clear();
        in.add(new Ingredient(4,65, "vegitables", "Grandi", "Bónus"));
        in.add(new Ingredient(5,65, "tofu", "Grandi", "Bónus"));
        recipes.add(new Recipe(1,"stirfry","just stir fry",in));

    }

    //add data to the repository
    @Override
    public void add (Recipe recipe) {
        recipes.add(recipe);
    }
}

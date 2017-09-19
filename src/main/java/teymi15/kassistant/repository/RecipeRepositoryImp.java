package teymi15.kassistant.repository;

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

package teymi15.kassistant.repository;


import teymi15.kassistant.model.Recipe;

import java.util.List;

public interface RecipeRepository {

    List <Recipe> getAll();

    void add (Recipe recipe);

}

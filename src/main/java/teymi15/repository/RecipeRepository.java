package teymi15.repository;


import teymi15.model.Recipe;

import java.util.List;

public interface RecipeRepository {

    List <Recipe> getAll();

    void add (Recipe recipe);

}

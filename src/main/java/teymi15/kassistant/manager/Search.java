package teymi15.kassistant.manager;

import java.util.ArrayList;
import java.util.List;

import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.model.Recipe;

import teymi15.kassistant.repository.*;

public class Search {
    private RecipeRepositoryImp repo = new RecipeRepositoryImp();

    public List<Recipe> getRecipes() {
        return recipes;
    }

    private List<Recipe> recipes = repo.getAll();

    private ArrayList ingredients = new ArrayList();




    public List searchRecipeByName(String name){
        List found = new ArrayList();
        recipes.add(new Recipe(1,"some", "abc",new ArrayList<>()));
        for(Object x : recipes){
            if(x.getClass().getClass().getName().equals(name)) {
                found.add(x);
            }
        }
        return found;
    }

    public List searchRecipeByCuisine(String cuisine){
        List found = new ArrayList();
        /*for(Object x : recipes){
            if(x.getClass().equals(cuisine)) {
                found.add(x);
            }
        }*/
        return found;
    }
    public List searchRecipeByPrimaryIngredient(String ingredient) {
        List found = new ArrayList();
        for(Object x : recipes){
           if(x.getClass().getName().equals(ingredient));
        }
        return found;
    }
    public List searchRecipeByType(String type){
        ArrayList found = new ArrayList();

        return found;
    }
    public List searchhAll(String search){
        List a = searchRecipeByName(search);
        List b = searchRecipeByCuisine(search);
        List c = searchRecipeByPrimaryIngredient(search);
        List d = searchRecipeByType(search);
        for(Object x : b){
            if(a.contains(x)) a.add(x);
        }
        for(Object x : c){
            if(a.contains(x)) a.add(x);
        }
        for(Object x : d){
            if(a.contains(x)) a.add(x);
        }
        return a;
    }
}

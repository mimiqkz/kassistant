package teymi15.kassistant.service;

import java.util.ArrayList;
import java.util.List;

import teymi15.kassistant.model.Recipe;

import teymi15.kassistant.repository.*;

public class Search {
    private RecipeRepositoryImp repo = new RecipeRepositoryImp();
    private List<Recipe> recipes = repo.getAll();
    private ArrayList ingredients = new ArrayList();




    public ArrayList searchRecipeByName(String name){
        ArrayList found = new ArrayList();
        for(Object x : recipes){
            if(x.getClass().getName().equals(name)) {
                found.add(x);
            }
        }
        return found;
    }
    public ArrayList searchRecipeByCuisine(String cuisine){
        ArrayList found = new ArrayList();
        /*for(Object x : recipes){
            if(x.getClass().equals(cuisine)) {
                found.add(x);
            }
        }*/
        return found;
    }
    public ArrayList searchRecipeByPrimaryIngredient(String ingredient) {
        ArrayList found = new ArrayList();
        for(Object x : recipes){
           // List<Ingredient> in = x.getIngredients();
        }
        return found;
    }
    public ArrayList searchRecipeByType(String type){
        ArrayList found = new ArrayList();

        return found;
    }
    public ArrayList searchhAll(String search){
        ArrayList a = searchRecipeByName(search);
        ArrayList b = searchRecipeByCuisine(search);
        ArrayList c = searchRecipeByPrimaryIngredient(search);
        ArrayList d = searchRecipeByType(search);
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

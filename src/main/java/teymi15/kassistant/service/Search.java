package teymi15.kassistant.service;

import java.util.ArrayList;

public class Search {
    public ArrayList getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList recipes) {
        this.recipes = recipes;
    }

    public ArrayList getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList ingredients) {
        this.ingredients = ingredients;
    }

    private ArrayList recipes = new ArrayList();
    private ArrayList ingredients = new ArrayList();

    public ArrayList searchRecipeByName(String name){
        ArrayList found = new ArrayList();
        for(Object x : recipes){
            if(x.equals(name)) found.add(x);
        }
        return found;
    }
    public ArrayList searchRecipeByCuisine(String Cuisine){
        ArrayList found = new ArrayList();

        return found;
    }
    public ArrayList searchRecipeByPrimaryIngredient(String ingredient) {
        ArrayList found = new ArrayList();

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

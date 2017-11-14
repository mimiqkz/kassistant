
package teymi15.kassistant.service;
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
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.model.User;
import teymi15.kassistant.repository.RecipeRepository;
import teymi15.kassistant.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecipeServiceImp implements RecipeService {

    @Autowired
    RecipeRepository recipeRep; // Connection to the recipe repository

    @Autowired
    IngredientServiceImp ingredientService;

    @Autowired
    PhotoServiceImp photoService;

    @Autowired
    UserRepository userRepository;

    @Override
    @ResponseBody
    public boolean addRecipe(Recipe k) {
        try{
            recipeRep.save(k);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @ResponseBody
    public List<Recipe> getMatchingRecipe(String name) {
        return recipeRep.findAllByNameLikeIgnoreCase("%" +name+ "%");
    }

    @Override
    @ResponseBody
    public Recipe getRecipeById(int id) {
        return recipeRep.findById(id);
    }

    @Override
    @ResponseBody
    public boolean isAlive() {
        return true;
    }
    @Override
    @ResponseBody
    public Recipe createRecipe(String name, String description, String[] instructions, String[] ingredients, byte[] bytes, User user) {
        Recipe recipe = new Recipe(name, mergeInstructions(instructions));
        try {
            //Create object, merge instruction array into string
            //Add ingredients if they match those in the database
            List<Ingredient> ingredientList = ingredientService.getAllMatchingIngredients(ingredients);
            recipe.setDescription(description);
            String pic = "";
            try {
                pic = photoService.addPhoto(bytes);
            }catch (Exception e){
                e.printStackTrace();
            }

            recipe.setPhotoURL(pic);
            recipe.setUserCreator(user);
            try {
                recipe = recipeRep.save(recipe);
            }catch (Exception e){
                e.printStackTrace();
            }
            for (Ingredient i: ingredientList) {
                i.addRecipe(recipe);
                ingredientService.addIngredient(i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return recipe;
    }

    @Override
    @ResponseBody
    public Recipe editRecipe(Recipe recipe, String name, String description, String[] instructions, String[] ingredients) {
        recipe.setName(name);
        recipe.setDescription(description);
        Set<Ingredient> ingredientsList = new HashSet<Ingredient>(ingredientService.getAllMatchingIngredients(ingredients));
        recipe.setIngredients(ingredientsList);
        recipe.setDescription(description);
        recipe.setInstruction(mergeInstructions(instructions));
        recipeRep.save(recipe);
        return recipe;
    }

    @Override
    @ResponseBody
    public void deleteRecipe(Recipe recipe) {
        /**int id = recipe.getId();
        System.out.println("_________Delete test __________");
        Set<Ingredient> ingredients = recipe.getIngredients();
        for (Ingredient in: ingredients) {
            System.out.println(in.getName());
            List<Ingredient> thisIngredient =  ingredientService.getMatchingIngredient(in.getName());
            for (Ingredient tin: thisIngredient) {
                tin.removeRecipe(recipe);
                ingredientService.addIngredient(tin);
            }
        }

        /**List<Ingredient> ingredients = ingredientService.getMatchingIngredient("");
        for (Ingredient in:ingredients) {
            System.out.println(in.getName());
            Set<Recipe> recipes = in.getRecipes();
            for (Recipe re:recipes) {
                System.out.println(re.getName());
            }
        }**/
        try{
            recipeRep.deleteById(recipe.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
       /** if (!(recipeRep.findByName(recipe.getName()).getName().isEmpty())) managedRecipe = recipe;
        else managedRecipe = recipeRep.save(recipe);
        managedRecipe.getIngredients().remove(managedRecipe);
        **/

    }
    @Override
    @ResponseBody
    public String mergeInstructions(String[] instructions){
        String instruction = "";
        for(int i = 0; i < instructions.length; i++) {
            if(i == instructions.length-1) {
                instruction+= instructions[i];
                break;
            }
            instruction += (instructions[i] + "!!");
        }
        return instruction;
    }

    @Override
    @ResponseBody
    public String[] splitInstructions(String instruction) {
        return new String[0];
    }

    @Override
    @ResponseBody
    public boolean likeRecipe(User user, Recipe recipe) {
        if(!user.getLikedRecipes().contains(recipe)){
            user.addLikedRecipes(recipe);
        }

        try{
        userRepository.save(user);
        return  true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    @ResponseBody
    public boolean unlikeRecipe(User user, Recipe recipe){
        if(user.getLikedRecipes().remove(recipe)){
            System.out.println("eyddi útt úr");
        }
        Set<Recipe> recipes = user.getLikedRecipes();
        //user.getLikedRecipes().remove(this);
        System.out.println("-----------------");
        System.out.println("----liked-recipe---");
        for (Recipe r: recipes){
            if(r.getName().equals(recipe.getName())){
                if(recipes.remove(r)){
                    System.out.println("eyðir útt");
                }
            }
            System.out.println(r.getName());
        }
        user.setLikedRecipes(recipes);
        try{
            userRepository.save(user);
            System.out.println("unlike");
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  false;
    }

}

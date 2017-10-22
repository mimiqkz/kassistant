package teymi15.kassistant.service;

import org.codehaus.groovy.runtime.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ArrayUtils;
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.repository.IngredientRepository;

import java.util.List;
@Service
public class IngredientServiceImp implements IngredientService{

    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public void addRecipe(Ingredient k) {

    }

    @Override
    public List<Ingredient> getMatchingIngredient(String name) {
        return ingredientRepository.findAllByNameLikeIgnoreCase("%" +name+ "%");
    }

    @Override
    public Ingredient getIngredientById(int id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public void addAllData() {

    }

    @Override
    public List<Ingredient> getAllIngredient() {

        return ingredientRepository.findAll();
    }

    @Override
    public List<Ingredient> getAllMacingIngredients(String[] names) {
        List<Ingredient> maching = getAllIngredient();
        for(int i = 0; i<maching.size();i++){
            Ingredient ingredient = maching.get(i);
            for(int j = 0;j<names.length;j++){
                if(ingredient.getName().equals(names[j])){

                }else{
                    maching.remove(ingredient);
                }
            }
        }
        return maching;
    }
}

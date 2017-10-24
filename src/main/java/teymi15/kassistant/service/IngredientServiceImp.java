package teymi15.kassistant.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ArrayUtils;
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.repository.IngredientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class IngredientServiceImp implements IngredientService{

    @Autowired
    IngredientRepository ingredientRepository;



    @Override
    public List<Ingredient> getMatchingIngredient(String name) {
        return ingredientRepository.findAllByNameLikeIgnoreCase("%" +name+ "%");
    }

    @Override
    public Ingredient getIngredientById(int id) {
        return ingredientRepository.findById(id);
    }



    @Override
    public List<Ingredient> getAllIngredient() {

        return ingredientRepository.findAll();
    }

    @Override
    public List<Ingredient> getAllMacingIngredients(String[] names) {
        List<Ingredient> matched = new ArrayList<>();
        for(int i = 0; i<names.length;i++){
            List<Ingredient> mach = getMatchingIngredient(names[i]);
            for(Ingredient in : mach){
                matched.add(in);
            }
        }
        return matched;
    }

    @Override
    public boolean addIngredient(Ingredient ingredient) {

        try{
            ingredientRepository.save(ingredient);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}

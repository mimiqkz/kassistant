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

    @Autowired
    PhotoServiceImp photoService;



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
    public List<Ingredient> getAllMatchingIngredients(String[] names) {

        List<Ingredient> matched = new ArrayList<>();
        for(int i = 0; i<names.length;i++){
            List<Ingredient> match = getMatchingIngredient(names[i]);
            for(Ingredient in : match){
                matched.add(in);
            }
        }
        return matched;
    }

    @Override
    public Ingredient createIngredient(String name, String description, byte[] bytes) {
        String pic = photoService.addPhoto(bytes);
        Ingredient ingredient = new Ingredient(name, description);
        ingredient.setPhotoURL(pic);
        addIngredient(ingredient);
        return ingredient;

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

    @Override
    public Ingredient updatePrice(Ingredient ingredient, String store, String location, int price) {
        ingredient.setPrice(price);
        ingredient.setLocation(location);
        ingredient.setStore(store);
        addIngredient(ingredient);
        return ingredient;
    }


}

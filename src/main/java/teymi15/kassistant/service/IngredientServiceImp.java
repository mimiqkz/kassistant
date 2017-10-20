package teymi15.kassistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return null;
    }

    @Override
    public void addAllData() {

    }

    @Override
    public List<Ingredient> getAllIngredient() {
        return null;
    }
}

package teymi15.kassistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teymi15.kassistant.model.Ingredient;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {

    List<Ingredient> findAll();

    Ingredient save(Ingredient ingredient);
}

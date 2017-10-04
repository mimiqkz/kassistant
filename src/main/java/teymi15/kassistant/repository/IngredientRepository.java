package teymi15.kassistant.repository;

import org.springframework.data.repository.CrudRepository;
import teymi15.kassistant.model.Ingredient;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
public interface IngredientRepository extends CrudRepository<Ingredient,Long> {

    List<Ingredient> findAll();

    List<Ingredient> findByName(String name);

    Ingredient save(Ingredient ingredient);
}

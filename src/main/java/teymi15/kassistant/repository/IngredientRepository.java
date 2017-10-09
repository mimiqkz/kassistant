package teymi15.kassistant.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import teymi15.kassistant.model.Ingredient;


import javax.transaction.Transactional;

import java.util.List;


@Transactional
public interface IngredientRepository extends CrudRepository<Ingredient,Long> {

    List<Ingredient> findAll();

    List<Ingredient> findAllByNameLikeIgnoreCase(String name);

    Ingredient save(Ingredient ingredient);
}

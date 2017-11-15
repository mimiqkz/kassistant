package teymi15.kassistant.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import teymi15.kassistant.model.Ingredient;


import javax.transaction.Transactional;

import java.util.List;


@Transactional
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    /**
     *
     * @return
     */
    List<Ingredient> findAll();

    /**
     *
     * @param name
     * @return
     */
    List<Ingredient> findAllByNameLikeIgnoreCase(String name);

    /**
     *
     * @param ingredient
     * @return
     */
    Ingredient save(Ingredient ingredient);

    /**
     *
     * @param id
     * @return
     */
    Ingredient findById(int id);

    //Ingredient insert(Ingredient ingredient);
    
}

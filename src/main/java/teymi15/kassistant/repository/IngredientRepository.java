package teymi15.kassistant.repository;
/**
 * The program allows user to search for recipe with the matching name.
 * For example, "Apple pie", "Brocoli soup", "Chocolate Cake" , etc.
 *
 * @author Nu Phan Quynh Do
 * @author  Alexander Freyr Sveinsson
 * @author Alexandra Mjöll Young
 * @version 1.2
 * @since   2017-09-20
 */
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import teymi15.kassistant.model.Ingredient;


import javax.transaction.Transactional;

import java.util.List;


@Transactional
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    /**
     * Find all the ingredients
     * @return List of the ingredients
     */
    List<Ingredient> findAll();

    /**
     * Find all the ingredients which matched the search name
     * @param name
     * @return List of the ingredients which match the search
     */
    List<Ingredient> findAllByNameLikeIgnoreCase(String name);

    /**
     * Save the ingredients
     * @param ingredient
     * @return
     */
    Ingredient save(Ingredient ingredient);

    /**
     * Find the ingredient by id
     * @param id of the ingredient
     * @return The ingredient
     */
    Ingredient findById(int id);
    
}

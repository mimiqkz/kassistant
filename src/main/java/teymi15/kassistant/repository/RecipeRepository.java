package teymi15.kassistant.repository;
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

import org.springframework.data.repository.CrudRepository;
import teymi15.kassistant.model.Recipe;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The repository for recipe
 */
@Transactional
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    /**
     * Get the list of all recipe
     * @return List of all recipe
     */
    List <Recipe> findAll();

    /**
     * add to the recipe list
     * @param recipe recipe
     */
    Recipe save (Recipe recipe);

    /**
     * Return the list of recipe matched the searched string
     * @param name name of the recipe
     * @return list of the recipe
     */
    List<Recipe> findAllByNameLikeIgnoreCase(String name);

    /**
     * Find the id of the recipe
     * @param id
     * @return the recipe
     */
    Recipe findById(int id);

    /**
     * Find recipe by name
     * @param name
     * @return the recipe
     */
    Recipe findByName(String name);

    /**
     * Delete the recipe by id
     * @param id
     */
    @Transactional
    void deleteById(int id);

}

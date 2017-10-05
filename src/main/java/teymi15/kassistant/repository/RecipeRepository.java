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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import teymi15.kassistant.model.Recipe;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The repository for recipe
 */
@Transactional
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    /**
     * get the list of all recipe
     * @return List of all recipe
     */
    List <Recipe> findAll();

    /**
     * add to the recipe list
     * @param recipe
     */
    Recipe save (Recipe recipe);

    /*@Query("SELECT '*' FROM recipe WHERE lower(name) LIKE lower(:name)")*/
    List<Recipe> findAllByName(@Param("name") String name);


}

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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.model.User;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {


    /**
     * get the list of all Users
     * @return List of all Users
     */
    List<User> findAll();

    /**
     * add to the recipe list
     * @param user
     */
    User save(User user);

    /**
     * Find the user by name
     * @param name
     * @return The user
     */
    User findByName(String name);

    /**
     * Find the username by name
     * @param name
     * @return list of the user
     */
    List<User> findByUsername(String name);

}

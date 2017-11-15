package teymi15.kassistant.repository;

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
     *
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     *
     * @param name
     * @return
     */
    List<User> findByUsername(String name);

}

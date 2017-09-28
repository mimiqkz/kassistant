package teymi15.kassistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teymi15.kassistant.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


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
}

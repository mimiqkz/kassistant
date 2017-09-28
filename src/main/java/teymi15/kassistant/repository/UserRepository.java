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

import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.model.User;

import java.util.List;

/**
 * The repository for recipe
 */
public interface UserRepository {

    /**
     * get the list of all users
     * @return List of all users
     */
    List <User> getAll();

    /**
     * add to the user list
     * @param user user
     */
    void add(User user);

    /**
     * add all the dummy data to the repository
     */
    public void addAll();

}

package teymi15.kassistant.service;
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

import org.springframework.stereotype.Service;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.model.User;

import java.util.List;

/**
 * The service class for the user controller, getting data from the repository
 */
@Service
public interface UserService {


    /**
     * add user
     * @param  username
     * @param name
     * @param password
     * @param confirm
     * @return User
     */
    User addUser(String name, String username, String password, String confirm) throws Exception;

    /**
     * Returns true if user is in repository, false if not
     *
     * @param username String, password String
     */
    boolean isUserInDatabase(String username, String password);

    /**
     * Returns User based on username and password
     *
     * @param username String, password String
     */
    User getUser(String username, String password);

    /**
     * deletes the user
     * @param id
     * @return boolean on the success
     */
    Boolean delete(int id);

    /**
     * gets the user by it's name
     * @param name
     * @return User if he was found
     */
    User getUserByName(String name);

    /**
     * updates the user
     * @param user
     * @return true if success else false
     */
    boolean updateUser(User user);

    /**
     * checks if username already exists, returns true or false
     * @param username
     * @return true if username exists, else false
     */
    boolean isUsernameFree(String username);

    /**
     * @param recipe
     * @param user
     * @return
     */
    boolean hasLikedRecipe(Recipe recipe,User user);

}

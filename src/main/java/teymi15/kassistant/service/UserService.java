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
     * @param  password
     * @param username
     * @param email
     * @param name
     * @param age
     * @return void
     */
    boolean addUser(String username, String password, String email, String name, int age);

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
     * @return User if he whas found
     */
    User getUserByName(String name);

    /**
     * updates the user
     * @param id
     * @param age
     * @param userName
     * @param name
     * @param password
     * @return true if success else false
     */
    boolean updateUser(Long id,int age, String userName, String name,String password);



}

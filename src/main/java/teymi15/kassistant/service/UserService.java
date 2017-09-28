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

import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.model.User;

import java.util.List;

/**
 * The service class for the user controller, getting data from the repository
 */
public interface UserService {


    /**
     * Add the user
     *
     * @param newUser User
     */
    public void addUser(User newUser);

    public User findUser(String username, String password);




}

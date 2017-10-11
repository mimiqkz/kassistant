package teymi15.kassistant.control;
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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.model.User;
import teymi15.kassistant.service.UserService;
import teymi15.kassistant.service.UserServiceImp;

/**
 * The class manages user data
 */

public class UserController {

    @Autowired
    UserServiceImp userService;

    private User currentUser;
    private Boolean loggedIn = false;


    public void registerUser(User newUser){ userService.addUser(newUser);}

    /**
     * checks if the login is correct
     * @param username
     * @param password
     * @return boolean
     */
    public Boolean isLoginCorrect(String username, String password){
        if(userService.isUserInDatabase(username, password)){return true;}
        return false;
    }

    /**
     * sets the current user if he is
     * int the database
     * @param username
     * @param password
     */
    public void setCurrentUser(String username, String password){
        currentUser = userService.getUser(username, password);
        loggedIn = true;
        System.out.println("This should come second. User logged in: " +  loggedIn);
    }

    /**
     * Gets the current user
     * @return currentUser
     */
    public User getCurrentUser(){
        return currentUser;
    }

    /**
     * logs the user out
     * by clering the loggedIn
     * and currentuser variables
     */
    public void signoutUser(){
        loggedIn = false;
        currentUser = null;
    }

    /**
     * @return loggedIn
     */
    public Boolean isUserLoggedIn(){
        return loggedIn;
    }

}
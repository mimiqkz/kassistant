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
import teymi15.kassistant.service.KassistantServiceImp;
import teymi15.kassistant.service.UserService;
import teymi15.kassistant.service.UserServiceImp;

/**
 * The class manages user data
 */

public class UserController {
/*
    @Autowired
    private UserService userService;

    private User currentUser;

    private Boolean loggedIn = false;

    public void registerUser(User newUser){ userService.addUser(newUser);}

    //Returns true if login exists, else, false
    public Boolean isLoginCorrect(String username, String password){

        if(userService.isUserInDatabase(username, password)){return true;}

        return false;
    }

    //sets current user
    public void setCurrentUser(String username, String password){
        currentUser = userService.getUser(username, password);
        loggedIn = true;
        System.out.println("This should come second. User logged in: " +  loggedIn);

    }

    public User getCurrentUser(){
        return currentUser;
    }

    public void signoutUser(){
        loggedIn = false;
        currentUser = null;
    }

    public Boolean isUserLoggedIn(){
        return loggedIn;
    }

*/
}

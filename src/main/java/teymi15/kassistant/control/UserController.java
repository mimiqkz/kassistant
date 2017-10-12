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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.model.User;
import teymi15.kassistant.service.UserService;
import teymi15.kassistant.service.UserServiceImp;

import javax.servlet.http.HttpServletRequest;

/**
 * The class manages user data
 */
@Controller
public class UserController {

    @Autowired
    UserServiceImp userService;

    private User currentUser;
    private Boolean loggedIn = false;

    @GetMapping("register")
    public String registrationForm(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    /**
     * The function returns a string with the route which should be rendered. This
     *  is initiated when the user submits his/her input. The input from the user
     *  should then be displayed on the result page.
     * @param user User
     * @return String
     */

    @PostMapping("register")
    public String registrationSubmit(@ModelAttribute User user){
        System.out.println("USER ID " + user.getId());
        registerUser(user);
        return "signup";
    }

    /**
     * The function returns a string with the route which should be rendered depending
     * on user input.
     * @param request getting request from the page
     * @param model model
     * @return String
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);

        if (isLoginCorrect(username, password)) {
           //If login successful set the current user
            setCurrentUser(username, password);
            //Have to do this twice at the moment until i can
            //think of something more clever
            model.addAttribute("user", getCurrentUser());
            model.addAttribute("loggedIn", true);
            return "homepage";

        } else {
            model.addAttribute("loginError", true);

        }
        return "login";
    }

    public void registerUser(User user){
        if(userService.addUser(user)){
            setCurrentUser(user.getUsername(),user.getPassword());
        }else{
            System.out.println("error somthing whent wrong");
        }
    }

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
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
    public String registrationSubmit(@ModelAttribute User user, Model model){

        if(userService.addUser(user)){
            setCurrentUser(user);
            displayLoggedInUser(model);
            return "homepage";

        }else{
            System.out.println("Error on registration");
        }
        return "signup";
    }

    @RequestMapping(value = "signout", method = RequestMethod.GET)
    public String signOut(Model model){
        setCurrentUser(null);
        loggedIn = false;
        currentUser = null;
      //  model.addAttribute("user", null);
       // model.addAttribute("loggedIn", false);
        return "homepage";
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


        if (isLoginCorrect(username, password)) {
            User user = userService.getUser(username, password);

            //If login successful set the current user
            setCurrentUser(user);
            //Have to do this twice at the moment until i can
            //think of something more clever
            displayLoggedInUser(model);
            return "homepage";

        } else {
            model.addAttribute("loginError", true);

        }
        return "login";
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
     * @param user User
     */
    public void setCurrentUser(User user){
        currentUser = user;
        loggedIn = true;
    }

    /**
     * Gets the current user
     * @return currentUser
     */
    public User getCurrentUser(){
        return currentUser;
    }



    /**
     * @return loggedIn
     */
    public Boolean isUserLoggedIn(){
        return loggedIn;
    }

    /**
     * Display the user once logged in
     * @param model model
     */
    public void displayLoggedInUser(Model model) {
        if(isUserLoggedIn()){
            model.addAttribute("user", getCurrentUser());
            model.addAttribute("loggedIn", true);
        }
    }
}
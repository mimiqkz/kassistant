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
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import teymi15.kassistant.model.User;
import teymi15.kassistant.service.UserServiceImp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.ws.Response;

/**
 * The class manages user data
 */
@Controller
public class UserController {

    @Autowired
    UserServiceImp userService;

/*
    @PostMapping("register")
    public String registrationSubmit(HttpSession session,@ModelAttribute User user, Model model){

        if(userService.addUser(user)){
            session.setAttribute("user",user.getUsername());
            session.setAttribute("password",user.getPassword());
            model.addAttribute("user", user.getUsername());
            model.addAttribute("loggedIn", true);
            return "homepage";

        }else{
            System.out.println("Error on registration");
        }
        return "signup";
    } */

    @RequestMapping(value = "signout", method = RequestMethod.GET)
    public String signOut(HttpSession session,Model model){
        session.setAttribute("user",null);
        session.setAttribute("password",null);

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
    public String login(HttpSession session, HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (isLoginCorrect(username, password)) {
            User user = userService.getUser(username, password);
            session.setAttribute("user",username);
            session.setAttribute("password", password);

            //If login successful set the current user
            displayLoggedInUser(session, model);

            return "homepage";

        } else {
            model.addAttribute("loginError", true);

        }
        return "login";
    }



    @RequestMapping(value = "sign-up", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity signup(HttpSession session, HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");

        System.out.println("HERE now " + name);
        try {
            userService.addUser(name, username, password, confirm);
            session.setAttribute("user", username);
            session.setAttribute("password", password);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        displayLoggedInUser(session, model);
        return null;
    }


        /*
        if(!what) {

        }
        else {
            model.addAttribute("error-message", "Username already exists");
            throw new java.lang.Error("this is very bad");
        } */

        /*
        if (!userService.isUsernameFree(username)) {
            model.addAttribute("error", true);
            model.addAttribute("displayErrorMessage", "Username already exists");
        }
        */




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

    @RequestMapping(value = "user-profile", method = RequestMethod.GET)
    public String displayUserProfile(HttpSession session, Model model){
        displayLoggedInUser(session, model);
        return "user-profile";
    }


    /**
     * The function returns a gets the user in the current section and adds the
     * User attribute to the current page
     * @param session httpSession
     * @param model model
     * @return void
     */
    public void displayLoggedInUser(HttpSession session, Model model) {
        if(!session.isNew()) {
            if(!(session.getAttribute("user") == null)) {
                model.addAttribute("user", session.getAttribute("user"));
                model.addAttribute("loggedIn", true);

            }

        }
    }

}
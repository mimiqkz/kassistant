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
import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.model.User;
import teymi15.kassistant.service.UserServiceImp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class manages user data
 */
@Controller
public class UserController {

    @Autowired
    UserServiceImp userService;

    /**
     * After signing out, user is redirect to the homepage
     * @param session
     * @param model
     * @return String
     */
    @RequestMapping(value = "signout", method = RequestMethod.GET)
    public String signOut(HttpSession session,Model model){
        System.out.println("Signing out");
        session.setAttribute("user",null);
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
    public String login(HttpSession session, HttpServletRequest request, Model model) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.isUserInDatabase(username, password)) {
            User user = userService.getUser(username, password);
            //If login successful set the current user
            session.setAttribute("user", user);
            displayLoggedInUser(session, model);

            return "homepage";
        } else {
            throw new Exception("Login incorrect");

        }
    }

    /**
     * A function that gets a picture from the user and sends to the
     * userService
     * @param session
     * @param model
     * @param file
     * @return String
     * @throws IOException
     */
    @RequestMapping(value = "upload-user-image")
    public String uploadUserImage (HttpSession session, Model model,
                                   @RequestParam("file") MultipartFile file) throws IOException {
        byte [] pic = null;
        if(!file.isEmpty()){
            pic = file.getBytes();
        }
        User user = (User)session.getAttribute("user");
        userService.updatePhoto(user, pic);
        displayLoggedInUser(session, model);
        return "user-profile";

    }

    /**
     * a function that gets the data from
     * the user and if correct creates a new user
     * @param session
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "sign-up", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity signup(HttpSession session, HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        try {
            User user = userService.addUser(name, username, password, confirm);
            session.setAttribute("user", user);
            displayLoggedInUser(session, model);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return null;
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

    @RequestMapping(value = "user-profile", method = RequestMethod.GET)
    public String displayUserProfile(HttpSession session, Model model){
        displayLoggedInUser(session, model);
        if(session.isNew()){
            return "homepage";
        }else {
            User u = (User) session.getAttribute("user");
            return "user-profile";
        }

    }

    /**
     * The function returns a gets the user in the current section and adds the
     * User attribute to the current page
     * @param session httpSession
     * @param model model
     */
    public void displayLoggedInUser(HttpSession session, Model model) {
        if(!session.isNew()) {
            if(!(session.getAttribute("user") == null)) {
                model.addAttribute("user", session.getAttribute("user"));
                model.addAttribute("loggedIn", true);
            }else {
                model.addAttribute("loggedIn", false);
            }
        }
    }

}
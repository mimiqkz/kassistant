package teymi15.kassistant.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import teymi15.kassistant.model.User;
import teymi15.kassistant.repository.UserRepository;
@Controller
public class UserControler {
        /**
         * GET /create  --> Create a new user and save it in the database.
         */
        @RequestMapping("/create-user")
        @ResponseBody
        public String create(String password, String username, String name, int age) {
            String userId = "";
            try {
                User user = new User(password, username,name,age);
                userRepository.save(user);
                userId = String.valueOf(user.getId());
            }
            catch (Exception ex) {
                return "Error creating the user: " + ex.toString();
            }
            return "User succesfully created with id = " + userId;
        }

        /**
         * GET /delete  --> Delete the user having the passed id.
         */
        @RequestMapping("/delete-user")
        @ResponseBody
        public String delete(int id) {
            try {
                User user = new User(id);
                userRepository.delete(user);
            }
            catch (Exception ex) {
                return "Error deleting the user:" + ex.toString();
            }
            return "User succesfully deleted!";
        }

        /**
         * GET /get-by-email  --> Return the id for the user having the passed
         * email.
         */
        @RequestMapping("/get-user-by-name")
        @ResponseBody
        public String getByEmail(String name) {
            String userId = "";
            try {
                User user = userRepository.findByName(name);
                userId = String.valueOf(user.getId());
            }
            catch (Exception ex) {
                return "User not found";
            }
            return "The user id is: " + userId;
        }

        /**
         * GET /update  --> Update the email and the name for the user in the
         * database having the passed id.
         */
        @RequestMapping("/update-user")
        @ResponseBody
        public String updateUser(Long id,int age, String userName, String name,String password) {
            try {
                User user = userRepository.findOne(id);
                user.setName(name);
                user.setUsername(userName);
                user.setAge(age);
                user.setPassword(password);
                userRepository.save(user);
            }
            catch (Exception ex) {
                return "Error updating the user: " + ex.toString();
            }
            return "User succesfully updated!";
        }

        // Private fields

        @Autowired
        private UserRepository userRepository;

}


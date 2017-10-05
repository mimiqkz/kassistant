package teymi15.kassistant.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import teymi15.kassistant.model.User;
import teymi15.kassistant.repository.UserRepository;
import teymi15.kassistant.service.UserService;

@Controller
public class UserControler {
    // Private fields
    @Autowired
    private UserService userService;

        /**
         * GET /create  --> Create a new user and save it in the database.
         */
        @RequestMapping("/create-user")
        public String create(String password, String username, String name, int age) {
            return userService.addUser(password,username,name,age);
        }

        /**
         * GET /delete  --> Delete the user having the passed id.
         */
        @RequestMapping("/delete-user")
        public String delete(int id) {
            return userService.delete(id);
        }

        /**
         * GET /get-by-email  --> Return the id for the user having the passed
         * email.
         */
        @RequestMapping("/get-user-by-name")
        public String getByEmail(String name) {
            return userService.getUserByName(name);
        }

        /**
         * GET /update  --> Update the email and the name for the user in the
         * database having the passed id.
         */
        @RequestMapping("/update-user")
        public String updateUser(Long id,int age, String userName, String name,String password) {
           return userService.updateUser(id,age,userName,name,password);
        }

        public Boolean isLoginCorrect(String username, String password){

          /*  if(userRepository.findByUsername(username) {
            }
*/

        return false;
        }

}


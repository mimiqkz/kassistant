
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import teymi15.kassistant.model.User;
import teymi15.kassistant.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    // Connection to the recipe repository
    @Autowired
    UserRepository userRep;


    @Override
    @ResponseBody
    public String addUser(String password,String username, String name, int age) {
        String userId = "";
        try {
            User user = new User(password, username,name,age);
            userRep.save(user);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created with id = " + userId;
    }


    @Override
    @ResponseBody
    public boolean isUserInDatabase(String username, String password) {
        List<User> users = userRep.findAll();

        for (int i = 0; i < users.size(); i++) {

            if (username.equals(users.get(i).getUsername()) &&
                    password.equals(users.get(i).getPassword())){ return true; }
        }

        return false;
    }

    @Override
    @ResponseBody
    public User getUser(String username, String password) {
        List<User> users = userRep.findAll();

        for (int i = 0; i < users.size(); i++) {

            if (username.equals(users.get(i).getUsername()) &&
                    password.equals(users.get(i).getPassword())){
                return users.get(i);

                 }
        }

        return null;
    }
    @Override
    @ResponseBody
    public String delete(int id){
        try {
            User user = new User(id);
            userRep.delete(user);
        }
        catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
    }
    @Override
    @ResponseBody
    public String getUserByName(String name){
        String userId = "";
        try {
            User user = userRep.findByName(name);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }
    @Override
    @ResponseBody
    public  String updateUser(Long id,int age, String userName, String name,String password){
        try {
            User user = userRep.findOne(id);
            user.setName(name);
            user.setUsername(userName);
            user.setAge(age);
            user.setPassword(password);
            userRep.save(user);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }
}

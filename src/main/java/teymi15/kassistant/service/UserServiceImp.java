
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
import teymi15.kassistant.model.User;
import teymi15.kassistant.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    // Connection to the recipe repository
    @Autowired
    UserRepository userRep;

    public UserServiceImp(){

        System.out.println("create userserviceimp");

    }
    @Override
    public void addUser(User newUser) { userRep.save(newUser);}


    @Override
    public User findUser(String username, String password) {
         if(userRep.findByUsername(username).size() != 0){
             return userRep.findByUsername(username).iterator().next();
         }else{
             return null;
         }

    }
}

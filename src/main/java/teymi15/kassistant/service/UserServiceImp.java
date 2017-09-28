
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
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.model.User;
import teymi15.kassistant.repository.RecipeRepository;
import teymi15.kassistant.repository.RecipeRepositoryImp;
import teymi15.kassistant.repository.UserRepository;
import teymi15.kassistant.repository.UserRepositoryImp;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService{

    // Connection to the recipe repository
    @Autowired
    UserRepository userRep;

    public UserServiceImp(){
        userRep = new UserRepositoryImp();
        userRep.addAll();
        System.out.println("create userserviceimp");

    }
    @Override
    public void addUser(User newUser) { userRep.add(newUser);}


    @Override
    public Boolean findUser(String username, String password) {
        List<User> users = userRep.getAll();

        for (int i = 0; i < users.size(); i++) {

            if (username.equals(users.get(i).getUsername()) &&
                    password.equals(users.get(i).getPassword())){ return true; }
        }

        return false;
    }
}

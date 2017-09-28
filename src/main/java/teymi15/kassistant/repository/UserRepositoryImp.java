package teymi15.kassistant.repository;
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
import org.springframework.stereotype.Repository;
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImp implements UserRepository {
    //the List of users
    private final List<User> users;

    public UserRepositoryImp() {
        this.users = new ArrayList<User>();
    }

    // get all the users in the repository
    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void addAll(){
        //Add fake data
        users.clear();
        users.add(new User(1, "meatyminx", "easy"));
        users.add(new User(2, "haskoli", "islands" ));
        users.add(new User(3, "foodlover", "1234" ));


    }

}

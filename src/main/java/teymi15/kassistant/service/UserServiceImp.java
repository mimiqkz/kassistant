
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
import teymi15.kassistant.Hashing.BcryptHashing;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.model.User;
import teymi15.kassistant.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService{

    // Connection to the recipe repository
    @Autowired
    UserRepository userRep;


    @Autowired
    PhotoServiceImp photoService;


    @Override
    @ResponseBody
    public User addUser(String name, String username, String password, String confirm) throws Exception {
        //Check first if any fields are empty
        User user = new User();
        if(!(username.isEmpty() || name.isEmpty() || password.isEmpty() || confirm.isEmpty())) {
                if(!password.equals(confirm)) {
                    throw new Exception("Passwords do not match");
                } else if(!isUsernameFree(username)){
                    throw new Exception("Username is already in use");
                } try{
                    user.setName(name);
                    user.setUsername(username);
                    String hashedPassword = BcryptHashing.signup(password);
                    user.setPassword(hashedPassword);
                    userRep.save(user);
                    return user;
                }catch (Exception e){
                    System.out.println("hér " + e.toString());
                } } else {
                throw new Exception("Please fill out all of the fields");
            }
            return user;
    }


    @Override
    @ResponseBody
    public boolean isUserInDatabase(String username, String password) {
        List<User> users = userRep.findAll();
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername()) &&
                    BcryptHashing.match(password,users.get(i).getPassword())){
                Set<Recipe> myRecipe = users.get(i).getMyRecipes();
                return true;
            }
        }

        return false;
    }

    @Override
    @ResponseBody
    public User getUser(String username, String password) {
        List<User> users = userRep.findAll();

        for (int i = 0; i < users.size(); i++) {

            if (username.equals(users.get(i).getUsername()) &&
                    BcryptHashing.match(password,users.get(i).getPassword())){
                return users.get(i);

                 }
        }

        return null;
    }
    @Override
    @ResponseBody
    public Boolean delete(int id){
        try {
            User user = new User(id);
            userRep.delete(user);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    @Override
    @ResponseBody
    public User getUserByName(String name){
        User user = new User();
        try {
            user = userRep.findByName(name);
        }
        catch (Exception ex) {
            return null;
        }
        return user;
    }
    @Override
    @ResponseBody
    public boolean updateUser(User user){
        try {
            userRep.save(user);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    @ResponseBody
    public boolean isUsernameFree(String username){
        return (userRep.findByUsername(username).isEmpty());
    }

    @ResponseBody
    public void updatePhoto(User user, byte[] bytes) {

        String pic = "";
        try {
            pic = photoService.addPhoto(bytes);

        }catch (Exception e){
            e.printStackTrace();
        }

        user.setPhoto(pic);
        try {
            userRep.save(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Boolean hasLikedRecipe(User user, Recipe recipe) {
        for(Recipe r: user.getLikedRecipes()) {
            if(r.getId() == recipe.getId()) return true;
        }
        return false;
    }


}

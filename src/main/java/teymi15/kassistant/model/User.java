package teymi15.kassistant.model;

import teymi15.kassistant.SQLsafety.SQLInjectionSafe;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * user model class
 * makes the user table in the database
 * */
@Entity
@Table(name = "KassistantUser")
public class User {
    /**
     * primary key of user
     * */
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 6, message = "The password must be at least {min} characters or numbers.")
    private @SQLInjectionSafe String password; // user password string that holds the password

    @NotNull
    @Column(unique = true)
    @Size(min = 1, message = "Username cannot be empty")
    private @SQLInjectionSafe String username; // user name e.g. peat12

    @NotNull
    @Size(min = 1, message = "Name cannot be empty")
    private @SQLInjectionSafe String name; // name of the user e.g. pétur pétursson


    private String photo;

    @OneToMany(mappedBy = "userCreator", cascade = CascadeType.ALL)
    private Set<Recipe> myRecipes = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recipe> likedRecipes = new HashSet<>();

    public User(){}

    /**
     * The contructor
     * @param password password
     * @param username username
     * @param name name of the user
     */
    public User(String password, String username, String name){
        this.password = password;
        this.username = username;
        this.name = name;
    }
    public User(int id){
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int uid) {
        this.id = uid;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "username",unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Recipe> getMyRecipes() {
        return myRecipes;
    }

    public void setMyRecipes(Set<Recipe> myRecipes) {
        this.myRecipes = myRecipes;
    }

    public Set<Recipe> getLikedRecipes() {
        return likedRecipes;
    }

    public void setLikedRecipes(Set<Recipe> likedRecipes) {
        this.likedRecipes = likedRecipes;
    }

    public void addLikedRecipes(Recipe recipe){this.likedRecipes.add(recipe);}
    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String image) {
        this.photo = image;
    }
}

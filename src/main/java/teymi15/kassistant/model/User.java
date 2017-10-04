package teymi15.kassistant.model;

import javax.persistence.*;

@Entity
@Table(name = "KassistantUser")
public class User {

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String password;
    private String username;
    private String name;
    private int age;

    public User(){}
    public User(String password, String username, String name, int age){
        this.password = password;
        this.username = username;
        this.name = name;
        this.age = age;
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

    @Column(name = "username")
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

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

package teymi15.kassistant.model;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String password;
    private String username;
    private String name;
    private int age;

    public User(){

    }
    public User(String password, String username, String name, int age){
        this.age = age;
        this.name = name;
        this.password = password;
        this.username = username;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

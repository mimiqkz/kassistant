package teymi15.kassistant.repository;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionToDB {

    public static java.sql.Connection connection(){
        try{
            //finn gagnagrunninn
            Class.forName("org.sqlite.JDBC");
            java.sql.Connection con = DriverManager.getConnection("jdbc:sqlite:kichenApp.sqlite");
            Statement stmt =  con.createStatement();
            //ResultSet rs = stmt.executeQuery("SELECT * FROM Recipes ");
            //skilar tengingu
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

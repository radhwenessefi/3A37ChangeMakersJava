/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package changemakers.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author zaiir
 */
public class MyConnection {
      private final String url = "jdbc:mysql://localhost:3306/atpeace";
     private final String login = "root";
     private final String password = "";
    
 Connection cnx;
    public static MyConnection instance;
    
 public MyConnection(){
        try {
         cnx = DriverManager.getConnection(url, login, password);
            System.out.println("Connexion établie avec succés!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
   }
    public Connection getCnx(){
        return cnx;
    }
    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }

    public PreparedStatement prepareStatement(String requete2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

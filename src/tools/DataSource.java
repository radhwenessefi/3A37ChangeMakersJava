/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author FGH
 */
public class DataSource {
    
    private final String url = "jdbc:mysql://localhost:3306/atpeaceD?useSSL=false";
     

    private final String login = "root";
    private final String password = "";
    
    private Connection cnx;
    
    private static DataSource instance;

    private DataSource() {
        
        try {
            cnx = DriverManager.getConnection(url, login, password);
            System.out.println("Connexion etablie !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
    public static DataSource getInstance(){
        if (instance == null) 
            instance = new DataSource();
        
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
}

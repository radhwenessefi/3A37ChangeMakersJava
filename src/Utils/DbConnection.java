package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static DbConnection instance;
    private Connection con = null;

    private DbConnection()  {

        try { 
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection established!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return con;
    }


        public static DbConnection getInstance(){
    if (instance == null)
        instance = new DbConnection();
    return instance;
    }
}

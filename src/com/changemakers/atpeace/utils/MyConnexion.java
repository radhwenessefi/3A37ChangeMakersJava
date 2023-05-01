/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author gille
 */
public class MyConnexion {
    
     private final String url = "jdbc:mysql://localhost:3306/atpeace";
     private final String login = "root";
     private final String password = "";
     private static MyConnexion instance;
     private Connection con;

    public Connection getCon() {
        return con;
    }
     
     private MyConnexion()
     {
         try{
            con =  DriverManager.getConnection(url,login,password);
             System.out.println("Connection etablie");
         }
         catch(SQLException ex)
         {
            // ex.printStackTrace();
            System.out.println(ex.getMessage());

         }
     }
     
     public static MyConnexion getInstance()
     {
         if(instance == null)
         {
             instance =  new MyConnexion();
         }
         return instance;
     }
}

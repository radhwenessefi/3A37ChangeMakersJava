/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.services;

import com.changemakers.atpeace.entities.Session;
import com.changemakers.atpeace.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gille
 */
public class SessionService {
      private Connection con;

    public SessionService() {
        con = MyConnection.getInstance().getCon();
    }
    
     public void Insert(Session s)
     {
          try {
            String req = "INSERT INTO `session` (`id_user`, `username`, `user_role`) "
                    + "VALUES (?,?, ?)";
             PreparedStatement ps = con.prepareStatement(req);
             ps.setInt(1, s.getId_user());
             ps.setString(2, s.getUsername());
             ps.setString(3, s.getUser_role());
              ps.executeUpdate();
          }catch(SQLException ex) {
            System.out.println("Erreur lors de l'ajout du rendez-vous: " + ex.getMessage());
        }
     }
     
      public Session ConnectUser()
      {
          Session s = new Session();
          try {  
             String req = "SELECT * FROM session";
             PreparedStatement ps = con.prepareStatement(req);
              ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                s.setId_user(rs.getInt("id_user"));
                s.setUsername(rs.getString("username"));
                s.setUser_role(rs.getString("user_role"));
            }
          } catch(SQLException ex) {
            System.out.println("Erreur lors de l'ajout du rendez-vous: " + ex.getMessage());
        }
          return s;
      }
      
      public void Delete()
      {
           try {  
             String req = "delete FROM session";
             PreparedStatement ps = con.prepareStatement(req);
              ps.executeUpdate();
           } catch(SQLException ex) {
            System.out.println("Erreur lors de l'ajout du rendez-vous: " + ex.getMessage());
        }
      }
}

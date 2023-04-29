/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.services;


import com.changemakers.atpeace.entites.Sport;
import com.changemakers.atpeace.utils.MyConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FGH
 */
public class ServiceSport implements IService<Sport>{
    
    private Connection cnx;

    public ServiceSport() {
        cnx = MyConnexion.getInstance().getCnx();
    }

    @Override
    public void insertOne(Sport t) throws SQLException{
        /*
        String req = "INSERT INTO `regime`(`nom`, `prenom`, `age`) "
                + "VALUES ('"+t.getTitle()+"','"+t.getDiscription()+"', "+t.getLevel()+")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);    
        System.out.println("regime ajouté !");
*/
    }
    
    public void insertOne1(Sport t) throws SQLException{
String req = "INSERT INTO `sport`(`titre`, `discription`, `niveaux`, `image`, `level`) VALUES (?,?,?,?,?)";
        
PreparedStatement ps = cnx.prepareStatement(req);    
ps.setString(1, t.getTitre());
ps.setString(2, t.getDiscription());
ps.setString(3, t.getNiveaux());
ps.setString(4, t.getImage());
ps.setString(5, t.getLevel());
ps.executeUpdate(); 

System.out.println("regime ajouté !");

    }

    @Override
   public void updateOne(Sport t) throws SQLException {
    String req = "UPDATE `sport` SET `titre`=?, `discription`=?, `niveaux`=?, `image`=?, `level`=? WHERE `id`=?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setString(1, t.getTitre());
    ps.setString(2, t.getDiscription());
    ps.setString(3, t.getNiveaux());
    ps.setString(4, t.getImage());
    ps.setString(5, t.getLevel());
    ps.setInt(6, t.getId());
    ps.executeUpdate();
    System.out.println("Sport updated successfully!");
}

    @Override
    public void deleteOne(Sport t) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
   public void deleteOne(int id) throws SQLException {
    String req = "DELETE FROM `sport` WHERE `id`=?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setInt(1, id);
    int rowsDeleted = ps.executeUpdate();
    if (rowsDeleted == 0) {
        System.out.println("No rows were deleted for ID " + id);
    } else {
        System.out.println(rowsDeleted + " rows were deleted for ID " + id);
    }
}


    @Override
    public List<Sport> selectAll() throws SQLException {
        List<Sport> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `sport`";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Sport p = new Sport();

            p.setId(rs.getInt(1));
            p.setTitre(rs.getString(2));
            p.setDiscription(rs.getString(3));
            p.setNiveaux(rs.getString(4));
            p.setImage(rs.getString(5));
            p.setLevel(rs.getString(6));
            temp.add(p);
        }

        
        return temp;
        
    }
    
   @Override
public List<Sport> selectByLevel(String level) throws SQLException {
    List<Sport> temp = new ArrayList<>();
    
    String req = "SELECT * FROM `sport` WHERE `level` = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setString(1, level);
    
    ResultSet rs = ps.executeQuery();
    
    while (rs.next()) {
        Sport p = new Sport();
        
        p.setId(rs.getInt(1));
            p.setTitre(rs.getString(2));
            p.setDiscription(rs.getString(3));
            p.setNiveaux(rs.getString(4));
            p.setImage(rs.getString(5));
            p.setLevel(rs.getString(6));
            temp.add(p);
    }
    
    return temp;
}

    @Override
    public List<Sport> selectByIdRegime(int idregime) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.services;


import com.changemakers.atpeace.entites.Regime;
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
public class ServiceRegime implements IService<Regime>{
    
    private Connection con;

    public ServiceRegime() {
        con = MyConnexion.getInstance().getCon();
    }

    @Override
    public void insertOne(Regime t) throws SQLException{
        /*
        String req = "INSERT INTO `regime`(`nom`, `prenom`, `age`) "
                + "VALUES ('"+t.getTitle()+"','"+t.getDiscription()+"', "+t.getLevel()+")";
        Statement st = con.createStatement();
        st.executeUpdate(req);    
        System.out.println("regime ajouté !");
*/
    }
    
    public void insertOne1(Regime t) throws SQLException{
String req = "INSERT INTO `regime`(`titre`, `discription`, `liste_alement`, `image`, `level`) VALUES (?,?,?,?,?)";
        
PreparedStatement ps = con.prepareStatement(req);    
ps.setString(1, t.getTitle());
ps.setString(2, t.getDiscription());
ps.setString(3, t.getListe_alement());
ps.setString(4, t.getImage());
ps.setString(5, t.getLevel());
ps.executeUpdate(); 

System.out.println("regime ajouté !");

    }

    @Override
   public void updateOne(Regime t) throws SQLException {
    String req = "UPDATE `regime` SET `titre`=?, `discription`=?, `liste_alement`=?, `image`=?, `level`=? WHERE `id`=?";
    PreparedStatement ps = con.prepareStatement(req);
    ps.setString(1, t.getTitle());
    ps.setString(2, t.getDiscription());
    ps.setString(3, t.getListe_alement());
    ps.setString(4, t.getImage());
    ps.setString(5, t.getLevel());
    ps.setInt(6, t.getId());
    ps.executeUpdate();
    System.out.println("Regime updated successfully!");
}

    @Override
    public void deleteOne(Regime t) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
   public void deleteOne(int id) throws SQLException {
    String req = "DELETE FROM `regime` WHERE `id`=?";
    PreparedStatement ps = con.prepareStatement(req);
    ps.setInt(1, id);
    int rowsDeleted = ps.executeUpdate();
    if (rowsDeleted == 0) {
        System.out.println("No rows were deleted for ID " + id);
    } else {
        System.out.println(rowsDeleted + " rows were deleted for ID " + id);
    }
}


    @Override
    public List<Regime> selectAll() throws SQLException {
        List<Regime> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `regime`";
        Statement st = con.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Regime p = new  Regime();
            
            p.setId(rs.getInt(1));
            p.setTitle(rs.getString(2));
            p.setListe_alement(rs.getString(4));
            p.setDiscription(rs.getString(3));
            p.setImage(rs.getString(5));
            p.setLevel(rs.getString(6));
                        
            temp.add(p);
        }
        
        
        return temp;
        
    }
     @Override
public List<Regime> selectByLevel(String level) throws SQLException {
    List<Regime> temp = new ArrayList<>();
    
    String req = "SELECT * FROM `regime` WHERE `level` = ?";
    PreparedStatement ps = con.prepareStatement(req);
    ps.setString(1, level);
    
    ResultSet rs = ps.executeQuery();
    
    while (rs.next()) {
        
        Regime p = new Regime();
        p.setId(rs.getInt(1));
        p.setTitle(rs.getString(2));
        p.setListe_alement(rs.getString(4));
        p.setDiscription(rs.getString(3));
        p.setImage(rs.getString(5));
        p.setLevel(rs.getString(6));        
        temp.add(p);
        
    }
    
    return temp;
}

    @Override
    public List<Regime> selectByIdRegime(int id) throws SQLException {
            List<Regime> temp = new ArrayList<>();
    
    String req = "SELECT * FROM `regime` WHERE `id` = ?";
    PreparedStatement ps = con.prepareStatement(req);
    ps.setInt(1, id);
    
    ResultSet rs = ps.executeQuery();
    
    while (rs.next()) {
        
        Regime p = new Regime();
        p.setId(rs.getInt(1));
        p.setTitle(rs.getString(2));
        p.setListe_alement(rs.getString(4));
        p.setDiscription(rs.getString(3));
        p.setImage(rs.getString(5));
        p.setLevel(rs.getString(6));        
        temp.add(p);
        
    }
    
    return temp;
    }

    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.services;

import com.changemakers.atpeace.entites.Rate;
//import com.changemakers.atpeace.entites.Regime;
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
 * @author DELL
 */
public class ServiceRate implements IService<Rate> {

    private Connection con;

 

    public ServiceRate() {
                con = MyConnexion.getInstance().getCon();

    }

    public void insertOne1(Rate t) throws SQLException {
        String req = "INSERT INTO `rate`(`regime_id`, `rating`, `num_totale`, `nub_of_rate`,`regime_name`) VALUES (?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(req);
        ps.setInt(1, t.getRegime_id());
        ps.setInt(2, t.getRating());
        ps.setInt(3, t.getNum_totale());
        ps.setInt(4, t.getNub_of_rate());
        ps.setString(5, t.getName_regime());
        ps.executeUpdate();

        System.out.println("regime ajouté !");

    }

    @Override
    public void insertOne(Rate t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateOne(Rate t) throws SQLException {
        String req = "UPDATE `rate` SET `regime_id`=?, `rating`=?, `num_totale`=?, `nub_of_rate`=?,`regime_name`=? WHERE `id`=?";
    PreparedStatement ps = con.prepareStatement(req);
    ps.setInt(1, t.getRegime_id());
    ps.setInt(2, t.getRating());
    ps.setInt(3, t.getNum_totale());
    ps.setInt(4, t.getNub_of_rate());
    ps.setString(5, t.getName_regime());
    ps.setInt(6, t.getId());
    ps.executeUpdate();
    System.out.println("Rate updated successfully!");
    }

    @Override
    public void deleteOne(Rate t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteOne(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Rate> selectAll() throws SQLException {
        List<Rate> temp = new ArrayList<>();

        String req = "SELECT * FROM `rate`";
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            Rate p = new Rate();

           p.setId(rs.getInt(1));
            p.setRegime_id(rs.getInt(2));
            p.setRating(rs.getInt(3));
            p.setNum_totale(rs.getInt(4));
            p.setNub_of_rate(rs.getInt(5));
            p.setName_regime(rs.getString(6));

            temp.add(p);
        }

        return temp;
    }

    @Override
    public List<Rate> selectByLevel(String leve) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
         @Override
public List<Rate> selectByIdRegime(int idregime) throws SQLException {
    List<Rate> temp = new ArrayList<>();
    
    String req = "SELECT * FROM `rate` WHERE `regime_id` = ?";
    PreparedStatement ps = con.prepareStatement(req);
    ps.setInt(1, idregime);
    
    ResultSet rs = ps.executeQuery();
    
    while (rs.next()) {
        
          Rate p = new Rate();

            p.setId(rs.getInt(1));
            p.setRegime_id(rs.getInt(2));
            p.setRating(rs.getInt(3));
            p.setNum_totale(rs.getInt(4));
            p.setNub_of_rate(rs.getInt(5));
            p.setName_regime(rs.getString(6));

            temp.add(p);
        
    }
    
    return temp;
}
}

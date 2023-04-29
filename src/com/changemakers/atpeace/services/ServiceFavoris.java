/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.services;

import com.changemakers.atpeace.entites.Favoris;
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
public class ServiceFavoris implements IService<Favoris> {

    private Connection cnx;

    public ServiceFavoris() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    @Override
    public void updateOne(Favoris t) throws SQLException {
        String req = "UPDATE `favoris` SET `regime_id`=?, `nb_favori`=?, `nb_total`=?,`regime_name`=? WHERE `id`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getRegime_id());
        ps.setInt(2, t.getNb_favori());
        ps.setInt(3, t.getNb_total());
         ps.setString(4, t.getRegime_name());
        ps.setInt(5, t.getId());
        ps.executeUpdate();
        System.out.println("Rate updated successfully!");

    }

    @Override
    public void deleteOne(Favoris t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteOne(int id) throws SQLException {
        String req = "DELETE FROM `favoris` WHERE `id`=?";
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
    public List<Favoris> selectAll() throws SQLException {
        List<Favoris> temp = new ArrayList<>();

        String req = "SELECT * FROM `favoris`";
        Statement st = cnx.createStatement();

        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            Favoris p = new Favoris();

            p.setId(rs.getInt(1));
            p.setRegime_id(rs.getInt(2));
            p.setNb_favori(rs.getInt(3));
            p.setNb_total(rs.getInt(4));
               p.setRegime_name(rs.getString(5));

            temp.add(p);
        }

        return temp;
    }

    @Override
    public List<Favoris> selectByLevel(String leve) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Favoris> selectByIdRegime(int idregime) throws SQLException {
        List<Favoris> temp = new ArrayList<>();
 System.out.print("serviceeeeeeee"+idregime);
        String req = "SELECT * FROM `favoris` WHERE `regime_id` = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idregime);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Favoris p = new Favoris();

            p.setId(rs.getInt(1));
            p.setRegime_id(rs.getInt(2));
            p.setNb_favori(rs.getInt(3));
            p.setNb_total(rs.getInt(4));
   temp.add(p);
        }

        return temp;
    }

    @Override
    public void insertOne(Favoris t) throws SQLException {
        String req = "INSERT INTO `favoris`(`regime_id`, `nb_favori`, `nb_total`,`regime_name`) VALUES (?,?,?,?)";

        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getRegime_id());
        ps.setInt(2, t.getNb_favori());
        ps.setInt(3, t.getNb_total());
         ps.setString(4, t.getRegime_name());

        ps.executeUpdate();

        System.out.println(" ajouté on favoris !");
    }

}

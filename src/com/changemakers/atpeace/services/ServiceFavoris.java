/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.services;

import com.changemakers.atpeace.entites.Regime;
import com.changemakers.atpeace.entities.Favoris;
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

    private Connection con;

    public ServiceFavoris() {
        con = MyConnexion.getInstance().getCon();

    }

    @Override
    public void updateOne(Favoris t) throws SQLException {
        String req = "UPDATE `favoris` SET `regime_id`=?, `nb_favori`=?, `nb_total`=? WHERE `id`=?";
        PreparedStatement ps = con.prepareStatement(req);
        ps.setInt(1, t.getRegime_id().getId());
        ps.setInt(2, t.getNb_favori());
        ps.setInt(3, t.getNb_total());
        //  ps.setString(4, t.getRegime_name());
        ps.setInt(4, t.getId());
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
    public List<Favoris> selectAll() throws SQLException {
        List<Favoris> temp = new ArrayList<>();

        String req = "SELECT * FROM `favoris`";
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            Favoris p = new Favoris();

            p.setId(rs.getInt(1));
            //  p.setRegime_id(rs.getInt(2));
            p.setNb_favori(rs.getInt(3));
            p.setNb_total(rs.getInt(4));
            //    p.setRegime_name(rs.getString(5));

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
        System.out.print("serviceeeeeeee" + idregime);
        String req = "SELECT * FROM `favoris` WHERE `regime_id` = ?";
        PreparedStatement ps = con.prepareStatement(req);
        ps.setInt(1, idregime);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Favoris p = new Favoris();

            p.setId(rs.getInt(1));

            p.setNb_favori(rs.getInt(3));
            p.setNb_total(rs.getInt(4));
            temp.add(p);
        }

        return temp;
    }

    @Override
    public void insertOne(Favoris t) throws SQLException {
        String req = "INSERT INTO `favoris`(`regime_id`,`id_patient`, `nb_favori`, `nb_total`) VALUES (?,?,?,?)";
        System.out.println(t.getPatient().getId());
        PreparedStatement ps = con.prepareStatement(req);
        ps.setInt(1, t.getRegime_id().getId());
        ps.setInt(2, t.getPatient().getId());
        ps.setInt(3, t.getNb_favori());
        ps.setInt(4, t.getNb_total());

        ps.executeUpdate();

        System.out.println(" ajouté on favoris !");
    }

    public List<Favoris> ListFav(int id) throws SQLException {
        List<Favoris> temp = new ArrayList<>();
        String req = "SELECT * FROM favoris f JOIN regime r on f.regime_id = r.id where f.id_patient = ?";

        PreparedStatement ps = con.prepareStatement(req);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Favoris p = new Favoris();
            Regime r = new Regime();
            r.setId(rs.getInt(1));
            r.setTitle(rs.getString(2));
            r.setListe_alement(rs.getString(4));
            r.setDiscription(rs.getString(3));
            r.setImage(rs.getString(5));
            r.setLevel(rs.getString(6));
            p.setId(rs.getInt(1));
            p.setRegime_id(r);
            p.setNb_favori(rs.getInt(3));
            p.setNb_total(rs.getInt(4));
            temp.add(p);
        }

        return temp;

    }

}

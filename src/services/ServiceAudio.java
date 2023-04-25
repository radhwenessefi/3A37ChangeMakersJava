/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entites.Audio;
import java.io.FileInputStream;
import java.io.IOException;
import tools.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableColumn;

/**
 *
 * @author dorra
 */
public class ServiceAudio implements Iservice<Audio> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Audio a) throws SQLException {
        String req = "INSERT INTO audio (titre,type,image,fichier) VALUES (?,?,?,?)";

        PreparedStatement st;
        st = cnx.prepareStatement(req);

        st.setString(1, a.getTitre());
        st.setString(2, a.getType());
        st.setString(3, a.getImage());
        st.setString(4, a.getFichier());
     

        st.executeUpdate();

    }

    @Override
    public Audio getOneById(int id) throws SQLException {

        try {

            String req = "SELECT * FROM audio WHERE `Id`=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Audio a = new Audio();
                a.setId(rs.getInt(1));
                a.setTitre(rs.getString("titre"));
                a.setType(rs.getString("type"));
                a.setImage(rs.getString("image"));
                a.setFichier(rs.getString("fichier"));
                a.setRating(rs.getInt("rating"));
                a.setLike(rs.getInt("like"));
                a.setDislike(rs.getInt("dislike"));

                return a;
            }

        } catch (SQLException ex) {
            System.err.println("ex.getMessage()");
        }
        return null;

    }

 @Override
public void modifier(Audio a, int id) throws SQLException {
    try {
       String req = "UPDATE audio SET titre=?, type=?, image=? , fichier=?, rating=?, `like`=?, dislike=? WHERE id=?";


        PreparedStatement st = cnx.prepareStatement(req);

        st.setString(1, a.getTitre());
        st.setString(2, a.getType());
        st.setString(3, a.getImage());
        st.setString(4, a.getFichier());
        st.setInt(5, a.getRating());
        st.setInt(6, a.getLike());
        st.setInt(7, a.getDislike());
        st.setInt(8, id);

        st.executeUpdate();
        System.out.println("Votre audio a été modifié!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

  /*  @Override
    public void modifier(Audio a) throws SQLException {
        String req = "UPDATE audio SET titre`=?, type`=?, image`=?,fichier`=?,rating`=?,like`=?,dislike`=? WHERE `id`=?";
        PreparedStatement st = cnx.prepareStatement(req);
           st.setString(1, a.getTitre());
            st.setString(2, a.getType());
            st.setString(3, a.getImage());
            st.setString(4, a.getFichier());
            st.setInt(5, a.getRating());
            st.setInt(6, a.getLike());
            st.setInt(7, a.getDislike());
        st.setInt(8, a.getId());
        st.executeUpdate();
        System.out.println("Rate updated successfully!");

    }
    
    */
    
    

    @Override
    public void supprimer(int id) throws SQLException {

        try {
            String req = "delete from audio where Id=" + id;
            PreparedStatement st = cnx.prepareStatement(req);
            st.executeUpdate();

            System.out.println("Votre audio a été supprimée aves succés");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Audio> getAll() throws SQLException {
        List<Audio> list = new ArrayList<>();

        String req = "SELECT * FROM audio";

        Statement st = cnx.createStatement();

        ResultSet set = st.executeQuery(req);
        while (set.next()) {
            Audio a = new Audio();

            a.setId(set.getInt(1));
            a.setTitre(set.getString("titre"));
            a.setType(set.getString("type"));
            a.setImage(set.getString("image"));
            a.setFichier(set.getString("fichier"));
            a.setRating(set.getInt("rating"));
            a.setLike(set.getInt("like"));
            a.setDislike(set.getInt("dislike"));

            list.add(a);
        }

        return list;
    }

}


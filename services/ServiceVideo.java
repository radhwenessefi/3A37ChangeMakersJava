/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import entites.Video;
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
public class ServiceVideo implements Iservice<Video> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Video v ) throws SQLException {
        String req = "INSERT INTO video (titre,description,video,image) VALUES (?,?,?,?)";

        PreparedStatement st;
        st = cnx.prepareStatement(req);

        st.setString(1, v.getTitre());
        st.setString(2, v.getDescription());
         st.setString(3, v.getVideo());
        st.setString(4, v.getImage());
     

        st.executeUpdate();

    }

    @Override
    public Video getOneById(int id) throws SQLException {

        try {

            String req = "SELECT * FROM video WHERE `Id`=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Video v  = new Video();
               v.setId(rs.getInt(1));
                v.setTitre(rs.getString("titre"));
               v.setDescription(rs.getString("type"));
               v.setVideo(rs.getString("video"));
                v.setImage(rs.getString("image"));
                
                v.setRating(rs.getInt("rating"));
                v.setLike(rs.getInt("like"));
                v.setDislike(rs.getInt("dislike"));

                return v;
            }

        } catch (SQLException ex) {
            System.err.println("ex.getMessage()");
        }
        return null;

    }

 @Override
public void modifier(Video v , int id) throws SQLException {
    try {
       String req = "UPDATE video SET titre=?, description=? , video=?, image=? , rating=?, `like`=?, dislike=? WHERE id=?";


        PreparedStatement st = cnx.prepareStatement(req);

        st.setString(1, v.getTitre());
        st.setString(2, v.getDescription());
        st.setString(4, v.getVideo());
        st.setString(3, v.getImage());
        
        st.setInt(5, v.getRating());
        st.setInt(6, v.getLike());
        st.setInt(7, v.getDislike());
        st.setInt(8, id);

        st.executeUpdate();
        System.out.println("Votre video a été modifié!");
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
            String req = "delete from video where Id=" + id;
            PreparedStatement st = cnx.prepareStatement(req);
            st.executeUpdate();

            System.out.println("Votre video a été supprimée aves succés");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Video> getAll() throws SQLException {
        List<Video> list = new ArrayList<>();

        String req = "SELECT * FROM video";

        Statement st = cnx.createStatement();

        ResultSet set = st.executeQuery(req);
        while (set.next()) {
            Video v = new Video();

            v.setId(set.getInt(1));
            v.setTitre(set.getString("titre"));
            v.setDescription(set.getString("description"));
             v.setVideo(set.getString("video"));
            v.setImage(set.getString("image"));
           
            v.setRating(set.getInt("rating"));
            v.setLike(set.getInt("like"));
            v.setDislike(set.getInt("dislike"));

            list.add(v);
        }

        return list;
    }

}


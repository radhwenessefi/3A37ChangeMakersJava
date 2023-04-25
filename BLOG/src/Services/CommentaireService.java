/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author khaled
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Commentaire;
import Entities.Poste;
import Utils.DbConnection;

public class CommentaireService {
    private Connection cnx;

    public CommentaireService() {
        cnx = DbConnection.getInstance().getConnection();
    }

    public List<Commentaire> getAllCommentaires() {
        List<Commentaire> commentaires = new ArrayList<>();

        try {
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM commentaire");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Commentaire commentaire = new Commentaire();
                commentaire.setId(rs.getInt("id"));
                commentaire.setContinueCommentaire(rs.getString("continue_commentaire"));
                commentaires.add(commentaire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentaires;
    }

    public Commentaire getCommentaireById(int id) {
        Commentaire commentaire = null;

        try {
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM commentaire WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                commentaire = new Commentaire();
                commentaire.setId(rs.getInt("id"));
                commentaire.setContinueCommentaire(rs.getString("continue_commentaire"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentaire;
    }

    public void addCommentaire(Commentaire commentaire) {
        try {
            PreparedStatement ps = cnx.prepareStatement("INSERT INTO commentaire (continue_commentaire) VALUES (?)");
            ps.setString(1, commentaire.getContinueCommentaire());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCommentaire(Commentaire commentaire) {
        try {
            PreparedStatement ps = cnx.prepareStatement("UPDATE commentaire SET continue_commentaire = ? WHERE id = ?");
            ps.setString(1, commentaire.getContinueCommentaire());
            ps.setInt(2, commentaire.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCommentaire(int id) {
        try {
            PreparedStatement ps = cnx.prepareStatement("DELETE FROM commentaire WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 public List<Commentaire> getByPoste(int postId) {
    List<Commentaire> commentaires = new ArrayList<>();

    try {
        PreparedStatement ps = cnx.prepareStatement("SELECT * FROM commentaire WHERE poste_id = ?");
        ps.setInt(1, postId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Commentaire commentaire = new Commentaire();
            commentaire.setId(rs.getInt("id"));
            commentaire.setContinueCommentaire(rs.getString("continue_commentaire"));
            commentaires.add(commentaire);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return commentaires;
}

    public void addCommentaire(Poste poste) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
}

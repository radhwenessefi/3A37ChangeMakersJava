/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package changemakers.services;

/**
 *
 * @author LENOVO
 */
import changemakers.entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import changemakers.utils.MyConnection;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReclamationCrud implements ReclamationI<Reclamation> {

    private Connection cnx2;

    public ReclamationCrud() {
        MyConnection mycon = MyConnection.getInstance();
        cnx2 = mycon.getCnx();
    }

  
    @Override
    public void ajouterReclamation(Reclamation c) {

        try {
            String req = "INSERT INTO reclamation(titre_reclamation,type_reclamation,etat_reclamation,description_reclamation,date)values(?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(req);

          //  pst.setInt(1, c.getId_reclamation());
            pst.setString(1, c.getTitre_reclamation());
            pst.setString(2, c.getType_reclamation());
            pst.setString(3, c.getEtat_reclamation());
            pst.setString(4, c.getDescription_reclamation());

// Convertir la java.util.Date en java.sql.Date et l'insérer dans la requête préparée
            pst.setDate(5, new java.sql.Date(c.getDate().getTime()));
            System.out.println(c);
            pst.executeUpdate();
            System.out.println("Reclamation ajouté !");
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    //recherche selon id 
    public Reclamation GetOne(int id) {
        try {

            String requete3 = "SELECT * FROM `reclamation` WHERE `id_Reclamation`=" + id;
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setId_reclamation(rs.getInt(1));
                p.setTitre_reclamation(rs.getString("TitreReclamation"));
                p.setType_reclamation(rs.getString("TypeRec"));
                p.setEtat_reclamation(rs.getString("EtatRec"));
                p.setDate(rs.getDate("DateRec"));

                return p;
            }

        } catch (SQLException ex) {
            System.err.println("ex.getMessage()");
        }
        return null;
    }

    public void supprimerReclamation(String titre) {
        try {
            String sql = "DELETE FROM reclamation WHERE titre_reclamation=?";
            PreparedStatement ste = cnx2.prepareStatement(sql);
            ste.setString(1, titre);
            ste.executeUpdate();
            System.out.println("Reclamation a été supprimée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Reclamation> ChercherReclamation(String titre) {
        
        
        List<Reclamation> Reclamations = new ArrayList<>();

        try {
            String sql = "SELECT * FROM reclamation where titre_reclamation=" + titre;
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Reclamation p = new Reclamation();
               p.setId_reclamation(rs.getInt("id_reclamation"));
                p.setTitre_reclamation(rs.getString("titre_reclamation"));
                p.setType_reclamation(rs.getString("type_reclamation"));
                p.setEtat_reclamation(rs.getString("etat_reclamation"));
                p.setDescription_reclamation(rs.getString("description_reclamation"));
                p.setDate(rs.getDate("date"));
                Reclamations.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("------> " + Reclamations.size());
        return Reclamations;
    }

    @Override
    public List<Reclamation> ChercherReclamationByTitel(int idRec) {
        
        
        List<Reclamation> Reclamations = new ArrayList<>();

        try {
            String sql = "SELECT * FROM reclamation where id_reclamation=" + idRec;
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Reclamation p = new Reclamation();
               p.setId_reclamation(rs.getInt("id_reclamation"));
                p.setTitre_reclamation(rs.getString("titre_reclamation"));
                p.setType_reclamation(rs.getString("type_reclamation"));
                p.setEtat_reclamation(rs.getString("etat_reclamation"));
                p.setDescription_reclamation(rs.getString("description_reclamation"));
                p.setDate(rs.getDate("date"));
                Reclamations.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("------> " + Reclamations.size());
        return Reclamations;
    }
    
    // Recupérer tous les réclamations
     @Override
    public List<Reclamation> ChercherReclamation() {
       
        List<Reclamation> Reclamations = new ArrayList<>();

        try {
            String sql = "SELECT * FROM reclamation  ";
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setId_reclamation(rs.getInt("id_reclamation"));
                p.setTitre_reclamation(rs.getString("titre_reclamation"));
                p.setType_reclamation(rs.getString("type_reclamation"));
                p.setEtat_reclamation(rs.getString("etat_reclamation"));
                p.setDescription_reclamation(rs.getString("description_reclamation"));
                p.setDate(rs.getDate("date"));
                Reclamations.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("------> " + Reclamations.size());
        return Reclamations;
    }

 
    public void updateReclamation(Reclamation r) {

        try {
            String sql = "UPDATE Reclamation SET description_reclamation=?,titre_reclamation=? ,type_reclamation=? ,etat_reclamation=?,date=? WHERE id_reclamation=?";
            PreparedStatement ste = cnx2.prepareStatement(sql);

            // Demander à l'utilisateur les nouvelles valeurs
            ste.setString(1, r.getDescription_reclamation());
            ste.setString(2, r.getTitre_reclamation());
            ste.setString(3, r.getType_reclamation());
            ste.setString(4, r.getEtat_reclamation());
            ste.setDate(5, new java.sql.Date(r.getDate().getTime()));
            ste.setInt(6, r.getId_reclamation());
            // Passer les nouvelles valeurs comme paramètres à la requête SQL
            ste.executeUpdate();

            System.out.println("La réclamation a été mise à jour avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour de la réclamation : " + ex.getMessage());
        }
    }

}

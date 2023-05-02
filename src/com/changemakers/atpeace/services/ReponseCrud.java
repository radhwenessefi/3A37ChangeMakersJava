/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.services;

import com.changemakers.atpeace.entities.Reclamation;
import com.changemakers.atpeace.entities.Reponse;
import com.changemakers.atpeace.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author LENOVO
 */
public class ReponseCrud {

    Connection cnx2;

    public ReponseCrud() {

        cnx2 = MyConnection.getInstance().getCon();
    }

   /* public void ajouterReponse(Reponse c) throws SQLException {

        String req = "INSERT INTO reponse(date_reponse,solution_reponse,reclamation_id)values(?,?)";
        PreparedStatement pst = cnx2.prepareStatement(req);
// Convertir la java.util.Date en java.sql.Date et l'insérer dans la requête préparée
        pst.setDate(1, new java.sql.Date(c.getDate_reponse().getTime()));
        pst.setString(2, c.getSolution_reponse());
        pst.setInt(3, c.getReclamation_id());
        pst.executeUpdate();
        System.out.println("Reponse ajouté !");

    }*/

    public void ajouterReponse(Reponse c) throws SQLException {
        String req = "INSERT INTO reponse (date_reponse, solution_reponse, id_reponse,reclamation_id) VALUES (?, ?, ?,?)";
        PreparedStatement pst = cnx2.prepareStatement(req);

        // Convert the java.util.Date to java.sql.Date and insert into the prepared statement
        pst.setDate(1, new java.sql.Date(c.getDate_reponse().getTime()));
        pst.setString(2, c.getSolution_reponse());
        pst.setInt(3, c.getId_reponse());
        pst.setInt(4, c.getReclamation_id());

        pst.executeUpdate();
        System.out.println("Reponse ajoutée !");
    }
    
    

    public void supprimerReponse(int id_reponse) {
        try {
            String sql = "DELETE FROM reponse WHERE id_reponse=?";
            PreparedStatement ste = cnx2.prepareStatement(sql);
            ste.setInt(1, id_reponse);
            ste.executeUpdate();
            System.out.println("Reponse a été supprimée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void updateReponse(Reponse r) {

        try {
            String sql = "UPDATE Reponse SET date_reponse=?,solution_reponse=? WHERE id_reponse=?";
            PreparedStatement ste = cnx2.prepareStatement(sql);

            // Demander à l'utilisateur les nouvelles valeurs
            ste.setDate(1, new java.sql.Date(r.getDate_reponse().getTime()));
            ste.setString(2, r.getSolution_reponse());
            ste.setInt(3,r.getId_reponse());

            // Passer les nouvelles valeurs comme paramètres à la requête SQL
            ste.executeUpdate();

            System.out.println("La réponse a été mise à jour avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour de la reponse : " + ex.getMessage());
        }
    }

    public List<Reponse> ChercherReponse(int idReponse) {

        List<Reponse> Reponses = new ArrayList<>();

        try {
            String sql = "SELECT * FROM reponse where id_reponse=" + idReponse;
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Reponse p = new Reponse();

                p.setId_reponse(rs.getInt("id_reponse"));
                p.setSolution_reponse(rs.getString("titre_reclamation"));
                p.setDate_reponse(rs.getDate("date"));
                Reponses.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("------> " + Reponses.size());
        return Reponses;
    }
    // Recupérer tous les reponses

    public List<Reponse> ChercherReponse() {

        List<Reponse> Reponses = new ArrayList<>();

        try {
            String sql = "SELECT * FROM reponse ";
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Reponse p = new Reponse();
                p.setId_reponse(rs.getInt("id_reponse"));
                p.setSolution_reponse(rs.getString("solution_reponse"));
                p.setDate_reponse(rs.getDate("date_reponse"));
                p.setReclamation_id(rs.getInt("reclamation_id"));
                Reponses.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("------> " + Reponses.size());
        return Reponses;
    }
    public List<Reponse> ChercherReponsePatient(int id_u){
        List<Reponse> Reponses = new ArrayList<>();

        try {
            String sql = "SELECT * from reponse re JOIN reclamation r on r.id = re.reclamation_id JOIN reclamation_patient rp on rp.reclamation_id = r.id WHERE rp.patient_id = " + id_u;
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Reponse p = new Reponse();
                Reclamation r = new Reclamation();
                r.setId_reclamation(rs.getInt("id"));
                r.setTitre_reclamation(rs.getString("TitreReclamation"));
                r.setType_reclamation(rs.getString("TypeRec"));
                r.setEtat_reclamation(rs.getString("EtatRec"));
                r.setDate(rs.getDate("DateRec"));
                
                p.setId_reponse(rs.getInt("re.id_reponse"));
                p.setSolution_reponse(rs.getString("re.titre_reclamation"));
                p.setDate_reponse(rs.getDate("re.date"));
                p.setR(r);
                Reponses.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("------> " + Reponses.size());
        return Reponses;
        
        
    }
    

}

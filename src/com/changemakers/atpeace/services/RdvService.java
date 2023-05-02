/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.services;

import com.changemakers.atpeace.entities.Medecin;
import com.changemakers.atpeace.entities.Patient;
import com.changemakers.atpeace.entities.RendezVous;
import com.changemakers.atpeace.utils.MyConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gille
 */
public class RdvService {
    
   
     private Connection con;

    public RdvService() {
        con = MyConnexion.getInstance().getCon();
    }
    
    
     public void Insert(RendezVous rdv) {
         try {
            String req = "INSERT INTO `rende_vous` (`patient_id`, `medecin_id`, `date`, `etat_rdv`) "
                    + "VALUES (?,?, ?, ?)";
             PreparedStatement ps = con.prepareStatement(req);
            //ps.setInt(1, m.getId());
            ps.setInt(1, rdv.getId_patient());
            ps.setInt(2, rdv.getId_medecin());
            java.util.Date utilDate = rdv.getDateRdv();
java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps.setDate(3,  sqlDate);  
            rdv.setEtat("En attente");
             ps.setString(4,rdv.getEtat());
            ps.executeUpdate();
            System.out.println("Rendez-vous ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout du rendez-vous: " + ex.getMessage());
        }
    }
     
    
    public void Delete(int id) {
         try {
            String req = "DELETE FROM `rende_vous` WHERE `id` = ?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Rendez-vous supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression du rendez-vous: " + ex.getMessage());
        }
    }
    
    
     public void ModifierE(String etat,int id) {
         try {
            String req = "UPDATE rende_vous set etat_rdv = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, etat);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println(" L'etat du rendez-vous modifié avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du rendez-vous: " + ex.getMessage());
        }
    }
     
      public void ModifierD(java.util.Date d,int id) {
         try {
            String req = "UPDATE rende_vous set date = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(req);
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            ps.setDate(1, sqlDate);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println(" La date du rendez-vous modifié avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du rendez-vous: " + ex.getMessage());
        }
    }
      
       public List<RendezVous> ListRdv(int id) throws SQLException {
          List<RendezVous> Lrdv = new ArrayList<>();
        try {
            
             String reqRdv = "SELECT * FROM rende_vous r INNER JOIN patient p ON r.patient_id = p.id WHERE r.medecin_id = ?";
             PreparedStatement psRdv = con.prepareStatement(reqRdv);
             psRdv.setInt(1, id);
             ResultSet rsRdv = psRdv.executeQuery();
        while (rsRdv.next()) {
            Patient patient = new Patient();
            patient.setId(rsRdv.getInt("p.id"));
            patient.setNom(rsRdv.getString("p.nom"));
            patient.setPrenom(rsRdv.getString("p.prenom"));
            patient.setEmail(rsRdv.getString("p.email"));
            patient.setTelephone(rsRdv.getString("p.telephone"));

            RendezVous rdv = new RendezVous();
            rdv.setId(rsRdv.getInt("r.id"));
            rdv.setId_patient(rsRdv.getInt("r.patient_id"));
            rdv.setId_medecin(rsRdv.getInt("r.medecin_id"));
           /* java.sql.Date sqlDate = rsRdv.getDate("r.date");
            java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
            rdv.setDateRdv(utilDate);*/
           rdv.setDateRdv(rsRdv.getDate("r.date"));
            rdv.setEtat(rsRdv.getString("r.etat_rdv"));
            rdv.setPatient(patient);
            
            Lrdv.add(rdv);
        }
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Lrdv;
    }
       
        public RendezVous RdvP(int id) throws SQLException {
         RendezVous Lrdv = new RendezVous();
        try {
            
             String reqRdv = "SELECT * FROM rende_vous r INNER JOIN patient p ON r.patient_id = p.id WHERE r.patient_id = ?";
             PreparedStatement psRdv = con.prepareStatement(reqRdv);
             psRdv.setInt(1, id);
             ResultSet rsRdv = psRdv.executeQuery();
        while (rsRdv.next()) {
          /*  Patient patient = new Patient();
            patient.setId(rsRdv.getInt("p.id"));
            patient.setNom(rsRdv.getString("p.nom"));
            patient.setPrenom(rsRdv.getString("p.prenom"));
            patient.setEmail(rsRdv.getString("p.email"));
            patient.setTelephone(rsRdv.getString("p.telephone"));*/

            RendezVous rdv = new RendezVous();
            rdv.setId(rsRdv.getInt("r.id"));
            rdv.setId_patient(rsRdv.getInt("r.patient_id"));
            rdv.setId_medecin(rsRdv.getInt("r.medecin_id"));        
           rdv.setDateRdv(rsRdv.getDate("r.date"));
            rdv.setEtat(rsRdv.getString("r.etat_rdv"));
           // rdv.setPatient(patient);
            
           // Lrdv.add(rdv);
        }
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Lrdv;
    }
        
        
        public List<RendezVous> RdvT() throws SQLException {
         List<RendezVous> Lrdv = new ArrayList<>();
        try {
            
             String reqRdv = "SELECT * FROM rende_vous r INNER JOIN patient p ON r.patient_id = p.id JOIN medecin m ON r.medecin_id= m.id";
             PreparedStatement psRdv = con.prepareStatement(reqRdv);
          //   psRdv.setInt(1, id);
             ResultSet rsRdv = psRdv.executeQuery();
        while (rsRdv.next()) {
            Patient patient = new Patient();
            patient.setId(rsRdv.getInt("p.id"));
            patient.setNom(rsRdv.getString("p.nom"));
            patient.setPrenom(rsRdv.getString("p.prenom"));
            patient.setEmail(rsRdv.getString("p.email"));
            patient.setTelephone(rsRdv.getString("p.telephone"));
            
            Medecin m = new Medecin();
                m.setId(rsRdv.getInt("m.id"));
                m.setNom(rsRdv.getString("m.nom"));
                m.setPrenom(rsRdv.getString("m.prenom"));
                m.setTelephone(rsRdv.getString("m.telephone"));
                m.setAdresse(rsRdv.getString("m.adresse"));
                m.setEmail(rsRdv.getString("m.email"));
                 m.setDiplome(rsRdv.getString("m.diplome"));
                m.setEtat(rsRdv.getString("m.etat"));
            
            RendezVous rdv = new RendezVous();
            rdv.setId(rsRdv.getInt("r.id"));
            rdv.setId_patient(rsRdv.getInt("r.patient_id"));
            rdv.setId_medecin(rsRdv.getInt("r.medecin_id"));        
           rdv.setDateRdv(rsRdv.getDate("r.date"));
            rdv.setEtat(rsRdv.getString("r.etat_rdv"));
           rdv.setPatient(patient);
           rdv.setMedecin(m);
            
           Lrdv.add(rdv);
        }
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Lrdv;
    }
}

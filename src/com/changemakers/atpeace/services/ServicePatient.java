package com.changemakers.atpeace.services;

import com.changemakers.atpeace.services.MyService;
import com.changemakers.atpeace.entities.Patient;
import com.changemakers.atpeace.entities.RendezVous;
import com.changemakers.atpeace.utils.MyConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServicePatient implements MyService<Patient> {

    private Connection con;

    public ServicePatient() {
        con = MyConnexion.getInstance().getCon();
    }
    
   /* public MyConnexion deconnexion() {
        return MyConnexion.destroy();
    }
*/
    @Override
    public void Insert(Patient p) {

        try {

            String req = "INSERT INTO `patient` (`nom`, `prenom`, `telephone`, `adresse`, `email`, `password`, `roles`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getTelephone());
            ps.setString(4, p.getAdresse());
            ps.setString(5, p.getEmail());
            ps.setString(6, p.getPassword());
            String[] roles = {"ROLE_PATIENT"};
            ps.setString(7, Arrays.toString(roles));
            ps.executeUpdate();
            System.out.println("Patient ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout du patient: " + ex.getMessage());
        }
    }

    public void Update(Patient p) {
        try {
            String req = "UPDATE `patient` SET `nom` = ?, `prenom` = ?, `telephone` = ?, `adresse` = ?, `email` = ?, "
                    + " `roles` = ? WHERE `id` = ?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getTelephone());
            ps.setString(4, p.getAdresse());
            ps.setString(5, p.getEmail());
            String[] role = new String[]{"ROLE_PATIENT"};
            String[] roles = {"ROLE_PATIENT"};
            ps.setString(6, Arrays.toString(roles));
            ps.setInt(7, p.getId());
            ps.executeUpdate();
            System.out.println("Patient mis à jour avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour du patient: " + ex.getMessage());
        }
    }

    @Override
    public void Delete(Patient p) {
        try {
            String req = "DELETE FROM patient WHERE id=?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, p.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        //Delete(p.getId());
    }

    @Override
    public void Delete(String email) {
        try {
            String req = "DELETE FROM `patient` WHERE `email` = ?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, email);
            ps.executeUpdate();
            System.out.println("Patient supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression du patient: " + ex.getMessage());
        }

    }

    @Override
    public List<Patient> Read() {
        List<Patient> patients = new ArrayList<>();
        try {
            String req = "SELECT * FROM patient";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Patient p = new Patient();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setTelephone(rs.getString("telephone"));
                p.setAdresse(rs.getString("adresse"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setRole(rs.getString("roles").split(","));
                /* if (p.getRole().length == 0) {
                  String[]  roles = new String[]{"ROLE_PATIENT"};
                  p.setRole(roles);
                }*/
                //Patient p = new Patient(id, nom, prenom, telephone, adresse, email, password, roles);
                patients.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return patients;
    }

    @Override
    public Patient SignIn(String email, String password) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Patient patient = new Patient();
        try {
            // String req = "SELECT * FROM patient where email = ? and password = ?";
            String req = "SELECT * FROM `patient` where `email` = '" + email + "' and `password` = '" + password + "'";
            PreparedStatement ps = con.prepareStatement(req);
            /* ps.setString(1, email);
            ps.setString(2, password);*/
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                patient.setId(rs.getInt("id"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setEmail(rs.getString("email"));
                patient.setPassword(rs.getString("password"));
                patient.setAdresse(rs.getString("adresse"));
                patient.setTelephone(rs.getString("telephone"));
                patient.setRole(rs.getString("roles").split(","));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return patient;
    }
    
      @Override
    public Patient Verifier(String email) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Patient patient = new Patient();
        try {
            // String req = "SELECT * FROM patient where email = ? and password = ?";
            String req = "SELECT * FROM `patient` where `email` = '" + email + "'";
            PreparedStatement ps = con.prepareStatement(req);
            /* ps.setString(1, email);
            ps.setString(2, password);*/
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                patient.setId(rs.getInt("id"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setEmail(rs.getString("email"));
                patient.setPassword(rs.getString("password"));
                patient.setAdresse(rs.getString("adresse"));
                patient.setTelephone(rs.getString("telephone"));
                patient.setRole(rs.getString("roles").split(","));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return patient;
    }
    
     // @Override
    public Patient VerifierI(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Patient patient = new Patient();
         RendezVous rdv = new RendezVous();
        try {
            String req = "SELECT * FROM patient where  id = '" + id + "'";
           // String req = "SELECT * FROM patient p INNER JOIN rende_vous r ON p.id=r.patient_id   WHERE r.patient_id = '" + id + "'";
            PreparedStatement ps = con.prepareStatement(req);
            /* ps.setString(1, email);
            ps.setString(2, password);*/
          //   PreparedStatement psRdv = con.prepareStatement(req);
             //ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                patient.setId(rs.getInt("id"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setEmail(rs.getString("email"));
                patient.setPassword(rs.getString("password"));
                patient.setAdresse(rs.getString("adresse"));
                patient.setTelephone(rs.getString("telephone"));
                patient.setRole(rs.getString("roles").split(","));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return patient;
    }
    
      public RendezVous VerifierRdv(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //Patient patient = new Patient();
         RendezVous rdv = new RendezVous();
        try {
            // String req = "SELECT * FROM patient where email = ? and password = ?";
            String req = "SELECT * FROM patient p INNER JOIN rende_vous r ON p.id=r.patient_id   WHERE r.patient_id = '" + id + "'";
            PreparedStatement ps = con.prepareStatement(req);
            /* ps.setString(1, email);
            ps.setString(2, password);*/
          //   PreparedStatement psRdv = con.prepareStatement(req);
             //ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {     
            rdv.setId(rs.getInt("r.id"));
            rdv.setId_patient(rs.getInt("r.patient_id"));
            rdv.setId_medecin(rs.getInt("r.medecin_id"));        
           rdv.setDateRdv(rs.getDate("r.date"));
            rdv.setEtat(rs.getString("r.etat_rdv"));
           // patient.setRdv(rdv);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rdv;
    }
}

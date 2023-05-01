/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.services;

import com.changemakers.atpeace.services.RdvService;
import com.changemakers.atpeace.services.MyService;
import com.changemakers.atpeace.entities.Medecin;
import com.changemakers.atpeace.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author gille
 */
public class ServiceMedecin implements MyService<Medecin>{
     private Connection con;

    public ServiceMedecin() {
        con = MyConnexion.getInstance().getCon();
    }

    @Override
    public void Insert(Medecin m) {
         try {

            String req = "INSERT INTO `medecin` (`nom`, `prenom`, `telephone`, `adresse`, `email`, `password`, `roles`,`diplome`,`etat`) "
                    + "VALUES (?,?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement ps = con.prepareStatement(req);
            //ps.setInt(1, m.getId());
            ps.setString(1, m.getNom());
            ps.setString(2, m.getPrenom());
            ps.setString(3, m.getTelephone());
            ps.setString(4, m.getAdresse());
            ps.setString(5, m.getEmail());
            ps.setString(6, m.getPassword());
            String[] roles = {"ROLE_MEDECIN"};
            ps.setString(7, Arrays.toString(roles));
            ps.setString(8, m.getDiplome());
            ps.setString(9, m.getEtat());
            ps.executeUpdate();
            System.out.println("Medecin ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout du medecin: " + ex.getMessage());
        }
    }

    @Override
    public void Update(Medecin m) {
         try {
            String req = "UPDATE `medecin` SET `nom` = ?, `prenom` = ?, `telephone` = ?, `adresse` = ?, `email` = ?, "
                    + "`diplome` = ? WHERE `id` = ?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, m.getNom());
            ps.setString(2, m.getPrenom());
            ps.setString(3, m.getTelephone());
            ps.setString(4, m.getAdresse());
            ps.setString(5, m.getEmail());
            ps.setString(6, m.getDiplome());      
            ps.setInt(7, m.getId());
            ps.executeUpdate();
            System.out.println("Medecin mis à jour avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour du medecin: " + ex.getMessage());
        }
    }

    @Override
    public void Delete(Medecin m) {
         try {
            String req = "DELETE FROM medecin WHERE id=?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, m.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression du Medecin: " + ex.getMessage());
        }
    }

    @Override
    public void Delete(String email) {
         try {
            String req = "DELETE FROM `medecin` WHERE `email` = ?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, email);
            ps.executeUpdate();
            System.out.println("Medecin supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression du Medecin: " + ex.getMessage());
        }
    }

    @Override
    public List<Medecin> Read() {
 List<Medecin> medecin = new ArrayList<>();
        try {
            String req = "SELECT * FROM medecin";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Medecin m = new Medecin();
                m.setId(rs.getInt("id"));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                m.setTelephone(rs.getString("telephone"));
                m.setAdresse(rs.getString("adresse"));
                m.setEmail(rs.getString("email"));
                 m.setDiplome(rs.getString("diplome"));
                m.setEtat(rs.getString("etat"));
                m.setPassword(rs.getString("password"));
                m.setRole(rs.getString("roles").split(","));  
                RdvService rdvs = new RdvService();
                m.setRdv(rdvs.ListRdv(m.getId()));      
               medecin.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return medecin;    
    }

    @Override
    public Medecin SignIn(String email, String password) {
         Medecin medecin = new Medecin();
        try {
            String req = "SELECT * FROM `medecin` where `email` = '" + email + "' and `password` = '" + password + "'";
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                medecin.setId(rs.getInt("id"));
                medecin.setNom(rs.getString("nom"));
                medecin.setPrenom(rs.getString("prenom"));
                medecin.setEmail(rs.getString("email"));
                medecin.setPassword(rs.getString("password"));
                medecin.setAdresse(rs.getString("adresse"));
                medecin.setTelephone(rs.getString("telephone"));
                medecin.setDiplome(rs.getString("diplome"));
                medecin.setEtat(rs.getString("etat"));
                medecin.setRole(rs.getString("roles").split(","));
                /* RdvService rdvs = new RdvService();
                medecin.setRdv(rdvs.ListRdv(medecin.getId()));     */
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return medecin;
    }

    @Override
    public Medecin Verifier(String email) {
         Medecin medecin = new Medecin();
        try {
            String req = "SELECT * FROM medecin where email = '" + email + "'";
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                medecin.setId(rs.getInt("id"));
                medecin.setNom(rs.getString("nom"));
                medecin.setPrenom(rs.getString("prenom"));
                medecin.setEmail(rs.getString("email"));
                medecin.setPassword(rs.getString("password"));
                medecin.setAdresse(rs.getString("adresse"));
                medecin.setTelephone(rs.getString("telephone"));
                medecin.setDiplome(rs.getString("diplome"));
                medecin.setEtat(rs.getString("etat"));
                medecin.setRole(rs.getString("roles").split(","));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return medecin;
    }

     
       public Medecin VerifierD(int id) {
         Medecin medecin = new Medecin();
        try {
            String req = "SELECT * FROM medecin where email = '" + id + "'";
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                medecin.setId(rs.getInt("id"));
                medecin.setNom(rs.getString("nom"));
                medecin.setPrenom(rs.getString("prenom"));
                medecin.setEmail(rs.getString("email"));
                medecin.setPassword(rs.getString("password"));
                medecin.setAdresse(rs.getString("adresse"));
                medecin.setTelephone(rs.getString("telephone"));
                medecin.setDiplome(rs.getString("diplome"));
                medecin.setEtat(rs.getString("etat"));
                medecin.setRole(rs.getString("roles").split(","));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return medecin;
    }
       
       public void ModifierD(String etat,int id)
       {
            try {
            String req = "UPDATE `medecin` SET `etat` = ? WHERE `id` = ?";
            PreparedStatement ps = con.prepareStatement(req);          
            ps.setString(1, etat);          
            ps.setInt(2,id);
            ps.executeUpdate();
            System.out.println("Medecin mis à jour avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour du medecin: " + ex.getMessage());
        }
       }
}

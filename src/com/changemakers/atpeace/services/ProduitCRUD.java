/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.services;
/*

import com.changemakers.atpeace.entities.Produit;
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
import com.changemakers.atpeace.utils.MyConnection;*/

/**
 *
 * @author zaiir
 */
/*public class ProduitCRUD {
    
    Connection cnx2;

    public ProduitCRUD() {

        cnx2 = MyConnection.getInstance().getCon();
    }
    
    
     public void ajouterProduit() {
        String requete = "INSERT INTO produit (nom_produit,prix_produit,categorie_produit,quantite_produit,description)"
                + "VALUES ('Livre meditation',20,'Lecture',10,'bien')";
        try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit ajouteé avec succès");
        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
        }
    }
     
    //recherche selon id 
          public Produit GetOne(int id) {
        try {

            String requete3 = "SELECT * FROM `produit` WHERE `id_produit`="+ id;
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Produit p = new Produit();
                p.setId_produit(rs.getInt(1));
                p.setNom_produit(rs.getString("NomProd"));
                p.setPrix_produit(rs.getFloat("PrixProd"));
                p.setQuantite_produit(rs.getInt("quantProd"));
                p.setCategorie_produit(rs.getString("TypeProd"));
                p.setImage_produit(rs.getString("image"));
                p.setDescription(rs.getString("descripttion"));
             return p;
            }

        } catch (SQLException ex) {
            System.err.println("ex.getMessage()");
        }
        return null;
    }
     
          
         public void supprimerProduit(int mr) {

        try {
            String sql = "delete from produit where id_produit=" + mr;
            PreparedStatement ste = cnx2.prepareStatement(sql);
            ste.executeUpdate();

            System.out.println("produit a été supprimé avec succés ");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
         
     public List<Produit> ChercherProduit(int id) throws SQLException {
        System.out.println("entre l Id de produit à Chercher");
        Scanner sc = new Scanner(System.in);
        String f = sc.nextLine();
        List<Produit> Produits = new ArrayList<>();

        try {
            String sql = "SELECT * FROM produit where id_produit=" + f;
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setId_produit(rs.getInt(1));
                p.setNom_produit(rs.getString("NomProd"));
                p.setPrix_produit(rs.getFloat("PrixProd"));
                p.setQuantite_produit(rs.getInt("quantProd"));
                p.setCategorie_produit(rs.getString("TypeProd"));
                p.setImage_produit(rs.getString("image"));
                p.setDescription(rs.getString("descripttion"));
                Produits.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("------> "+Produits.size());
        return Produits;
    }     
     
         public void updateProduit(Produit p, int id) {
        try {
            String sql = "UPDATE produit SET Nom_produit=?, Prix_produit=?, Quantite_produit=? ,Description=?,Image_produit=? WHERE id_produit="+id;
            PreparedStatement ste = cnx2.prepareStatement(sql);
            ste.setString(1, p.getNom_produit());
            ste.setFloat(2, p.getPrix_produit());
            ste.setInt(3, p.getQuantite_produit());
            ste.setString(4, p.getCategorie_produit());
            ste.setString(5, p.getDescription());
            ste.setString(5, p.getImage_produit());
            ste.executeUpdate();
            System.out.println("Votre produit est modifie !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}*/
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package changemakers.services;

import changemakers.entities.ProduitCommande;
import changemakers.entities.Produit;
import changemakers.entities.Commande;
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
import java.util.Base64;

/**
 *
 * @author zaiir
 */
public class ProduitCommadeCRUD {
    /*
      Connection cnx2;

    public ProduitCommadeCRUD() {

        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    
     /*public List<ProduitCommande> selectAll() throws SQLException {
        List<ProduitCommande> temp = new ArrayList<>();

        String req = "SELECT * FROM `produit`";
        Statement st = cnx2.createStatement();

        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            ProduitCommande p = new ProduitCommande();

            p.setId_produit(rs.getInt(1));
            p.setNom_produit(rs.getString(2));
            p.setPrix_produit(rs.getInt(3));
            p.setQuantite_produit(rs.getInt(4));
            p.setCategorie_produit(rs.getString(5));
            p.setImage_produit(rs.getString(6));
            p.setDescription(rs.getString(7));

            temp.add(p);
        }

        return temp;*/
    /*
    public List<ProduitCommande> selectAll() throws SQLException {
    List<ProduitCommande> temp = new ArrayList<>();

    String req = "SELECT p.*, pc.quantite, c.* FROM produit p " +
                 "JOIN produit_commande pc ON p.id_produit = pc.id_produit " +
                 "JOIN commande c ON pc.id_commande = c.id_commande";

    try (PreparedStatement ps = cnx2.prepareStatement(req);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Produit produit = new Produit(rs.getInt("id_produit"),
                                           rs.getString("nom_produit"),
                                           rs.getFloat("prix_unitaire"));
            Commande commande = new Commande(rs.getInt("id_commande"),
                                             rs.getFloat("frais_commande"),
                                             rs.getDate("date_livraison"),
                                             rs.getString("etat"));

            ProduitCommande produitCommande = new ProduitCommande(produit,
                                                                  rs.getInt("quantite"));
            produitCommande.setCommande(commande);

            temp.add(produitCommande);
        }
    }

    return temp;*/
}


    
    


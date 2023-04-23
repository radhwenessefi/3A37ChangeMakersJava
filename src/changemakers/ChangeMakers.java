/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package changemakers;
import changemakers.utils.MyConnection;
import changemakers.services.CommandeCRUD;
import changemakers.services.ProduitCRUD;
import  changemakers.entities.Produit;
import  changemakers.entities.Commande;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
//import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author zaiir
 */
public class ChangeMakers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        MyConnection mc = MyConnection.getInstance();
    

      
           /*tester l ajout 
ProduitCRUD produitCRUD = new ProduitCRUD();
        
       Produit p = new Produit("Guide n", 55 , 10,"outils ","ee", "Livre");
        
     try{
            produitCRUD.ajouterProduit(p);
                    } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    */
    
  
        /*CommandeCRUD commandeCRUD = new CommandeCRUD();
        // Convertir la chaîne de caractères de la date de livraison en objet java.util.Date
String dateLivraisonStr = "2023-03-30";
DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
Date dateLivraison = format.parse(dateLivraisonStr);

// Créer un objet Commande avec la date de livraison en tant que java.util.Date
Commande c = new Commande(5, dateLivraison, "livré", 6);

    
        
     try{
            commandeCRUD.ajouterCommande(c);
                    } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }/*

     
     /*tester l update 
     ProduitCRUD produitCRUD = new ProduitCRUD();
        
       Produit p = new Produit("Guide n", 55 , 10,"outils ", "eee","l");
       
        
       produitCRUD.updateProduit(1,p);
       
       /*tester l affichage
        ProduitCRUD produitCRUD = new ProduitCRUD();
        try {
        // Appeler la méthode selectAll() pour récupérer la liste des produits
        List<Produit> produits = produitCRUD.selectAll();
        // Parcourir la liste des produits et afficher chaque produit
        for (Produit p : produits) {
        System.out.println(p.toString());
        }
        } catch (SQLException e) {
        e.printStackTrace();
        }
         */
        
        
        // Créer quelques produits
//Produit produit1 = new Produit(1, "Ordinateur portable", 799.99f, 10, "Informatique", "https://example.com/images/ordinateur-portable.jpg", "Un ordinateur portable performant pour les professionnels");


// Créer une commande
//Commande commande = new Commande(1, 10.0f, new Date(), "En cours");

// Ajouter des produits à la commande
//commande.ajouterProduit(produit1, 1);


// Afficher les produits de la commande avec leur quantité

/*System.out.println(ProduitCommande.getProduit().getNom_produit() + " : " + ProduitCommande.getQuantite());*/
}







  }


    

   //List<Produit> produitsCherches = ChercherProduit();
         //for (Produit produit : produitsCherches) {
                //System.out.println(produit.getId_produit() + " " + produit.getNom_produit() + " " + produit.getPrix_produit());
                    
      
        //t.supprimerProduit(20);
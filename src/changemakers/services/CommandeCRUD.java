/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package changemakers.services;

import changemakers.utils.MyConnection;
import java.sql.Connection;
import changemakers.entities.Commande;
import changemakers.entities.Produit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author zaiir
 */
public class CommandeCRUD {
     Connection cnx2;

    public CommandeCRUD() {

        cnx2 = MyConnection.getInstance().getCnx();
    }
    
     public void ajouterCommande(Commande c) throws SQLException{
         
            String req = "INSERT INTO commande(frais_commande,date_livraison,etat,quantite_livre)values(?,?,?,?)";
            PreparedStatement pst =  cnx2.prepareStatement(req);
            pst.setFloat(1, c.getFrais_commande());
    
            // Convertir la java.util.Date en java.sql.Date et l'insérer dans la requête préparée
            pst.setDate(2, new java.sql.Date(c.getDate_livraison().getTime()));
            pst.setString(3, c.getEtat());
    
      
                   

            pst.executeUpdate();
            System.out.println("Commande ajouté !");
      
    }
       
         /*String requete = "INSERT INTO commande (frais_livraison,date_livraison, etat,quantite_livre)"
                + "VALUES (7,'2023-03-30','Livré',3)";
        try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Commande ajouteé avec succès");
        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
        }
    }*/
    
       /*public void modifierCommande(Commande cNew ){
         try {
            String req = "update commande set id_commande = ? , frais_commande = ? , date_livraison = ? , etat = ?,quantite_livre= ? , where id_commande = " + cOld.getId_commande();
            PreparedStatement pst = cnx2.prepareStatement(req);
            pst.setObject(1,cNew.getId_commande());
            pst.setObject(2,cNew.getFrais_commande());
            pst.setObject(3,cNew.getEtat());
            pst.setObject(4,cNew.getQuantite_livre());
            pst.setObject(5,cNew.getDate_livraison());
    
            pst.executeUpdate();
            System.out.println("commande modifié");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }*/
     public List<Commande> selectAll() throws SQLException {
        List<Commande> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `commande`";
        Statement st = cnx2.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Commande p = new Commande();
            
            p.setId_commande(rs.getInt(1));
            p.setFrais_commande(rs.getInt(2));
            p.setDate_livraison(rs.getDate(3));
            p.setEtat(rs.getString(4));
        
          
         
                        
            temp.add(p);
        }
        
        
        return temp;
        
    }
     
     
   
    
     
       public void supprimerCommande(int ID) throws SQLException {
      

        String req = "DELETE FROM commande WHERE id_commande=?";
        try {
            PreparedStatement pst = (PreparedStatement) cnx2.prepareStatement(req);
            pst.setInt(1, ID);
            pst.executeUpdate();
        } catch (SQLException ex) {
                        System.out.println(ex);
        }

    }

       
          public void updateCommande(Commande c, int id) {
        try {
            String sql = "UPDATE commande SET fraix_livraison=?, date_livraison=?, etat=? ,quantite_livre=? WHERE id_commande="+id;
            PreparedStatement ste = cnx2.prepareStatement(sql);
            ste.setFloat(1, c.getFrais_commande());
            ste.setDate(2, (Date) c.getDate_livraison());
            ste.setString(3, c.getEtat());
         
           
           
            ste.executeUpdate();
            System.out.println("Votre commande est modifie !!");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour de la commande:" + ex.getMessage());
        }
    }
}
    


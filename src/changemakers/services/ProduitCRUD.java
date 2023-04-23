/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package changemakers.services;

import changemakers.entities.Produit;
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
public class ProduitCRUD {

    Connection cnx2;

    public ProduitCRUD() {

        cnx2 = MyConnection.getInstance().getCnx();
    }

    public void ajouterProduit(Produit p) throws SQLException {
        String req = "INSERT INTO produit(nom_produit,prix_produit,categorie_produit,quantite_produit,description,image_produit)values(?,?,?,?,?,?)";
        PreparedStatement pst = cnx2.prepareStatement(req);

        pst.setString(1, p.getNom_produit());
        pst.setFloat(2, p.getPrix_produit());
        pst.setString(3, p.getCategorie_produit());
        pst.setInt(4, p.getQuantite_produit());
        pst.setString(5, p.getDescription());
        pst.setString(6,p.getImage_produit());
        // Encode the image data as a Base64 string
            
//String imageData = Base64.getEncoder().encodeToString(p.getImage_produit());

// Decode the Base64 string back into a byte array
//byte[] imageDataBytes = Base64.getDecoder().decode(imageData);

//pst.setBytes(6, imageDataBytes);
         
        pst.executeUpdate();
        System.out.println("Produit added !");
        /*String req = "INSERT INTO produit(nom_produit,prix_produit,categorie_produit,quantite_produit,description,image_produit) values(?,?,?,?,?,?)";
           PreparedStatement pst =  cnx2.prepareStatement(req);
            pst.setString(1, p.getNom_produit());
            pst.setFloat(2, p.getPrix_produit());
            pst.setString(3, p.getCategorie_produit());
            pst.setInt(4,p.getQuantite_produit());
            pst.setString(5,p.getDescription());
            String imagePath = p.getImage_produit();
            imagePath = imagePath.replace("\\", "\\\\");
            pst.setString(6, imagePath);
            pst.executeUpdate();
            System.out.println("Produit added !");  */
    }

    //recherche selon id 
    public Produit GetOne(int id) {
        try {

            String requete3 = "SELECT * FROM `produit` WHERE `id_produit`=" + id;
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
        System.out.println("------> " + Produits.size());
        return Produits;
    }

    public void updateProduit(int id, Produit p) {

        try {
            String sql = "UPDATE produit SET nom_produit=?, prix_produit=?, quantite_produit=? ,categorie_produit=?, description=? WHERE id_produit=" + id;
            PreparedStatement ste = cnx2.prepareStatement(sql);
            ste.setString(1, p.getNom_produit());
            ste.setFloat(2, p.getPrix_produit());
            ste.setInt(3, p.getQuantite_produit());
            ste.setString(4, p.getCategorie_produit());
            ste.setString(5, p.getDescription());
            /*ste.setBytes(6, p.getImage_produit());*/
                 ste.setString(6, p.getImage_produit());
            ste.executeUpdate();
            System.out.println("Votre produit est modifie !!");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour : " + ex.getMessage());
        }

        /* try {
            String sql = " UPDATE produit SET " + " nom_produit = ?, prix_produit = ?, Quantite_produit= ?,Description=?,  WHERE id_produit = ? " ;
          PreparedStatement ste = cnx2.prepareStatement(sql);
          
               ste.setString(1,p.getNom_produit());
            ste.setFloat(2, p.getPrix_produit());
            ste.setInt(3, p.getQuantite_produit());
            ste.setString(4, p.getCategorie_produit());
            ste.setString(5, p.getDescription());
        ste.executeUpdate();
            System.out.println("produit modifiée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }*/
    }

    public List<Produit> selectAll() throws SQLException {
        List<Produit> temp = new ArrayList<>();

        String req = "SELECT * FROM `produit`";
        Statement st = cnx2.createStatement();

        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            Produit p = new Produit();

            p.setId_produit(rs.getInt(1));
            p.setNom_produit(rs.getString(2));
            p.setPrix_produit(rs.getInt(3));
            p.setQuantite_produit(rs.getInt(4));
            p.setCategorie_produit(rs.getString(5));
            p.setImage_produit(rs.getString(6));
            p.setDescription(rs.getString(7));

            temp.add(p);
        }

        return temp;

    }
}

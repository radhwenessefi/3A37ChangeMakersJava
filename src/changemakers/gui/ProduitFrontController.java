/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package changemakers.gui;

import changemakers.entities.Produit;
//import changemakers.entities.ProduitCommande;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zaiir
 */
public class ProduitFrontController implements Initializable {

    @FXML
    private Label labelTitre1;
    @FXML
    private ImageView imageContainer;
    private Label labelDescription;
    private Label labelListe;

    private Produit produit;
    @FXML
    private Button addToCartButton;

    //private ArrayList<ProduitCommande> ListProd;
    @FXML
    private TextField qte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Produit produits) throws SQLException {
        String image_produit = produits.getImage_produit();
        File file = new File("C:\\Users\\zaiir\\OneDrive\\Documents\\NetBeansProjects\\ChangeMakers\\src\\image" + image_produit);
        Image image1 = new Image(file.toURI().toString());
        imageContainer.setImage(image1);
        labelTitre1.setText(produits.getNom_produit());
        labelDescription.setText(produits.getDescription());
        labelListe.setText(String.valueOf(produits.getPrix_produit()));

        this.produit = produits;

    }

    @FXML
    private void AjouterAuPanier(ActionEvent event) throws IOException {
         // Get the selected product
        Produit selectedProduct = produit;
        // Load the "panier.fxml" controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("panier.fxml"));
        Parent root = loader.load();
         PanierController panierController = loader.getController();
        // Add the selected product to the table view in the "panier.fxml" controller
         panierController.addProductToTable(selectedProduct);
        // Close the current window
        //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //stage.close();

       /* Produit p= produit;
        int quantite = Integer.parseInt(qte.getText());
        for (ProduitCommande prod:ListProd){
            if (prod.getProduit().equals(p)){
                prod.quantite+=quantite;
                break;
            } else {                
                ListProd.add(new ProduitCommande(p, quantite));
            }
        }
        for (ProduitCommande pc:ListProd) {
            System.out.println(pc.toString());*/
        }
        
        
       
    

    @FXML
    private void incrementQuantity(ActionEvent event) {
    }

    @FXML
    private void decrementQuantity(ActionEvent event) {
    }
}

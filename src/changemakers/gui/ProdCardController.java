/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package changemakers.gui;

import changemakers.entities.Produit;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author zaiir
 */
public class ProdCardController implements Initializable {

    @FXML
    private Label labelTitre1;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfCategorie;
    @FXML
    private TextField tfDesc;
    @FXML
    private Button addToCartButton;

    private Produit p;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Produit produit) throws SQLException {
        this.p = produit;

        String image_produit = produit.getImage_produit();
        File file = new File("C:\\Users\\zaiir\\OneDrive\\Documents\\NetBeansProjects\\ChangeMakers\\src\\image" + image_produit);
        Image image1 = new Image(file.toURI().toString());
        labelTitre1.setText(produit.getNom_produit());
        tfDesc.setText(produit.getDescription());
        tfPrix.setText(String.valueOf(produit.getPrix_produit()));
    }

    @FXML
    private void AjouterAuPanier(ActionEvent event) {
    }

}

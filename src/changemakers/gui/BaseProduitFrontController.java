/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package changemakers.gui;

import changemakers.entities.Produit;
import changemakers.gui.AffichageProduitFXMLController;
import changemakers.services.ProduitCRUD;
import java.awt.CardLayout;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author zaiir
 */
public class BaseProduitFrontController implements Initializable {

    private List<Produit> listeProduit;
    @FXML
    private TextField search;
    @FXML
    private ScrollPane scrollPane1;
    @FXML
    private GridPane productGrid;
    ObservableList<Produit> produits = FXCollections.observableArrayList();
    ProduitCRUD pc = new ProduitCRUD();
    @FXML
    private VBox cardLayout;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Produit> ls = new ArrayList<>();
        try {
            ls = pc.selectAll();
            for (Produit p:ls) {
                System.out.println(p.toString());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("prodCard.fxml"));
                AnchorPane cardBox = loader.load();
                ProdCardController pcc = loader.getController();
                pcc.setData(p);
                cardLayout.getChildren().add(cardBox);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseProduitFrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BaseProduitFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
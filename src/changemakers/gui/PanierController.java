/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package changemakers.gui;

import changemakers.entities.Produit;
import changemakers.entities.ProduitCommande;
import changemakers.services.ProduitCRUD;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author zaiir
 */
public class PanierController implements Initializable {

    private ObservableList<Produit> ProduitList;
    private ObservableList<ProduitCommande> PCList;

    private ProduitCRUD pc;

    /**
     * Initializes the controller class.
     */
    // Find your Account Sid and Token at console.twilio.com
    public static final String ACCOUNT_SID = "AC6c197603e9b32566e57ee11b80fb452e";
    public static final String AUTH_TOKEN = "58bc3c2664282324bbde9c1e520ebc3b";
    @FXML
    private TableView<ProduitCommande> tabpanier;
    @FXML
    private TableColumn<ProduitCommande, String> nom;
    @FXML
    private TableColumn<ProduitCommande, Float> prix;
    @FXML
    private Label prix_total;
    private double total = 0;
    @FXML
    private TableColumn<?, ?> qte;

    public void addProductToTable(ProduitCommande produit) {
        // Add the product to the table view
        tabpanier.getItems().add(produit);

        // Update the total price
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProduitCRUD pc = new ProduitCRUD();
        for (ProduitCommande pcom : PCList) {
            total += pcom.getProduit().getPrix_produit();
            prix_total.setText(String.format("%.2f", total));
        }
        /*try {
          ObservableList<Produit> ProduitList = FXCollections.observableArrayList(pc.selectAll());
        } catch (SQLException ex) {
            Logger.getLogger(AffichageProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        nom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
         qte.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
        tabpanier.setItems(PCList);

    }

    @FXML
    private void EnvoyerSMS(ActionEvent event) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        com.twilio.rest.api.v2010.account.Message message;
        message = com.twilio.rest.api.v2010.account.Message
                .creator(
                        new PhoneNumber("+21690282670"),
                        new PhoneNumber("+19034378027"),
                        "Votre commande à été validé"
                )
                .create();

        System.out.println(message.getSid());
    }

}

/**
 *
 * @author zaiir
 */

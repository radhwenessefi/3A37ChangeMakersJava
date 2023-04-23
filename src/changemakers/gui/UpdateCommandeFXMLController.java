/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package changemakers.gui;

import changemakers.entities.Commande;
import changemakers.entities.Produit;
import changemakers.services.CommandeCRUD;
import changemakers.services.ProduitCRUD;
import java.sql.Date;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author zaiir
 */
public class UpdateCommandeFXMLController implements Initializable {

    @FXML
    private BorderPane mainPane;
    @FXML
    private Button ao_btn_dashboard;
    @FXML
    private Button ao_btn_manage_users;
    @FXML
    private Button ao_btn_manage_users1;
    @FXML
    private Button ao_btn_manage_users11;
    @FXML
    private Button ao_btn_manage_users12;
    @FXML
    private Button ao_btn_manage_users13;
    @FXML
    private Button ao_btn_manage_users131;
    @FXML
    private TextField tprix;
    @FXML
    private TextField tetat;
    @FXML
    private TextField tquantiteliv;
    @FXML
    private DatePicker tdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setTtprix(float tprix) {
    this.tprix.setText(String.valueOf(tprix));
}


  public void settquantiteliv(int tquantiteliv) {
    this.tquantiteliv.setText(String.valueOf(tquantiteliv));
}

    public void settetat(String tetat) {
        this.tetat.setText(tetat);
    }
    /*public void tdate(Date tdate) {
    this.tdate.setValue(tdate);
}*/
    public void tdate(Date tdate) {
    Instant instant = tdate.toInstant();
    ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
    LocalDate localDate = zonedDateTime.toLocalDate();
    this.tdate.setValue(localDate);
}



    @FXML
    private void GoToManagUsers(ActionEvent event) {
    }

    @FXML
    private void GoToManagProd(ActionEvent event) {
    }

    @FXML
    private void GoToManagMusic(ActionEvent event) {
    }

    @FXML
    private void GoToManagSport(ActionEvent event) {
    }

    @FXML
    private void GoToManagReclamation(ActionEvent event) {
    }

    @FXML
    private void GoToManagBlog(ActionEvent event) {
    }

    @FXML
    private void UpdateCom(ActionEvent event) {
     
        float frais_commande = Float.parseFloat(tprix.getText());
        //Date date_livraison=tdate.setValue(Date.valueOf(LocalDate.now()));
        
          String etat = tetat.getText();
        int quantite_livre = Integer.parseInt(tquantiteliv.getText());

        /*if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "L'adresse email n'est pas au format correct.");
            alert.showAndWait();
        }

        else if((nom.isEmpty()) || (!Character.isUpperCase(nom.charAt(0)) && !nom.matches("^[a-zA-Z0-9]+$"))) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le nom d'utilisateur ne peut être vide et doit contenir que des lettres et des chiffres.");
            alert.showAndWait();
        }

        else if ((prenom.isEmpty()) || (!Character.isUpperCase(prenom.charAt(0)) && !prenom.matches("^[a-zA-Z0-9]+$"))) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le nom d'utilisateur ne peut être vide et doit contenir que des lettres et des chiffres.");
            alert.showAndWait();
        }

        else if (mdp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le mot de passe est obligatoire.");
            alert.showAndWait();
        }
        
        else if (adresse.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "L'adresse est obligatoire.");
            alert.showAndWait();
        }
         
        else if((tel.isEmpty())||(!tel.matches("^[0-9]+$")))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le numéro de téléphone est obligatoire et ne doit contenir que des chiffres.");
            alert.showAndWait();
        }
        else
        {*/
            CommandeCRUD sp = new CommandeCRUD();
            
                /*Commande commande = new Commande(7,"livré",17);
                sp.updateCommande(commande, 1);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "commande modifé!");
                alert.showAndWait(); 
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageCommandeFXML.fxml"));

        try {
            Parent root = loader.load();
            AffichageCommandeFXMLController mn = loader.getController();
            mn.setTtprix(commande.getFrais_commande());
            mn.setLbsettquantitelivnom((Date) commande.getDate_livraison());
            mn.settetat(commande.getEtat());
            mn.tdate(commande.getQuantite_livre());
            
            btUpdate.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
               
        }*/
    
    }
    
}



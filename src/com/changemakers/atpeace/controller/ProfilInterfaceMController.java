/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entities.Medecin;
import com.changemakers.atpeace.services.ServiceMedecin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gille
 */
public class ProfilInterfaceMController implements Initializable {

    @FXML
    private Label tbusername;
    @FXML
    private Label suppCompte;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tftelphone;
    @FXML
    private TextField tfnom;
    @FXML
    private Button btUpdate;

    private int id;
    @FXML
    private TextArea tadiplome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTbusername(String tbusername) {
        this.tbusername.setText(tbusername);
    }

    public void setTfprenom(String tfprenom) {
        this.tfprenom.setText(tfprenom);
    }

    public void setTfemail(String tfemail) {
        this.tfemail.setText(tfemail);
    }

    public void setTfadresse(String tfadresse) {
        this.tfadresse.setText(tfadresse);
    }

    public void setTftelphone(String tftelphone) {
        this.tftelphone.setText(tftelphone);
    }

    public void setTfnom(String tfnom) {
        this.tfnom.setText(tfnom);
    }

    public void setTadiplome(String tadiplome) {
        this.tadiplome.setText(tadiplome);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("RendezVousInterfaceM.fxml"));

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProfilInterfaceMController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerCompte(MouseEvent event) {
    }

    @FXML
    private void Update(ActionEvent event) {

        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String tel = tftelphone.getText();
        String adresse = tfadresse.getText();
        String email = tfemail.getText();
        String diplome = tadiplome.getText();
        if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "L'adresse email n'est pas au format correct.");
            alert.showAndWait();
        } else if ((nom.isEmpty()) || (!Character.isUpperCase(nom.charAt(0)) && !nom.matches("^[a-zA-Z0-9]+$"))) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le nom d'utilisateur ne peut être vide et doit contenir que des lettres et des chiffres.");
            alert.showAndWait();
        } else if ((prenom.isEmpty()) || (!Character.isUpperCase(prenom.charAt(0)) && !prenom.matches("^[a-zA-Z0-9]+$"))) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le nom d'utilisateur ne peut être vide et doit contenir que des lettres et des chiffres.");
            alert.showAndWait();
        } else if (adresse.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "L'adresse est obligatoire.");
            alert.showAndWait();
        } else if (diplome.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veillez indiquer votre diplome.");
            alert.showAndWait();
        } else if ((tel.isEmpty()) || (!tel.matches("^[0-9]+$"))) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le numéro de téléphone est obligatoire et ne doit contenir que des chiffres.");
            alert.showAndWait();
        } else {
            ServiceMedecin sm = new ServiceMedecin();

            Medecin medecin = new Medecin(id, diplome, nom, prenom, tel, adresse, email);
            sm.Update(medecin);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Utilisateur modifié!");
            alert.showAndWait();

        }
    }

    @FXML
    private void deconnecter(MouseEvent event) {
        try{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("SigninInterface.fxml"));
        Parent root = loader.load();       
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entities.Medecin;
import com.changemakers.atpeace.entities.Patient;
import com.changemakers.atpeace.services.ServiceMedecin;
import com.changemakers.atpeace.services.ServicePatient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author gille
 */
public class SignUpMController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfTelephone;
    @FXML
    private RadioButton rapatient;
    @FXML
    private ToggleGroup user;
    @FXML
    private RadioButton ramedecin;
    @FXML
    private TextArea taDiplome;
    @FXML
    private RadioButton radispo;
    @FXML
    private ToggleGroup etat;
    @FXML
    private RadioButton raindispo;
    @FXML
    private Button sincrire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        radispo.setVisible(false);
        raindispo.setVisible(false);
        taDiplome.setVisible(false);

    }

    @FXML
    private void SignUp(ActionEvent event) {
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String tel = tfTelephone.getText();
        String adresse = tfAdresse.getText();
        String email = tfEmail.getText();
        String mdp = tfPassword.getText();
        String diplome = taDiplome.getText();

        String hashpwd = BCrypt.hashpw(mdp, BCrypt.gensalt(13));

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
        } else if (mdp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le mot de passe est obligatoire.");
            alert.showAndWait();
        } else if (adresse.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "L'adresse est obligatoire.");
            alert.showAndWait();
        } else if ((tel.isEmpty()) || (!tel.matches("^[0-9]+$"))) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le numéro de téléphone est obligatoire et ne doit contenir que des chiffres.");
            alert.showAndWait();
        } else if (!ramedecin.isSelected() && !rapatient.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Selectionner un type d'utilisateur.");
            alert.showAndWait();
        } else { // I l'utilisateur est un medecin
            if (ramedecin.isSelected()) {
                if (diplome.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Veillez indiquer votre diplome.");
                    alert.showAndWait();
                } else if (!radispo.isSelected() && !raindispo.isSelected()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Veillez indiquer votre disponibilité.");
                    alert.showAndWait();
                } else {
                    ServiceMedecin sm = new ServiceMedecin();
                    Medecin m = sm.Verifier(email);
                    String etat = "";
                    if (m.getEmail() != null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "L' utilisateur existe déjà.");
                        alert.showAndWait();
                    } else {
                        if (radispo.isSelected()) {
                            etat = "Disponible";
                        }
                        if (raindispo.isSelected()) {
                            etat = "Non disponible";
                        }
                        Medecin medecin = new Medecin(diplome, etat, nom, prenom, tel, adresse, email, hashpwd);
                        sm.Insert(medecin);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Utilisateur ajouté!");
                        alert.showAndWait();
                        // this.signIn(event);
                    }
                }

            } else //L'utilisateur est un patient
            {
                ServicePatient sp = new ServicePatient();
                Patient p = sp.Verifier(email);

                if (p.getEmail() != null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "L' utilisateur existe déjà.");
                    alert.showAndWait();
                } else {

                    Patient patient = new Patient(nom, prenom, tel, adresse, email, hashpwd);
                    sp.Insert(patient);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Utilisateur ajouté!");
                    alert.showAndWait();
                    //this.signIn(event);
                }
            }
        }

    }

    @FXML
    private void selection(ActionEvent event) {

        if (ramedecin.isSelected()) {
            radispo.setVisible(true);
            raindispo.setVisible(true);
            taDiplome.setVisible(true);
        } else {
            radispo.setVisible(false);
            raindispo.setVisible(false);
            taDiplome.setVisible(false);
        }
    }

}

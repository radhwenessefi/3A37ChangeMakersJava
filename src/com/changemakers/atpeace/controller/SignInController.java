/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entities.Medecin;
import com.changemakers.atpeace.entities.Patient;
import com.changemakers.atpeace.entities.Session;
import com.changemakers.atpeace.entities.User;
import com.changemakers.atpeace.services.ServiceMedecin;
import com.changemakers.atpeace.services.ServicePatient;
import com.changemakers.atpeace.services.SessionService;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author gille
 */
public class SignInController implements Initializable {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMdp;
    @FXML
    private Button mdpoublie;

    FXMLLoader loader;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void MdpOublie(ActionEvent event) {
    }

    @FXML
    private void SignIn(ActionEvent event) {
        String email = tfEmail.getText();
        String password = tfMdp.getText();
        if (email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veiller remplir ce champ.");
            alert.showAndWait();
        } else if (email.compareTo("ADMIN")== 0 && password.compareTo("admin")== 0) {
            try {
                // Si l'utilisateur est l'admin
                /* role = "ROLE_ADMIN";
                    s = new Session(1, "Admin", role);*/
                loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/MenuInterface.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
                // afficher un message d'erreur et sortir de la méthode
                Alert alert = new Alert(Alert.AlertType.ERROR, "L'adresse email n'est pas au format correct.");
                alert.showAndWait();
            } else if (password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Le mot de passe est obligatoire.");
                alert.showAndWait();
            } else {
                //Si ne l'est pas
                User user = new User();
                Patient patient = new Patient();
                Medecin medecin = new Medecin();
                ServicePatient ps = new ServicePatient();
                patient = ps.Verifier(email);
                Session s = new Session();
                ServiceMedecin sm = new ServiceMedecin();
                medecin = sm.Verifier(email);
                String role = "";
                //On verifie s'il  s'agie d'un patient ou  un medecin
                if (patient.getEmail() != null) {
                    if (BCrypt.checkpw(password, patient.getPassword())) {
                        try {
                            user = (User) patient;
                            for (int i = 0; i < user.getRole().length; i++) {
                                role += user.getRole()[i];
                            }
                            s = new Session(user.getId(), user.getNom(), role);
                            loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/MenuFrontInterface.fxml"));
                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                } else if (medecin.getEmail() != null) {
                    if (BCrypt.checkpw(password, medecin.getPassword())) {
                        try {
                            user = (User) medecin;
                            for (int i = 0; i < user.getRole().length; i++) {
                                role += user.getRole()[i];
                            }
                            s = new Session(user.getId(), user.getNom(), role);
                            loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/RendezVousInterfaceM.fxml"));
                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Mot de passe incorrect.");
                    alert.showAndWait();
                }
                SessionService ss = new SessionService();
                System.out.println("user: " + user.getId());

                System.out.println("session : " + s.getId_user());
                ss.Delete();
                ss.Insert(s);
            }

        }
    }
}

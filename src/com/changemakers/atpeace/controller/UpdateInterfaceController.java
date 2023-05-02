/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.controller;
/*
import com.changemakers.atpeace.controller.SigninInterfaceController;
import com.changemakers.atpeace.controller.MenuInterfaceController;
import com.changemakers.atpeace.entities.Patient;
import com.changemakers.atpeace.services.ServicePatient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author gille
 */
/*public class UpdateInterfaceController implements Initializable {

    @FXML
    private Label tbusername;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tftelphone;
    private PasswordField tfmdp;
    @FXML
    private TextField tfnom;
    @FXML
    private Button btUpdate;
    @FXML 
    private Label suppCompte;

    private int id;
            */
    /**
     * Initializes the controller class.
     */
   /* @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    public void setTfmdp(String tfmdp) {
        this.tfmdp.setText(tfmdp);
    }

    public void setTfnom(String tfnom) {
        this.tfnom.setText(tfnom);
    }

    public void setBtUpdate(Button btUpdate) {
        this.btUpdate = btUpdate;
    }*/
    
    
    
   /* @FXML
    private void Update(ActionEvent event) {
        
          // public Patient(int id,String nom, String prenom, String telephone, String adresse, String email, String password) { 
        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String tel = tftelphone.getText();
        String adresse = tfadresse.getText();
        String email = tfemail.getText();
        String mdp = tfmdp.getText();

        if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
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
        {
            ServicePatient sp = new ServicePatient();
            
                Patient patient = new Patient(id,nom,prenom,tel,adresse,email);
                sp.Update(patient);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Utilisateur modifé!");
                alert.showAndWait(); 
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuInterface.fxml"));

        try {
            Parent root = loader.load();
            MenuInterfaceController mn = loader.getController();
            mn.setLbemail(patient.getEmail());
            mn.setLbnom(patient.getNom());
            mn.setLbprenom(patient.getPrenom());
            mn.setLbtelephone(patient.getTelephone());
            mn.setLbusename(patient.getNom());
            mn.setLbadresse(patient.getAdresse());

            btUpdate.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
               
        }
    
    }*/

  /*  @FXML
    private void supprimerCompte(MouseEvent event) {
        
        String email = tfemail.getText();
         if (email.isEmpty() || !email.matches("^\\S+@\\S+\\.\\S+$")) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "L'adresse email n'est pas au format correct.");
            alert.showAndWait();
        }
         else
         {
             ServicePatient sp = new ServicePatient();
             sp.Delete(email);
             Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le compte à bien été supprimer.");
             alert.showAndWait();
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("SigninInterface.fxml"));
           try {
            Parent root = loader.load();
            SigninInterfaceController mn = loader.getController();
           suppCompte.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }

    @FXML
    private void retour(ActionEvent event) {
    }
    
}
*/
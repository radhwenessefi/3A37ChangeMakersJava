/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gille
 */
public class MenuInterfaceController implements Initializable {

    @FXML
    private Label tbusername;
    private Label lbprenom;
    private Label lbemail;
    private Label lbadresse;
    private Label lbtelephone;
    @FXML
    private Label lbnom;
    @FXML
    private ImageView imup;
    @FXML
    private Label lbEt;
    @FXML
    private Label lbetat;
    @FXML
    private Label lbDi;
    @FXML
    private Label lbdiplome;
    
    private int id;
    @FXML
    private Label lbrdv;
    @FXML
    private AnchorPane mainAncho;
    
    private Parent loader;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lbetat.visibleProperty().setValue(false);
        lbdiplome.visibleProperty().setValue(false);
        lbEt.visibleProperty().setValue(false);
        lbDi.visibleProperty().setValue(false);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    

    public Label getLbEt() {
        return lbEt;
    }

    public Label getLbetat() {
        return lbetat;
    }

    public Label getLbDi() {
        return lbDi;
    }

    public Label getLbdiplome() {
        return lbdiplome;
    }
    

    public void setLbnom(String nom) {
        this.lbnom.setText(nom);
    }

    public void setLbprenom(String prenom) {
        this.lbprenom.setText(prenom);
    }

    public void setLbemail(String email) {
        this.lbemail.setText(email);
    }

    public void setLbtelephone(String tel) {
        this.lbtelephone.setText(tel);
    }

    public void setLbusename(String username) {
        this.tbusername.setText(username);
    }
    
    public void setLbadresse(String adresse) {
        this.lbadresse.setText(adresse);
    }

   
    public void setLbetat(String etat) {
        this.lbetat.setText(etat);
    }


    public void setLbdiplome(String diplome) {
        this.lbdiplome.setText(diplome);
    }

    @FXML
    private void update(MouseEvent event) {
        
        /* FXMLLoader loader = new FXMLLoader(getClass().getResource("updateInteface.fxml"));
    
        try {
            Parent root = loader.load();
            UpdateInterfaceController up = loader.getController();
            up.setId(id);
            up.setTbusername(tbusername.getText());
            up.setTfadresse(lbadresse.getText());
            up.setTfemail(lbemail.getText());
            up.setTfnom(lbnom.getText());
            up.setTfprenom(lbprenom.getText());
            up.setTftelphone(lbtelephone.getText());
            
            imup.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/
    }

   /* @FXML
    private void deconnexion(MouseEvent event) {
        
    }*/

    private void rdvIn(MouseEvent event) {
          try {
                  /* */
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/RendezVousInterace.fxml"));
        Parent root = loader.load();
        RendezVousInteraceController rd = loader.getController();
        rd.setId(id);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void deconnecter(MouseEvent event) {
        try {
                  /* */
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/Main.fxml"));
        Parent root = loader.load();       
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void gotomusique(MouseEvent event) {
    }

    @FXML
    private void gotoboutique(MouseEvent event) {
    }

    @FXML
    private void gotosport(MouseEvent event) {
          try { loader = FXMLLoader.load(getClass().getResource("/com/changemakers/atpeace/gui/gereSport.fxml"));
                mainAncho.getChildren().removeAll();

// pane.getChildren().removeAll(lbcon,btconnecter);
                //pane.getChildren().addAll(lbins,btinscrire);
                mainAncho.getChildren().setAll(loader);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    @FXML
    private void gotoregime(MouseEvent event) {
         try {
                loader = FXMLLoader.load(getClass().getResource("/com/changemakers/atpeace/gui/MenuInterfaceR.fxml"));
                mainAncho.getChildren().removeAll();

// pane.getChildren().removeAll(lbcon,btconnecter);
                //pane.getChildren().addAll(lbins,btinscrire);
                mainAncho.getChildren().setAll(loader);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    @FXML
    private void gotovideo(MouseEvent event) {
    }

    @FXML
    private void gotoreclamation(MouseEvent event) {
         try { loader = FXMLLoader.load(getClass().getResource("/com/changemakers/atpeace/gui/MenuInterfaceRe.fxml"));
                mainAncho.getChildren().removeAll();

// pane.getChildren().removeAll(lbcon,btconnecter);
                //pane.getChildren().addAll(lbins,btinscrire);
                mainAncho.getChildren().setAll(loader);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    @FXML
    private void gotordv(MouseEvent event) {
        try {
                loader = FXMLLoader.load(getClass().getResource("/com/changemakers/atpeace/gui/RdvBack.fxml"));
                mainAncho.getChildren().removeAll();

// pane.getChildren().removeAll(lbcon,btconnecter);
                //pane.getChildren().addAll(lbins,btinscrire);
                mainAncho.getChildren().setAll(loader);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    @FXML
    private void gotoblock(MouseEvent event) {
    }

    @FXML
    private void gotodash(MouseEvent event) {
    }

    @FXML
    private void gotopatient(MouseEvent event) {
        try {
                loader = FXMLLoader.load(getClass().getResource("/com/changemakers/atpeace/gui/PatientBack.fxml"));
                mainAncho.getChildren().removeAll();

// pane.getChildren().removeAll(lbcon,btconnecter);
                //pane.getChildren().addAll(lbins,btinscrire);
                mainAncho.getChildren().setAll(loader);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    @FXML
    private void gotomedecin(MouseEvent event) {
        try {
                loader = FXMLLoader.load(getClass().getResource("/com/changemakers/atpeace/gui/MedecinBack.fxml"));
                mainAncho.getChildren().removeAll();

// pane.getChildren().removeAll(lbcon,btconnecter);
                //pane.getChildren().addAll(lbins,btinscrire);
                mainAncho.getChildren().setAll(loader);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }
}

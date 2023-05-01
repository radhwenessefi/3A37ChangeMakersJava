/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author gille
 */
public class MainController implements Initializable {

    @FXML
    private Button btconnecter;

    @FXML
    private Button btinscrire;

    @FXML
    private VBox vbox;

    private Parent loader;
    @FXML
    private Pane pane;
    @FXML
    private Label lbins;
    @FXML
    private Label lbcon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * (-20.5));
        t.play();
    }

    @FXML
    private void gotoSignIn(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * (-20.5));
        t.play();
        t.setOnFinished(e -> {
            try {
                loader = FXMLLoader.load(getClass().getResource("/com/changemakers/atpeace/gui/SignIn.fxml"));
                vbox.getChildren().removeAll(lbins, btinscrire);

// pane.getChildren().removeAll(lbcon,btconnecter);
                //pane.getChildren().addAll(lbins,btinscrire);
                vbox.getChildren().setAll(loader);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    private void gotoSignup(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX());
        t.play();
        t.setOnFinished(e -> {
            try {
                loader = FXMLLoader.load(getClass().getResource("/com/changemakers/atpeace/gui/SignUpM.fxml"));
                vbox.getChildren().removeAll(lbins, btinscrire);
                // pane.getChildren().removeAll(lbins,btinscrire);
                //pane.getChildren().addAll(lbcon,btconnecter);
                vbox.getChildren().setAll(loader);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

}

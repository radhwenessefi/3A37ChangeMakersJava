/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.changemakers.atpeace.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DashbordController implements Initializable {

    @FXML
    private AnchorPane paneDash;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void goToSport(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/gereSport.fxml"));
        BorderPane content = loader.load();

        AnchorPane anchorPane = new AnchorPane(content); // create a new AnchorPane and add the BorderPane to it

        // Set the anchor constraints of the BorderPane to fill the AnchorPane
        AnchorPane.setTopAnchor(content, 0.0);
        AnchorPane.setRightAnchor(content, 0.0);
        AnchorPane.setBottomAnchor(content, 0.0);
        AnchorPane.setLeftAnchor(content, 0.0);

        // Replace the existing content in your main pane with the new content
        paneDash.getChildren().setAll(anchorPane);
    }

    @FXML
    private void goToRegime(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/MenuInterface.fxml"));
        BorderPane content = loader.load();

        AnchorPane anchorPane = new AnchorPane(content); // create a new AnchorPane and add the BorderPane to it

        // Set the anchor constraints of the BorderPane to fill the AnchorPane
        AnchorPane.setTopAnchor(content, 0.0);
        AnchorPane.setRightAnchor(content, 0.0);
        AnchorPane.setBottomAnchor(content, 0.0);
        AnchorPane.setLeftAnchor(content, 0.0);

        // Replace the existing content in your main pane with the new content
        paneDash.getChildren().setAll(anchorPane);

    }

}

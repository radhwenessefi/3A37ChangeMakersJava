/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.changemakers.atpeace.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class SportFrontController implements Initializable {

    @FXML
    private TextField tfTaille;
    @FXML
    private TextField tfPoids;
    @FXML
    private Button btncalculer;
    @FXML
    private TextField tfResult;
    @FXML
    private ScrollPane scrollPane1;
    @FXML
    private GridPane productGrid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CalculeBMI(ActionEvent event) {
    }
    
}

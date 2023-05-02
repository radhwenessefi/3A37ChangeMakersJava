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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ListReponseBackController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private Label tbusername;
    @FXML
    private TableView<?> tableviewReponse;
    @FXML
    private TableColumn<?, ?> idRecColumn;
    @FXML
    private TableColumn<?, ?> solutionReponseColumn;
    @FXML
    private TableColumn<?, ?> dateReponseColumn;
    @FXML
    private TextField tftitre;
    @FXML
    private DatePicker reponsedatefield;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextArea solutionfield;
    @FXML
    private Button exportButton;
    @FXML
    private Button sortButton;
    @FXML
    private TextField idReclamationf;
    @FXML
    private Button retourRec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Select(MouseEvent event) {
    }

    @FXML
    private void ajouterReponseScene(ActionEvent event) {
    }

    @FXML
    private void UpdateItemsReponse(ActionEvent event) {
    }

    @FXML
    private void dropItemsReponse(ActionEvent event) {
    }

    @FXML
    private void sortReponse(ActionEvent event) {
    }

    @FXML
    private void switchToReclamation(ActionEvent event) {
    }
    
}

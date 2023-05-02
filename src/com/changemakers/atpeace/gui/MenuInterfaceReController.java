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
import javafx.scene.control.ComboBox;
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
public class MenuInterfaceReController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private Label tbusername;
    @FXML
    private TableView<?> tableview;
    @FXML
    private TableColumn<?, ?> idColumn;
    @FXML
    private TableColumn<?, ?> tiColumn;
    @FXML
    private TableColumn<?, ?> tyColumn;
    @FXML
    private TableColumn<?, ?> desColumn;
    @FXML
    private TableColumn<?, ?> daColumn;
    @FXML
    private TableColumn<?, ?> etColumn;
    @FXML
    private TextField tftitre;
    @FXML
    private DatePicker cdate;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextArea tadescription;
    @FXML
    private ComboBox<?> cbtype;
    @FXML
    private Button sortButton;
    @FXML
    private Button sortbytypeButton;
    @FXML
    private Button verify;
    @FXML
    private Button repondre;

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
    private void ajouter(ActionEvent event) {
    }

    @FXML
    private void UpdateItems(ActionEvent event) {
    }

    @FXML
    private void dropItems(ActionEvent event) {
    }

    @FXML
    private void sortReclamation(ActionEvent event) {
    }

    @FXML
    private void sortReclamationByType(ActionEvent event) {
    }

    @FXML
    private void EnvoyerSMS(ActionEvent event) {
    }

    @FXML
    private void switchToReponse(ActionEvent event) {
    }
    
}

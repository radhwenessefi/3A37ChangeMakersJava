/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.gui;

import com.changemakers.atpeace.entities.Patient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author gille
 */
public class GereSportController implements Initializable {

    @FXML
    private AnchorPane fifRech;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Patient> tableview;
    @FXML
    private TableColumn<?, ?> titre;
    @FXML
    private TableColumn<?, ?> desc;
    @FXML
    private TableColumn<?, ?> niveaux;
    @FXML
    private TableColumn<?, ?> image;
    @FXML
    private TableColumn<?, ?> level;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ResearchRegime(ActionEvent event) {
    }


    @FXML
    private void updateSport(MouseEvent event) {
    }

    @FXML
    private void deleteOneSport(MouseEvent event) {
    }

    @FXML
    private void ajouterSportIcon(MouseEvent event) {
    }
    
}

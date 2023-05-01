/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entities.Medecin;
import com.changemakers.atpeace.services.ServiceMedecin;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author gille
 */
public class MedecinBackController implements Initializable {

    @FXML
    private AnchorPane fifRech;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Medecin> tableview;
    @FXML
    private TableColumn<Medecin, String> colnom;
    @FXML
    private TableColumn<Medecin, String> colprenom;
    @FXML
    private TableColumn<Medecin, String> colemail;
    @FXML
    private TableColumn<Medecin, String> coladresse;
    @FXML
    private TableColumn<Medecin, Object> colrole;
    @FXML
    private TableColumn<Medecin, String> coltel;
    
        ObservableList medecindata = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         ServiceMedecin sm = new ServiceMedecin();
        List<Medecin> medecin = null;

        medecin = sm.Read();

        if (!medecin.isEmpty()) {
            for (int i = 0; i < medecin.size(); i++) {
                medecindata.add(medecin.get(i));
            }
        }
        
        colnom.setCellValueFactory(new PropertyValueFactory<Medecin, String>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<Medecin, String>("prenom"));
        coladresse.setCellValueFactory(new PropertyValueFactory<Medecin, String>("adresse"));
        colemail.setCellValueFactory(new PropertyValueFactory<Medecin, String>("email"));
        coltel.setCellValueFactory(new PropertyValueFactory<Medecin, String>("telephone"));
                 colrole.setCellValueFactory(new PropertyValueFactory<Medecin,Object>("role"));

        tableview.setItems(medecindata);
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
    
}

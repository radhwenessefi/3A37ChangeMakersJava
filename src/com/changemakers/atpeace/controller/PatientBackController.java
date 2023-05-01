/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entities.Patient;
import com.changemakers.atpeace.services.ServicePatient;
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
public class PatientBackController implements Initializable {

    @FXML
    private AnchorPane fifRech;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Patient> tableview;
    @FXML
    private TableColumn<Patient, String> colnom;
    @FXML
    private TableColumn<Patient, String> colprenom;
    @FXML
    private TableColumn<Patient, String> colemail;
    @FXML
    private TableColumn<Patient, String> coladresse;
    @FXML
    private TableColumn<Patient, Object> colrole;
    @FXML
    private TableColumn<Patient, String> coltel;
    
    List<Patient> patient;
    
     ObservableList medecindata = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         ServicePatient sm = new ServicePatient();
        List<Patient> patient = null;

        patient = sm.Read();

        if (!patient.isEmpty()) {
            for (int i = 0; i < patient.size(); i++) {
                medecindata.add(patient.get(i));
            }
        }

        colnom.setCellValueFactory(new PropertyValueFactory<Patient, String>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<Patient, String>("prenom"));
        coladresse.setCellValueFactory(new PropertyValueFactory<Patient, String>("adresse"));
        colemail.setCellValueFactory(new PropertyValueFactory<Patient, String>("email"));
        coltel.setCellValueFactory(new PropertyValueFactory<Patient, String>("telephone"));
        colrole.setCellValueFactory(new PropertyValueFactory<Patient,Object>("role"));
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

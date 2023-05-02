/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entities.Medecin;
import com.changemakers.atpeace.entities.Patient;
import com.changemakers.atpeace.entities.RendezVous;
import com.changemakers.atpeace.services.RdvService;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
/**
 * FXML Controller class
 *
 * @author gille
 */
public class RdvBackController implements Initializable {

    @FXML
    private AnchorPane fifRech;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<RendezVous> tableview;
    @FXML
    private TableColumn<RendezVous, String> colnomP;
    @FXML
    private TableColumn<RendezVous, String> colnomM;
    @FXML
    private TableColumn<RendezVous, Date> coldate;
    @FXML
    private TableColumn<RendezVous, String> coletat;

    ObservableList rdvdata = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            RdvService sm = new RdvService();
            List<RendezVous> lrdv = sm.RdvT();
            if (!lrdv.isEmpty()) {
                for (int i = 0; i < lrdv.size(); i++) {
                    rdvdata.add(lrdv.get(i));
                }
            }
            /*colnomP.setCellValueFactory(new PropertyValueFactory<Patient, String>("patient.nom"));
            colprenomP.setCellValueFactory(new PropertyValueFactory<Patient, String>("patient.prenom"));
            colnomM.setCellValueFactory(new PropertyValueFactory<Medecin, String>("medecin.nom"));
            colnomM.setCellValueFactory(new PropertyValueFactory<Medecin, String>("medecin.prenom"));*/
            colnomP.setCellValueFactory(new Callback<CellDataFeatures<RendezVous, String>, ObservableValue<String>>() {
    public ObservableValue<String> call(CellDataFeatures<RendezVous, String> r) {
        String nomPrenomPatient = r.getValue().getPatient().getNom() + " " + r.getValue().getPatient().getPrenom();
        return new SimpleStringProperty(nomPrenomPatient);
    }
});

colnomM.setCellValueFactory(new Callback<CellDataFeatures<RendezVous, String>, ObservableValue<String>>() {
    public ObservableValue<String> call(CellDataFeatures<RendezVous, String> r) {
        String nomPrenomMedecin = r.getValue().getMedecin().getNom() + " " + r.getValue().getMedecin().getPrenom();
        return new SimpleStringProperty(nomPrenomMedecin);
    }
});

            coldate.setCellValueFactory(new PropertyValueFactory<RendezVous, Date>("dateRdv"));
            coletat.setCellValueFactory(new PropertyValueFactory<RendezVous, String>("etat"));
            
            tableview.setItems(rdvdata);

        } catch (SQLException ex) {
            Logger.getLogger(RdvBackController.class.getName()).log(Level.SEVERE, null, ex);
        }

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

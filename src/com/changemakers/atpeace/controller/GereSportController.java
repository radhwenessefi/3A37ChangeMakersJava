/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.changemakers.atpeace.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import com.changemakers.atpeace.entites.Regime;
import com.changemakers.atpeace.entites.Sport;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static jdk.javadoc.internal.doclets.toolkit.util.DocPath.parent;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class GereSportController implements Initializable {

    @FXML
    private TableView<Sport> tableview;
    @FXML
    private TableColumn<Sport, String> titre;
    @FXML
    private TableColumn<Sport, String> desc;
    @FXML
    private TableColumn<Sport, String> niveaux;
    @FXML
    private TableColumn<Sport, String> image;
    @FXML
    private TableColumn<Sport, String> level;
    ObservableList<Sport> sports = FXCollections.observableArrayList();
    ObservableList<Sport> allusers;
    ObservableList<Sport> selectedsport;
    @FXML
    private AnchorPane fifRech;
    @FXML
    private TextField searchField;
    private boolean isListenerAdded = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controleSport controle = new controleSport();

        List<Sport> listemps = null;
        try {
            listemps = controle.AfficheToutSport();
        } catch (SQLException ex) {
            Logger.getLogger(MenuInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!listemps.isEmpty()) {
            for (int i = 0; i < listemps.size(); i++) {
                sports.add(listemps.get(i));
            }
        }
        titre.setCellValueFactory(new PropertyValueFactory<Sport, String>("titre"));
        desc.setCellValueFactory(new PropertyValueFactory<Sport, String>("discription"));
        niveaux.setCellValueFactory(new PropertyValueFactory<Sport, String>("niveaux"));
        image.setCellValueFactory(new PropertyValueFactory<Sport, String>("image"));
        level.setCellValueFactory(new PropertyValueFactory<Sport, String>("level"));

        tableview.setItems(sports);
    }

    /* @FXML
    private void ajouterSportIcon(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/changemakers/atpeace/gui/AjouterSport.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

     @FXML
    private void updateSport(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/changemakers/atpeace/gui/modifer_sport.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Modifer_SportController modifier = loader.getController();
        modifier.initdata((Sport) tableview.getSelectionModel().getSelectedItems().get(0)); 
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deleteOneSport(MouseEvent event) throws SQLException {
        controleSport controle = new controleSport();
        selectedsport = tableview.getSelectionModel().getSelectedItems();
        if (selectedsport.size() > 0) {
            for (Sport u : selectedsport) {
                controle.SupprimerSport(u);
            }
            sports.clear();
            List<Sport> listemps = controle.AfficheToutSport();
            for (int i = 0; i < listemps.size(); i++) {
                sports.add(listemps.get(i));
            }
            tableview.setItems(sports);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Gestion entropôt");
            alert.setHeaderText("Suppression des l'employés");
            alert.setContentText("Employer supprimé avec succès !");
            alert.showAndWait();

        }
    }

    /* @FXML
    private void ResearchRegime(ActionEvent event) {
        // Add the listener to the search field if it hasn't already been added
        if (!isListenerAdded) {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                tableview.getItems().clear();
                tableview.getItems().addAll(
                        sports.stream()
                                .filter(sports -> regime.getTitle().toLowerCase().contains(newValue.toLowerCase()))
                                .collect(Collectors.toList())
                );
            });
            isListenerAdded = true;
        }

        //Populate the table view with the initial list of sports
        tableview.getItems().clear();
        tableview.getItems().addAll(sports);
    }*/

    @FXML
    private void ResearchRegime(ActionEvent event) {
    }


   

  

    @FXML
    private void ajouterSportIcon(MouseEvent event) throws IOException {
        
            Parent root = FXMLLoader.load(getClass().getResource("/com/changemakers/atpeace/gui/AjouterSport.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
       
        
    }

}

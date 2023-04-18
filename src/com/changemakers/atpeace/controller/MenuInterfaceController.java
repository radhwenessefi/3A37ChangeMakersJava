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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
public class MenuInterfaceController implements Initializable {

    @FXML
    private TableView<Regime> tableview;
    @FXML
    private TableColumn<Regime, String> titre;
    @FXML
    private TableColumn<Regime, String> desc;
    @FXML
    private TableColumn<Regime, String> liste;
    @FXML
    private TableColumn<Regime, String> image;
    @FXML
    private TableColumn<Regime, String> level;
    ObservableList<Regime> regimes = FXCollections.observableArrayList();
    ObservableList<Regime> allusers;
    ObservableList<Regime> selectedregime;
    @FXML
    private AnchorPane fifRech;
   
    private boolean isListenerAdded = false;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controleRegime controle = new controleRegime();

        List<Regime> listemps = null;
        try {
            listemps = controle.AfficheToutRegime();
        } catch (SQLException ex) {
            Logger.getLogger(MenuInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!listemps.isEmpty()) {
            for (int i = 0; i < listemps.size(); i++) {
                regimes.add(listemps.get(i));
            }
        }
        titre.setCellValueFactory(new PropertyValueFactory<Regime, String>("title"));
        desc.setCellValueFactory(new PropertyValueFactory<Regime, String>("discription"));
        liste.setCellValueFactory(new PropertyValueFactory<Regime, String>("liste_alement"));
        image.setCellValueFactory(new PropertyValueFactory<Regime, String>("image"));
        level.setCellValueFactory(new PropertyValueFactory<Regime, String>("level"));

        //tableview.setItems(regimes);
         // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Regime> filteredData = new FilteredList<>(regimes, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(re -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (re.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (re.getDiscription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (re.getListe_alement().indexOf(lowerCaseFilter)!=-1){
				     return true;
                                } else if (re.getLevel().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Regime> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableview.setItems(sortedData);
               
        
        
    
    }

    @FXML
    private void ajouterRegimeIcon(MouseEvent event) {
        

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/changemakers/atpeace/gui/ajouterRegime.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateRegime(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/changemakers/atpeace/gui/modifer_regime.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Modifer_regimeController modifier = loader.getController();
        modifier.initdata((Regime) tableview.getSelectionModel().getSelectedItems().get(0));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deleteOneRegime(MouseEvent event) throws SQLException {
        
        controleRegime controle = new controleRegime();
        selectedregime = tableview.getSelectionModel().getSelectedItems();
        if (selectedregime.size() > 0) {
            for (Regime u : selectedregime) {
                controle.SupprimerRegime(u);
            }
            regimes.clear();
            List<Regime> listemps = controle.AfficheToutRegime();
            for (int i = 0; i < listemps.size(); i++) {
                regimes.add(listemps.get(i));
            }
            tableview.setItems(regimes);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Gestion entropôt");
            alert.setHeaderText("Suppression des l'employés");
            alert.setContentText("Employer supprimé avec succès !");
            alert.showAndWait();

        }
    }



}

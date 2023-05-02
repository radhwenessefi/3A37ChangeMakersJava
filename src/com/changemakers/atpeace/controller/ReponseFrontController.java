/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entities.RendezVous;
import com.changemakers.atpeace.entities.Reponse;
import com.changemakers.atpeace.entities.Session;
import com.changemakers.atpeace.services.ReponseCrud;
import com.changemakers.atpeace.services.SessionService;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */

public class ReponseFrontController implements Initializable {
    Session s =new  Session();
    @FXML
    private TableView<Reponse> tableviewReponse;
    @FXML
    private TableColumn<Reponse, String> idRecColumn;
    @FXML
    private TableColumn<Reponse, String> solutionReponseColumn;
    @FXML
    private TableColumn<Reponse, Date> dateReponseColumn;
    Date date = null;
    

    ObservableList<Reponse> repon = FXCollections.observableArrayList();
    @FXML
    private AnchorPane fifRech;
    @FXML
    private TextField searchField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       SessionService ss = new SessionService();
       s = ss.ConnectUser();
    // Récupération des reponses
    ReponseCrud rc = new ReponseCrud();
    List<Reponse> listR = null;
    List<Reponse> reponses = null;
    listR = rc.ChercherReponse(s.getId_user());
    ObservableList<Reponse> observableList = FXCollections.observableArrayList(listR);

    if (!listR.isEmpty()) {
        for (int i = 0; i < listR.size(); i++) {
            repon.add(listR.get(i));
        }
    }
    //while
    //idRecColumn.setCellValueFactory(new PropertyValueFactory<>("reclamation_id"));
    idRecColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reponse, String>, ObservableValue<String>>() {
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Reponse, String> r) {
        String nomPrenomPatient = r.getValue().getR().getDescription_reclamation();
        return new SimpleStringProperty(nomPrenomPatient);
    }
});
    solutionReponseColumn.setCellValueFactory(new PropertyValueFactory<>("solution_reponse"));
    dateReponseColumn.setCellValueFactory(new PropertyValueFactory<>("date_reponse"));
    
    tableviewReponse.setItems(repon);

    }   
       private Parent root;
    private Stage stage;
    private Scene scene;
    public void switchToBack(ActionEvent event) {
        //String username = nameTextField.getText();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/ajouterRecFront.fxml"));	
        try {	
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(reponsesceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		//reponsesceneController MenuInterfaceController = loader.getController();
		//MenuInterfaceController.;
        
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();             
    } 

    @FXML
    private void add(MouseEvent event) {
        //String username = nameTextField.getText();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/ajouterRecFront.fxml"));	
        try {	
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(reponsesceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		//reponsesceneController MenuInterfaceController = loader.getController();
		//MenuInterfaceController.;
        
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();     
    }
    
    
}

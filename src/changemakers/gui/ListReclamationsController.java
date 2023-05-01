/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package changemakers.gui;

import changemakers.entities.Reclamation;
import changemakers.services.ReclamationCrud;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ListReclamationsController implements Initializable {
@FXML
    private ImageView ajtButton;
   
    @FXML
    private TableView<Reclamation> tableview;
    @FXML
    private TableColumn<Reclamation, String> tiColumn;
    @FXML
    private TableColumn<Reclamation, String> tyColumn;
    @FXML
    private TableColumn<Reclamation, String> etColumn;
    @FXML
    private TableColumn<Reclamation, String> desColumn;
    @FXML
    private TableColumn<Reclamation, Date> daColumn;
    @FXML
    private AnchorPane fifRech;
    @FXML
    private TextField searchField;
    @FXML
    private Button backButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         // Récupération des réclamations
        ReclamationCrud rc = new ReclamationCrud();
        List<Reclamation> listR = null;
        listR = rc.ChercherReclamation();
        ObservableList<Reclamation> reclam = FXCollections.observableArrayList();
        if (!listR.isEmpty()) {
            for (int i = 0; i < listR.size(); i++) {
                reclam.add(listR.get(i));

            }
        }

        //while
       
        tiColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("titre_reclamation"));
        tyColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("type_reclamation"));
        etColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("etat_reclamation"));
        desColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description_reclamation"));
        daColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date"));
        tableview.setItems(reclam);
        
    }  
    /*public void remplirTableView(){
        // Récupération des réclamations
        ReclamationCrud rc = new ReclamationCrud();
        List<Reclamation> listR = null;
        listR = rc.ChercherReclamation();
        ObservableList<Reclamation> reclam = FXCollections.observableArrayList();
        if (!listR.isEmpty()) {
            for (int i = 0; i < listR.size(); i++) {
                reclam.add(listR.get(i));

            }
        }

        //while
       
        tiColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("titre_reclamation"));
        tyColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("type_reclamation"));
        etColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("etat_reclamation"));
        desColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description_reclamation"));
        daColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date"));
        tableview.setItems(reclam);
    }*/
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    public void switchToBack(ActionEvent event) {
        //String username = nameTextField.getText();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterRecFront.fxml"));	
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
        
   
    String titreRecToDelete;
    Reclamation r = new Reclamation();
    
  /*  public void refresh(TableView<Reclamation> t) {
    ReclamationCrud rc = new ReclamationCrud();
    List<Reclamation> listR = rc.ChercherReclamation();
    ObservableList<Reclamation> dataR = FXCollections.observableArrayList(listR);
    t.setItems(dataR);
}*/
    public void refresh(TableView t) {
        ObservableList<Reclamation> dataR = FXCollections.observableArrayList();
        ReclamationCrud rc = new ReclamationCrud();
        List<Reclamation> listR = null;
        listR = rc.ChercherReclamation();

        if (!listR.isEmpty()) {
            for (int i = 0; i < listR.size(); i++) {
                dataR.add(listR.get(i));
            }
        }

        t.getItems().setAll(dataR);
        t.refresh();
    }
    public void Select(MouseEvent event) {
        r = tableview.getSelectionModel().getSelectedItem();
        //int  num = tableview.getSelectionModel().getSelectedIndex();

        if (r != null) {
            
            
            titreRecToDelete=r.getTitre_reclamation();
          
        }

    }
    public void dropItems(ActionEvent event) {

            ReclamationCrud rs = new ReclamationCrud();
       
            rs.supprimerReclamation(titreRecToDelete);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Reclamation Suppriméé.");
            alert.showAndWait();
            refresh(tableview);
            
    }

    @FXML
    private void add(javafx.scene.input.MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterRecFront.fxml"));	
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

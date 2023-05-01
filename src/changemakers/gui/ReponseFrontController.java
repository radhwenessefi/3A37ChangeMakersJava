/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package changemakers.gui;

import changemakers.entities.Reponse;
import changemakers.services.ReponseCrud;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */

public class ReponseFrontController implements Initializable {
    
    @FXML
    private TableView<Reponse> tableviewReponse;
    @FXML
    private TableColumn<Reponse, Integer> idRecColumn;
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
       
    // Récupération des reponses
    ReponseCrud rc = new ReponseCrud();
    List<Reponse> listR = null;
    List<Reponse> reponses = null;
    listR = rc.ChercherReponse();
    ObservableList<Reponse> observableList = FXCollections.observableArrayList(listR);

    if (!listR.isEmpty()) {
        for (int i = 0; i < listR.size(); i++) {
            repon.add(listR.get(i));
        }
    }
    /*tableviewReponse.setOnMouseClicked(event -> {
    if (event.getClickCount() == 1) {
        Select();
    }
    });
*/
    //while
    idRecColumn.setCellValueFactory(new PropertyValueFactory<>("reclamation_id"));
    solutionReponseColumn.setCellValueFactory(new PropertyValueFactory<>("solution_reponse"));
    dateReponseColumn.setCellValueFactory(new PropertyValueFactory<>("date_reponse"));
    
    tableviewReponse.setItems(repon);

    }   
       private Parent root;
    private Stage stage;
    private Scene scene;
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

    @FXML
    private void add(MouseEvent event) {
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
    
    
}

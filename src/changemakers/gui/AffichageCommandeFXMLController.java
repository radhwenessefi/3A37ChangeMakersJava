/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package changemakers.gui;

import changemakers.entities.Commande;
import changemakers.entities.Produit;
import changemakers.services.CommandeCRUD;
import changemakers.services.ProduitCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author zaiir
 */
public class AffichageCommandeFXMLController implements Initializable {

    @FXML
    private BorderPane mainPane;
    @FXML
    private Button ao_btn_dashboard;
    @FXML
    private Button ao_btn_manage_users;
    @FXML
    private Button ao_btn_manage_users1;
    @FXML
    private Button ao_btn_manage_users11;
    @FXML
    private Button ao_btn_manage_users12;
    @FXML
    private Button ao_btn_manage_users13;
    @FXML
    private Button ao_btn_manage_users131;
    @FXML
    private TableView<Commande> Comtable;
    @FXML
    private TableColumn<Commande, Integer> idTab;
    @FXML
    private TableColumn<Commande, Float> fraisab;
    @FXML
    private TableColumn<Commande, Date> datetab;
    @FXML
    private TableColumn<Commande, String> etattab;
    @FXML
    private TableColumn<Commande, Integer> quantitetab;
    @FXML
    private TableColumn<Commande, ?> Actiontab;
    
    private ObservableList<Commande> CommandeList;
    
    private CommandeCRUD cc;
    
    
    
    @FXML
    private Button cmdajout;
    @FXML
    private Button suppcmd;
    @FXML
    private Button cmdmodif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*
         cc = new CommandeCRUD();
        
      try {
          CommandeList = FXCollections.observableArrayList(cc.selectAll());
      } catch (SQLException ex) {
          Logger.getLogger(AffichageProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
      }

        idTab.setCellValueFactory(new PropertyValueFactory<>("frais_commande"));
        fraisab.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
        datetab.setCellValueFactory(new PropertyValueFactory<>("etat"));
        etattab.setCellValueFactory(new PropertyValueFactory<>("quantite_livre"));
     
      
        
        Comtable.setItems(CommandeList);
    */
        
    cc = new CommandeCRUD();
        
    try {
        CommandeList = FXCollections.observableArrayList(cc.selectAll());
    } catch (SQLException ex) {
        Logger.getLogger(AffichageProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }

    idTab.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
    fraisab.setCellValueFactory(new PropertyValueFactory<>("frais_commande"));
    datetab.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
    etattab.setCellValueFactory(new PropertyValueFactory<>("etat"));
    quantitetab.setCellValueFactory(new PropertyValueFactory<>("quantite_livre"));
    
    Comtable.setItems(CommandeList);

    // Add a listener to the selection model of the table
    Comtable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            try {
                // Call a method to delete the selected command
                deleteCommand(newSelection);
            } catch (SQLException ex) {
                Logger.getLogger(AffichageCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
}

// Method to delete the selected command
private void deleteCommand(Commande command) throws SQLException {
    // Get the ID of the selected command
    int id = command.getId_commande();
    
    // Delete the command from the database using the CommandeCRUD class
    cc.supprimerCommande(id);
    
    // Remove the command from the table view
    CommandeList.remove(command);


   
       
    }    

    @FXML
    private void GoToDashboard(ActionEvent event) {
    }

    @FXML
    private void GoToManagUsers(ActionEvent event) {
    }

    @FXML
    private void GoToManagProd(ActionEvent event) {
    }

    @FXML
    private void GoToManagMusic(ActionEvent event) {
    }

    @FXML
    private void GoToManagSport(ActionEvent event) {
    }

    @FXML
    private void GoToManagReclamation(ActionEvent event) {
    }

    @FXML
    private void GoToManagBlog(ActionEvent event) {
    }



    @FXML
    private void ajoutcmd(ActionEvent event) {
        
    }

    @FXML
    private void supprimercmd(ActionEvent event) {
    }

    @FXML
    private void modifiercmd(ActionEvent event) {
    }

   

        /*private void supprimerPROD(ActionEvent event) throws IOException, SQLException  {
     
       
        Interfaces.pm = Comtable.getSelectionModel().getSelectedItem();
           CommandeCRUD cc = new CommandeCRUD();
           ObservableList EV = cc.selectAll();
            
           
        cc.supprimerCommande(Interfaces.pm.getId_commande());
	 ObservableList PM = cc.selectAll();
           idTab.setCellValueFactory(new PropertyValueFactory <Commande, Float>("frais_commande"));
        fraisab.setCellValueFactory(new PropertyValueFactory<Commande,Date>("date_livraison"));
        datetab.setCellValueFactory(new PropertyValueFactory<Commande,String>("etat"));
        etattab.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("quantite_livre"));
       
	Comtable.setItems(PM); 
                     Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("commande Supprimé Correctement")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();

        }*/
    
}

   
    


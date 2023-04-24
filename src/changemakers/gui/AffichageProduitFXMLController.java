/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package changemakers.gui;


import changemakers.entities.Produit;
import changemakers.services.ProduitCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author zaiir
 */
public class AffichageProduitFXMLController implements Initializable {

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
    
    private Label labelAffich;
    @FXML
    private TableView<Produit> PMtable;
    @FXML
    private TableColumn<Produit, String> nomProdtab;
    @FXML
    private TableColumn<Produit, Float> Prixtab;
    @FXML
    private TableColumn<Produit, Integer> Pquantitetab;
    @FXML
    private TableColumn<Produit, String> PMtypetab;
    @FXML
    private TableColumn<Produit, String> imagetab;
    @FXML
    private TableColumn<Produit, String> descriptionprix;
  
   
    private ObservableList<Produit> ProduitList;
    
    private ProduitCRUD pc;
    @FXML
    private TableColumn<?, ?> Actiontab;
    @FXML
    private TextField PMcherchertab;
    @FXML
    private Button Pajoutertab;
    @FXML
    private Button suppPM;
    @FXML
    private Button Pmodifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
        pc = new ProduitCRUD();
        
      try {
          ProduitList = FXCollections.observableArrayList(pc.selectAll());
      } catch (SQLException ex) {
          Logger.getLogger(AffichageProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        nomProdtab.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom_produit"));
        Prixtab.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix_produit"));
        Pquantitetab.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("quantite_produit"));
        
        imagetab.setCellValueFactory(new PropertyValueFactory<Produit, String>("image_produit"));
        descriptionprix.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        
        PMtable.setItems(ProduitList);
        
            PMtable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            try {
                // Call a method to delete the selected command
                deleteProduit(newSelection);
            } catch (SQLException ex) {
                Logger.getLogger(AffichageCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
 try {
            
        
        FilteredList<Produit> filteredData = new FilteredList<>(ProduitList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		PMcherchertab.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(produit -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (produit.getNom_produit().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (produit.getCategorie_produit().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                               
                               
                              
				    
				     
				else return String.valueOf(produit.getNom_produit()).contains(lowerCaseFilter); // Does not match.
                                
	});
		});
                SortedList<Produit> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(PMtable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		PMtable.setItems(sortedData);
        }
         catch (Exception e) {
                 System.out.println(e.getMessage());
                 
       
        
     
       
       
        // TODO
    }    

     }    
   
    
    
    // Method to delete the selected command
private void deleteProduit(Produit produit) throws SQLException {
    // Get the ID of the selected command
    int id = produit.getId_produit();
    
    // Delete the command from the database using the CommandeCRUD class
    pc.supprimerProduit(id);
    
    // Remove the command from the table view
    ProduitList.remove(produit);


   
       
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
    private void AjoutP(ActionEvent event) throws IOException {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajoutp.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
          }
    }
    
  

    


 



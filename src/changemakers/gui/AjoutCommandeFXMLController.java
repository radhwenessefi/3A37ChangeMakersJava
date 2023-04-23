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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zaiir
 */
public class AjoutCommandeFXMLController implements Initializable {

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
    private TextField tprix;
    @FXML
    private TextField tetat;
    @FXML
    private TextField tquantiteliv;
    @FXML
    private DatePicker tdate;
    
    
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
    @FXML
    private ComboBox<?> etatCom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ChoisirEtat();
         cc = new CommandeCRUD();
        
    try {
        CommandeList = FXCollections.observableArrayList(cc.selectAll());
    } catch (SQLException ex) {
        Logger.getLogger(AffichageProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }

    idTab.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
    fraisab.setCellValueFactory(new PropertyValueFactory<>("frais_commande"));
    datetab.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
  
    
      etattab.setCellValueFactory(cellData -> {
            StringProperty selectedEtatProperty = new SimpleStringProperty(cellData.getValue().getEtat());
            String selectedEtat = (String) etatCom.getSelectionModel().getSelectedItem();
            if (selectedEtat != null) {
                selectedEtatProperty.set(selectedEtat);
            }
            return selectedEtatProperty;
        });
    /*quantitetab.setCellValueFactory(new PropertyValueFactory<>("quantite_livre"));*/
    
    Comtable.setItems(CommandeList);

    

       
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
    /*private void AjoutCom(ActionEvent event) throws IOException {
       
    if (tdate.getValue() == null || tetat.getText().isEmpty() || tquantiteliv.getText().isEmpty() || tprix.getText().isEmpty()) {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setTitle("Erreur de données");
        al.setContentText("Veuillez vérifier les données !");
        al.show();
    } else {
        LocalDate dateLivraisonLocal = tdate.getValue();
        LocalTime time = LocalTime.of(0, 0); // Heure par défaut à 00:00
        LocalDateTime dateTime = LocalDateTime.of(dateLivraisonLocal, time);
        Timestamp dateLivraison = Timestamp.valueOf(dateTime);

        Commande c = new Commande(Float.parseFloat(tprix.getText()), dateLivraison, tetat.getText(), Integer.parseInt(tquantiteliv.getText()));
        CommandeCRUD cc = new CommandeCRUD();
        
        try {
            cc.ajouterCommande(c);
        } catch (SQLException ex) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Erreur de données");
            al.setContentText(ex.getMessage());
            al.show();
        }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageCommandeFXML.fxml"));
             Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            }
        }
    }*/
    private void AjoutCom(ActionEvent event) throws IOException {
       
    if (tdate.getValue() == null   || tprix.getText().isEmpty()) {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setTitle("Erreur de données");
        al.setContentText("Veuillez vérifier les données !");
        al.show();
    } else {
        LocalDate dateLivraisonLocal = tdate.getValue();
        LocalTime time = LocalTime.of(0, 0); // Heure par défaut à 00:00
        LocalDateTime dateTime = LocalDateTime.of(dateLivraisonLocal, time);
        Timestamp dateLivraison = Timestamp.valueOf(dateTime);

       
        
         String selectedEtat = (String) etatCom.getSelectionModel().getSelectedItem();
        Commande c = new Commande(Float.parseFloat(tprix.getText()), dateLivraison, selectedEtat);
        CommandeCRUD cc = new CommandeCRUD();
        
   

        try {
            cc.ajouterCommande(c);
        } catch (SQLException ex) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Erreur de données");
            al.setContentText(ex.getMessage());
            al.show();
        }
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCommandeFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
          }
            
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
 String listEtat[] = {"Livré", "Non Livré"};
    @FXML
    private void ChoisirEtat() {
   

        List<String> listS = new ArrayList<>();

        for (String data : listEtat) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        etatCom.setItems(listData);

    
    }
    }


        /*
             if (tetat.getText().isEmpty() ) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Veuillez verifier les données !");
            al.show();
        }else{
            Commande c = new Commande(Float.parseFloat(tprix.getText()),tdate.getValue(),tetat.getText(),Integer.parseInt(tquantiteliv.getText()));
      
            CommandeCRUD cc = new CommandeCRUD();
            
            try {
                cc.ajouterCommande(c);
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur de donnee");
                al.setContentText(ex.getMessage());
                al.show();
            }
            
        }*/
    
    


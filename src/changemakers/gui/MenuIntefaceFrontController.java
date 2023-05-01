/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package changemakers.gui;

import changemakers.entities.Reclamation;
import changemakers.services.ReclamationCrud;
import static changemakers.utils.SMS.ACCOUNT_SID;
import static changemakers.utils.SMS.AUTH_TOKEN;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class MenuIntefaceFrontController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
 
    

    
    @FXML
    private TextField tftitre;
     @FXML
    private DatePicker cdate;
    @FXML
    private TextArea tadescription;
    @FXML
    private ComboBox<String> cbtype;
    
    Date date = null;
    int id = 0;

    @FXML
    private Button voirReclamations;
    @FXML
    private Label lbt;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         // Remplissage du combo box de type
        List<String> elements = new ArrayList<String>();
        elements.add("Medecin");
        elements.add("Blog");
        elements.add("Boutique");
        elements.add("Musique");
        elements.add("Autres");

        cbtype.getItems().addAll(elements);
    }   
    @FXML
    private void ajouter(ActionEvent event) {
        String titre = tftitre.getText();
        String type = cbtype.getValue();
        LocalDate localdate = cdate.getValue();
        LocalDate nowday = LocalDate.now();

        String description = tadescription.getText();
        //    public Reclamation(String titre_reclamation, String type_reclamation, String etat_reclamation, String description_reclamation, Date date) {
        if (localdate == null || localdate.compareTo(nowday) < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La date ne peut être vide et doit être ultérieure à celle d'aujourd'hui");
            alert.showAndWait();
        } else if (type.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Selectionner un type");
            alert.showAndWait();
        } else if ((titre.isEmpty()) || (!Character.isUpperCase(titre.charAt(0)) && !titre.matches("^[a-zA-Z0-9]+$"))) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le titre ne peut être vide et doit contenir que des lettres ou des chiffres.");
            alert.showAndWait();
        } else if ((description.isEmpty()) || (!Character.isUpperCase(description.charAt(0)) && description.length() <= 3)) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "La description ne peut être vide et doit plus de trois lettres et des chiffres.");
            alert.showAndWait();
        } else {
            Instant instant = Instant.from(localdate.atStartOfDay(ZoneId.systemDefault()));
            date = Date.from(instant);
            Reclamation r = new Reclamation(01, titre, type, "Non Traité", description, date);
            ReclamationCrud rc = new ReclamationCrud();
            rc.ajouterReclamation(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Reclamation Ajoutéé.");
            alert.showAndWait();
          
            cbtype.setValue(null);
            tftitre.clear();
            tadescription.clear();
            cdate.setValue(null);
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        com.twilio.rest.api.v2010.account.Message message;
        message = com.twilio.rest.api.v2010.account.Message
                .creator(
                        new PhoneNumber("+21658310144"),
                        new PhoneNumber("+16813233319"),
                        "Votre reclamation à été recu, on vous repondra dans les plus brefs délais"
                )
                .create();

        System.out.println(message.getSid());
        }
        

    }
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    public void switchToListReclamations(ActionEvent event) {
        //String username = nameTextField.getText();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("listReclamationsFront.fxml"));	
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
    public void switchToListReponse(ActionEvent event){
        //String username = nameTextField.getText();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("listReponseFront.fxml"));	
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

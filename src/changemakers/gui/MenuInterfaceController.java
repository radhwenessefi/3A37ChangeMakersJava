/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package changemakers.gui;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import changemakers.entities.Reclamation;
import changemakers.entities.Reponse;
import changemakers.services.ReclamationCrud;
import static changemakers.utils.SMS.ACCOUNT_SID;
import static changemakers.utils.SMS.AUTH_TOKEN;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.awt.Desktop.Action;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sami
 */
public class MenuInterfaceController implements Initializable {

    /*
      private int id_reclamation;
    private String titre_reclamation;
    private String type_reclamation;
    private String etat_reclamation;
    private String description_reclamation;
    private Date date;
     */
    @FXML
    private TableView<Reclamation> tableview;
    @FXML
    private TableColumn<Reclamation, Integer> idColumn;
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

    ObservableList<Reclamation> reclam = FXCollections.observableArrayList();
    @FXML
    private Label tbusername;
    @FXML
    private TextField tftitre;
    @FXML
    private DatePicker cdate;
    /* @FXML
    private Button btnajouter;
    @FXML
    private Button btmodifier;
    @FXML
    private Button btnsupprimer;*/
    @FXML
    private TextArea tadescription;
    @FXML
    private ComboBox<String> cbtype;
    
    Date date = null;
    int id = 0;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField search;
    @FXML
    private Button verify;
    @FXML
    private Button sortButton;
    @FXML
    private Button sortbytypeButton;
    @FXML
    private Button repondre;
    Reclamation r = new Reclamation();
    /**
     * Initializes the controller class.
     */
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

        // Récupération des réclamations
        ReclamationCrud rc = new ReclamationCrud();
        List<Reclamation> listR = null;
        listR = rc.ChercherReclamation();

        if (!listR.isEmpty()) {
            for (int i = 0; i < listR.size(); i++) {
                reclam.add(listR.get(i));

            }
        }

        //while
        idColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id_reclamation"));
        tiColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("titre_reclamation"));
        tyColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("type_reclamation"));
        etColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("etat_reclamation"));
        desColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description_reclamation"));
        daColumn.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date"));
        tableview.setItems(reclam);
        // ListR = ReclamationCrud
        ObservableList<Reclamation> observableList = FXCollections.observableArrayList(listR);

        FilteredList<Reclamation> filteredData = new FilteredList<Reclamation>(observableList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(re -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (re.getTitre_reclamation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches titre.
                } else if (re.getDescription_reclamation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches description.
                } /*else if (re.getType_reclamation().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }*/ else if (new SimpleDateFormat("yyyy-MM-dd").format(re.getDate()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches date.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Reclamation> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableview.setItems(sortedData);
        
        sortButton.setOnAction(event -> {
            sortReclamation();
        });
        sortbytypeButton.setOnAction(event -> {
            sortReclamationByType();
        });
        

    }

    /*public void refresh(TableView t) {
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
    }*/
    public void refresh(TableView<Reclamation> t) {
    ReclamationCrud rc = new ReclamationCrud();
    List<Reclamation> listR = rc.ChercherReclamation();
    ObservableList<Reclamation> dataR = FXCollections.observableArrayList(listR);
    t.setItems(dataR);
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
            refresh(tableview);
            cbtype.setValue(null);
            tftitre.clear();
            tadescription.clear();
            cdate.setValue(null);
        }

    }
    String titreRecToDisplay;
    String EtatReclamation;
    
    
    //Suppresion d'une réclamation
    @FXML
    public void Select() {
        r = tableview.getSelectionModel().getSelectedItem();
        //int  num = tableview.getSelectionModel().getSelectedIndex();

        if (r != null) {
            tftitre.setText(r.getTitre_reclamation());
            tadescription.setText(r.getDescription_reclamation());
            id = r.getId_reclamation();
            titreRecToDisplay=r.getTitre_reclamation();
            
          
        }

    }
    @FXML
    private void dropItems(ActionEvent event) {

        ReclamationCrud rs = new ReclamationCrud();
        if (tftitre.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le titre est vide .");
            alert.showAndWait();
        } else {
            rs.supprimerReclamation(tftitre.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Reclamation Suppriméé.");
            alert.showAndWait();
            refresh(tableview);
            cbtype.setValue(null);
            tftitre.clear();
            tadescription.clear();
            cdate.setValue(null);

        }

    }

    @FXML
    private void UpdateItems(ActionEvent event) {

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
            Reclamation r = new Reclamation(id, titre, type, "Non Traité", description, date);
            ReclamationCrud rc = new ReclamationCrud();
            rc.updateReclamation(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Reclamation Modifiéé.");
            alert.showAndWait();
            refresh(tableview);
            cbtype.setValue(null);
            tftitre.clear();
            tadescription.clear();
            cdate.setValue(null);
        }

    }

    @FXML
    public void sortReclamation() {
        ObservableList<Reclamation> data = tableview.getItems();
        if (!data.isEmpty()) {
            List<Reclamation> sortedList = new ArrayList<>(data);
            sortedList.sort(Comparator.comparing(Reclamation::getDate));
            ObservableList<Reclamation> sortedData = FXCollections.observableArrayList(sortedList);
            tableview.setItems(sortedData);
        }
    }

    @FXML
    private void sortReclamationByType() {
        // Get the current sorting order of the tableview
        ObservableList<TableColumn<Reclamation, ?>> sortOrder = tableview.getSortOrder();

        // Clear the sorting order so that we can sort by a different column
        tableview.getSortOrder().clear();

        // Sort the tableview by type
        TableColumn<Reclamation, String> typeColumn = tyColumn;
        tableview.getSortOrder().add(typeColumn);

        // If the tableview was already sorted by type, reverse the order
        if (sortOrder.contains(typeColumn)) {
            typeColumn.setSortType(TableColumn.SortType.DESCENDING);
            tableview.sort();
            typeColumn.setSortType(TableColumn.SortType.ASCENDING);
        } else {
            typeColumn.setSortType(TableColumn.SortType.ASCENDING);
            tableview.sort();
        }
    }

    @FXML
    private void EnvoyerSMS(ActionEvent event) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        com.twilio.rest.api.v2010.account.Message message;
        message = com.twilio.rest.api.v2010.account.Message
                .creator(
                        new PhoneNumber("+21658310144"),
                        new PhoneNumber("+16813233319"),
                        "Votre reclamation à été validé"
                )
                .create();

        System.out.println(message.getSid());
    }
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    public void switchToReponse(ActionEvent event) {
        r.setEtat_reclamation("Traité");
         FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuInterface_1.fxml"));
    try {
        root = loader.load();
    } catch (IOException ex) {
        Logger.getLogger(MenuInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
    }

    reponsesceneController reponsesceneController = loader.getController();
    reponsesceneController.setTitre(titreRecToDisplay,id);
    
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
                
    }   
    public String ChercherReclamation(int id_reclamation) {
     
    // loop through the rows of the table view to find the reclamation with the given id
    for (int i = 0; i < tableview.getItems().size(); i++) {
        // get the id and titre of the current row
        int id = tableview.getItems().get(i).getId_reclamation();
        String titre = tableview.getItems().get(i).getTitre_reclamation();
        
        // if the id matches the given id, return the titre
        if (id == id_reclamation) {
            return titre;
        }
    }
    
    // if no matching reclamation was found, return null
    return null;
}

  



}

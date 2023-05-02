/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entities.Reponse;
import com.changemakers.atpeace.services.ReponseCrud;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.text.Document;
import static jdk.nashorn.internal.objects.NativeString.search;

/**
 *
 * @author LENOVO
 */
public class reponsesceneController implements Initializable {
    @FXML
    private Button exportButton;
    @FXML
    private TableView<Reponse> tableviewReponse;
    @FXML
    private TableColumn<Reponse, Integer> idRecColumn;
    @FXML
    private TableColumn<Reponse, String> solutionReponseColumn;
    @FXML
    private TableColumn<Reponse, Date> dateReponseColumn;
    
    

    ObservableList<Reponse> repon = FXCollections.observableArrayList();

    @FXML
    private Label tbusername;
    @FXML
    private TextField tftitre;
    @FXML
    private TextField idReclamationf;
    @FXML
    private TextArea solutionfield;
    @FXML
    private DatePicker reponsedatefield;
    
    @FXML
    private TextField search;
    @FXML
        private Button sortButton;
    Date date = null;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button retourRec;
    
   
@Override
public void initialize(URL url, ResourceBundle rb) {
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
    tableviewReponse.setOnMouseClicked(event -> {
    if (event.getClickCount() == 1) {
        Select();
    }
    });

    //while
    idRecColumn.setCellValueFactory(new PropertyValueFactory<>("reclamation_id"));
    solutionReponseColumn.setCellValueFactory(new PropertyValueFactory<>("solution_reponse"));
    dateReponseColumn.setCellValueFactory(new PropertyValueFactory<>("date_reponse"));
    
    tableviewReponse.setItems(repon);
    FilteredList<Reponse> filteredData = new FilteredList<Reponse>(observableList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(re -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (re.getSolution_reponse().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
                                }else if (new SimpleDateFormat("yyyy-MM-dd").format(re.getDate_reponse()).indexOf(lowerCaseFilter) != -1) {
                                      return true; // Filter matches date.
                                }  
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reponse> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableviewReponse.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableviewReponse.setItems(sortedData);
    sortButton.setOnAction(event -> {
    sortReponse();
    });
    
    
}


   /* public void refresh(TableView t) {
        ObservableList<Reponse> dataR = FXCollections.observableArrayList();
        ReponseCrud rc = new ReponseCrud();
        List<Reponse> listR = null;
        listR = rc.ChercherReponse();

        if (!listR.isEmpty()) {
            for (int i = 0; i < listR.size(); i++) {
                dataR.add(listR.get(i));
            }
        }

        t.getItems().setAll(dataR);
        t.refresh();
    }*/
    public void refresh(TableView<Reponse> t) {
    ReponseCrud rc = new ReponseCrud();
    List<Reponse> listR = rc.ChercherReponse();
    ObservableList<Reponse> dataR = FXCollections.observableArrayList(listR);
    t.setItems(dataR);
}

    @FXML
    private void ajouterReponseScene(ActionEvent event) throws SQLException {
        String idRec= idReclamationf.getText();
        String stringNumber = idRec;
        int IDjointu = Integer.parseInt(stringNumber);//int of the textfield idReclamationf
        LocalDate localdate = reponsedatefield.getValue();
        LocalDate nowday = LocalDate.now();
        String solution = solutionfield.getText();
        

        //    public Reclamation(String titre_reclamation, String type_reclamation, String etat_reclamation, String description_reclamation, Date date) {
        if (localdate == null || localdate.compareTo(nowday) < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La date ne peut être vide et doit être ultérieure à celle d'aujourd'hui");
            alert.showAndWait();
        } else if ((solution.isEmpty()) || (!Character.isUpperCase(solution.charAt(0)) && solution.length() <= 3)) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "La solution ne peut être vide et doit plus de trois lettres et des chiffres.");
            alert.showAndWait();
        } else {
            Instant instant = Instant.from(localdate.atStartOfDay(ZoneId.systemDefault()));
            date = Date.from(instant);
            //Reponse r = new Reponse(date, solution);
            Reponse r1=new Reponse(date,solution,IDjointu);
            ReponseCrud rc = new ReponseCrud();
            rc.ajouterReponse(r1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Reponse Ajoutéé.");
            alert.showAndWait();
            refresh(tableviewReponse);

            solutionfield.clear();
            reponsedatefield.setValue(null);
        }

    }
    private int idReponse;
    private int numPages=5;

    @FXML
    public void Select() {
        Reponse r = tableviewReponse.getSelectionModel().getSelectedItem();
        idReponse=r.getId_reponse();

        if (r != null) {
            solutionfield.setText(r.getSolution_reponse());
            idReclamationf.setText(null);
            tftitre.setText(null);

        }

    }

    @FXML
    private void dropItemsReponse(ActionEvent event) {

        ReponseCrud rs = new ReponseCrud();
        if (solutionfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La solution est vide, tu n'a rien selectionné .");
            alert.showAndWait();
        } else {
            rs.supprimerReponse(idReponse);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "réponse Supprimée.");
            alert.showAndWait();
            refresh(tableviewReponse);
            solutionfield.clear();
            reponsedatefield.setValue(null);

        }

    }

    @FXML
    private void UpdateItemsReponse(ActionEvent event) throws SQLException {

        LocalDate localdate = reponsedatefield.getValue();
        LocalDate nowday = LocalDate.now();
        String solution = solutionfield.getText();

        //    public Reclamation(String titre_reclamation, String type_reclamation, String etat_reclamation, String description_reclamation, Date date) {
        if (localdate == null || localdate.compareTo(nowday) < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La date ne peut être vide et doit être ultérieure à celle d'aujourd'hui");
            alert.showAndWait();
        } else if ((solution.isEmpty()) || (!Character.isUpperCase(solution.charAt(0)) && solution.length() <= 3)) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "La solution ne peut être vide et doit plus de trois lettres et des chiffres.");
            alert.showAndWait();
        } else {
            Instant instant = Instant.from(localdate.atStartOfDay(ZoneId.systemDefault()));
            date = Date.from(instant);
            Reponse r = new Reponse(idReponse, date, solution);
            ReponseCrud rc = new ReponseCrud();
            rc.updateReponse(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Reponse modifiée.");
            alert.showAndWait();
            refresh(tableviewReponse);
            solutionfield.clear();
            reponsedatefield.setValue(null);
        }

    }
    
@FXML
public void sortReponse() {
    ObservableList<Reponse> data = tableviewReponse.getItems();
    if (!data.isEmpty()) {
        List<Reponse> sortedList = new ArrayList<>(data);
        sortedList.sort(Comparator.comparing(Reponse::getDate_reponse));
        ObservableList<Reponse> sortedData = FXCollections.observableArrayList(sortedList);
        
        tableviewReponse.setItems(sortedData);
    }
}

private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    public void switchToReclamation(ActionEvent event) {
        //String username = nameTextField.getText();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/MenuInterfaceRe.fxml"));	
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
   public  void setTitre(String titre, int id ){
        tftitre.setText(titre);
        idReclamationf.setText(Integer.toString(id));
    }

}

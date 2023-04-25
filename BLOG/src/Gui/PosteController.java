package Gui;

import Entities.Poste;
import Services.PosteService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import static org.omg.PortableInterceptor.ObjectIdHelper.id;

public class PosteController {

    @FXML
    private TextField titreField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextArea imageField;

    @FXML
    private TableView<Poste> posteTable;

    @FXML
    private TableColumn<Poste, String> titreColumn;

    @FXML
    private TableColumn<Poste, String> descriptionColumn;

    @FXML
    private TableColumn<Poste, String> imageColumn;

    private final ObservableList<Poste> posteList = FXCollections.observableArrayList();
    private final PosteService posteService = new PosteService();
    private int id;

    @FXML
    public void initialize() {
        titreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitre()));
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        imageColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImage()));
        posteList.addAll(posteService.getAll());
        posteTable.setItems(posteList);
    }

    @FXML
    void addPoste() {
        Poste poste = new Poste();
        poste.setDescription(descriptionField.getText());
        poste.setImage(""); // set image to empty string for now
        posteService.addPoste(poste);
        posteList.add(poste);
        clearFields();
    }

    @FXML
    void updatePoste() {
        Poste poste = posteTable.getSelectionModel().getSelectedItem();
        if (poste != null) {
            poste.setTitre(titreField.getText());
            poste.setDescription(descriptionField.getText());
            posteService.updatePoste(poste, poste.getId());
            posteTable.refresh();
            clearFields();
        }
    }

    @FXML
    void deletePoste() {
        Poste poste = posteTable.getSelectionModel().getSelectedItem();
        if (poste != null) {
            posteService.deletePoste(poste.getTitre());
            posteList.remove(poste);
            clearFields();
        }
    }

    @FXML
    void showPoste() {
        Poste poste = posteTable.getSelectionModel().getSelectedItem();
        if (poste != null) {
            titreField.setText(poste.getTitre());
            descriptionField.setText(poste.getDescription());
            imageField.setText(poste.getImage());
        }
    }
    
    @FXML
    void searchPoste() {
        String titre = titreField.getText();
        if (!titre.isEmpty()) {
            ObservableList<Poste> filteredList = FXCollections.observableArrayList();
            filteredList.addAll(posteService.getById(id));
            posteTable.setItems(filteredList);
        } else {
            posteTable.setItems(posteList);
        }
    }

    private void clearFields() {
        titreField.clear();
        descriptionField.clear();
        imageField.clear();
    }
}

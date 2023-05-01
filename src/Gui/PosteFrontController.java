package Gui;

import Entities.Commentaire;
import Entities.Poste;
import Services.CommentaireService;
import Services.PosteService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PosteFrontController implements Initializable {

    private ObservableList<Commentaire> commentaireList = FXCollections.observableArrayList();

    @FXML
    private TextField titreField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField imageField;

    @FXML
    private TextField continueCommentaireField;

    @FXML
    private TableView<Commentaire> commentaireTable;

    @FXML
    private TableColumn<Commentaire, String> continueCommentaireColumn;

    
    
    
    
    
    
    private PosteService posteService = new PosteService();

    private CommentaireService commentaireService = new CommentaireService();

    private static Poste selectedPoste;

    int id_c;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
    //titreField.setText(selectedPoste.getTitre());
    //descriptionField.setText(selectedPoste.getDescription());
    //imageField.setText(selectedPoste.getImage());
    
    continueCommentaireColumn.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
    commentaireTable.setItems(commentaireList);
        }

@FXML
private void addCommentaire(ActionEvent event) throws SQLException {
   
    String commentaire = continueCommentaireField.getText();
    Commentaire c = new Commentaire(id_c, commentaire);
    CommentaireService sc = new CommentaireService();
    sc.insertOne1(c);
}


    void setSelectedPoste(Poste selectedPoste) {
       id_c=selectedPoste.getId();
               
    }
    
  @FXML
private void handleBackButtonAction(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("ListPoste.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}  




}

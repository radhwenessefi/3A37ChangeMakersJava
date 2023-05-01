package Gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entities.Commentaire;
import Entities.Poste;
import Services.CommentaireService;
import Utils.DbConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.stream.Collectors;



public class GestionCommentaireController implements Initializable {
    @FXML
    private TextField continueCommentaireField;
    @FXML
    private TextField continueCommentaireField2;
    @FXML
    private TextField posteIdField;
    @FXML
    private TableView<Commentaire> commentaireTable;
    @FXML
    private TableColumn<Commentaire, String> continueCommentaireColumn;
    @FXML
    private TableColumn<Commentaire, Integer> posteIdColumn;

    private CommentaireService commentaireService;
    
    ObservableList <Commentaire> commetaireList = FXCollections.observableArrayList(); 

    private static Commentaire selectedCommentaire;
    private int id_selected;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        commentaireService = new CommentaireService();

        continueCommentaireColumn.setCellValueFactory(new PropertyValueFactory<>("continue_commentaire"));
        posteIdColumn.setCellValueFactory(new PropertyValueFactory<>("poste_id"));

    }

    private void showCommentaireTable(List<Commentaire> commentaires) {
    ObservableList<Commentaire> observableList = FXCollections.observableArrayList(commentaires);
    commentaireTable.setItems(observableList);
}
    @FXML
private void deleteCommentaire(ActionEvent event) {
    Commentaire selectedCommentaire = commentaireTable.getSelectionModel().getSelectedItem();
    if (selectedCommentaire != null) {
        commentaireService.deleteCommentaire(selectedCommentaire.getId());
        showCommentaireTable(commentaireService.getAllCommentaires());
    } else {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("No Commentaire Selected");
        alert.setContentText("Please select a Commentaire in the table.");
        alert.showAndWait();
    }
}



    @FXML
private void handleRefresh(ActionEvent event) {
    commentaireTable.setItems(commetaireList);
}

    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

   
    @FXML
private void tableview_clicked(MouseEvent event) {
    selectedCommentaire = commentaireTable.getSelectionModel().getSelectedItem();
    int index = commentaireTable.getSelectionModel().getSelectedItem().getId();
    continueCommentaireField.setText(commentaireTable.getSelectionModel().getSelectedItem().getContinueCommentaire());
    //posteIdField.setText(String.valueOf(commentaireTable.getSelectionModel().getSelectedItem().getPoste_id()));
    id_selected = commentaireTable.getSelectionModel().getSelectedItem().getId();
}

@FXML
private void handleFilter(ActionEvent event) {
    String filterText = continueCommentaireField2.getText();
    if (!filterText.isEmpty()) {
        List<Commentaire> filteredCommentaires = commentaireService.getAllCommentaires().stream()
                .peek(commentaire -> {
                    if (commentaire.getContinueCommentaire().contains(filterText)) {
                        commentaire.setContinueCommentaire("****");
                    }
                })
                .collect(Collectors.toList());
        showCommentaireTable(filteredCommentaires);
    } else {
        showCommentaireTable(commentaireService.getAllCommentaires());
    }
}

    
    @FXML
    private void handleBan(ActionEvent event) throws IOException {
        
        
    }


}


/*
    @FXML
private void handleUpdate(ActionEvent event) {
int index = id_selected;
CommentaireService service = new CommentaireService();
Commentaire commentaire = new Commentaire(continueCommentaireField.getText(), poste_idField.getText());
if (!commentaire.getContinueCommentaire().isEmpty()) {
    try {
        service.updateCommentaire(commentaire, index);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setContentText("Commentaire updated");
        alert.show();

        // Refresh the table view with updated data
        commetaireList.clear();
        commetaireList.addAll(service.getAll());

    } catch (SQLException ex) {
        Logger.getLogger(GestionCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Failed to update commentaire");
        alert.show();
    }
} else {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setContentText("Please fill in all fields");
    alert.show();
}
}
*/


/*
@FXML
private void refresh_pressed()
{
    commetaireList = FXCollections.observableArrayList();
    Connection con = DbConnection.getInstance().getConnection();
    try {
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `Commentaire`");
        while (rs.next()) {
            Commentaire commentaire = new Commentaire(
                rs.getPosteId("poste_id"),
                rs.getString("continueCommentaire")

            );
            commetaireList.add(commentaire);
        }
        commentaireTable.setItems(commetaireList);
        continueCommentaireColumn.setCellValueFactory(new PropertyValueFactory<>("continueCommentaire"));
        posteIdColumn.setCellValueFactory(new PropertyValueFactory<>("poste_id"));
    }
    catch (SQLException ex) {
        Logger.getLogger(GestionCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
    }
    continueCommentaireField.setText("");
    posteIdField.setText("");
    commentaireTable.setItems(commetaireList);
}
*/




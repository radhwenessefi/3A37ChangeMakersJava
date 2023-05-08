package Gui;

import javafx.fxml.FXMLLoader;
import java.net.URL;
import Entities.Poste;
import Services.PosteService;
import Utils.DbConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Gui.PosteFrontController;
import Services.CommentaireService;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author khale
 */
public class ListPosteController implements Initializable {

    ObservableList <Poste> list = FXCollections.observableArrayList(); 
    @FXML
    private TableView<Poste> posteTable;
    @FXML
    private TableColumn<Poste, String> titreColumn;
    @FXML
    private TableColumn<Poste, String> descriptionColumn;
    @FXML
    private TableColumn<Poste, String> imageColumn;

    ObservableList <Poste> posteList = FXCollections.observableArrayList();    

         
    private static Poste selectedPoste;

    private PosteService posteService;
    
    private int id_selected;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Color x1;
    @FXML
    private ImageView posteImageView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        posteList = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
           try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `Poste`");
            while (rs.next()) {
                Poste poste = new Poste(
                    rs.getInt("id"),
                    rs.getString("titre"),
                    rs.getString("description"),
                    rs.getString("image")
                );
                posteList.add(poste);
            }
            posteTable.setItems(posteList);
            titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
/*
            // Load the image from the file system
            String defaultImagePath = "default-image.jpg";
            InputStream defaultImageStream = getClass().getResourceAsStream(defaultImagePath);


            if (defaultImageStream != null) {
                Image defaultImage = new Image(defaultImageStream);
                posteImageView.setImage(defaultImage);
            } else {
                System.err.println("Failed to load default image: " + defaultImagePath);
            }
           }*/         
        } catch (SQLException ex) {
            Logger.getLogger(GestionPosteController.class.getName()).log(Level.SEVERE, null, ex);

        posteService = new PosteService();
    }
    }    
    @FXML
private void tableview_clicked(MouseEvent event) {
    selectedPoste = posteTable.getSelectionModel().getSelectedItem();
    id_selected = selectedPoste.getId();
}

/*
private void handleShow(ActionEvent event) throws IOException {
    selectedPoste = posteTable.getSelectionModel().getSelectedItem();
    if (selectedPoste != null) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/PosteFront.fxml"));
        Parent root = loader.load();
        PosteFrontController controller = loader.getController();
        controller.setSelectedPoste(selectedPoste);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } else {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("No Poste Selected");
        alert.setHeaderText("Please select a poste from the table.");
        alert.showAndWait();
    }
}
*/



    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
private void goToCommant(ActionEvent event) throws IOException {
    System.out.println("hello" + selectedPoste);

    FXMLLoader loader = new FXMLLoader(getClass().getResource("PosteFront.fxml"));
    Parent root = loader.load();
    PosteFrontController controller = loader.getController();
    controller.setSelectedPoste(selectedPoste);
    
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.initStyle(StageStyle.UTILITY);
    stage.show();
}



}
    /*
        @FXML
    private void tableview_clicked(MouseEvent event) {

            selectedPoste = (Poste) posteTable.getItems();
             id_selected=posteTable.getSelectionModel().getSelectedItem().getId();
    }
    */
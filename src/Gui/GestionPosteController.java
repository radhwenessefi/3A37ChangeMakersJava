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
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
//import java.io.FileOutputStream;

public class GestionPosteController implements Initializable {

   
    @FXML
    ObservableList <Poste> list = FXCollections.observableArrayList();
    @FXML
    private ImageView posteImageView;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField titreField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TextField imageField;
    @FXML
    private TextField titreField2;
    @FXML
    private TableView<Poste> posteTable;
    @FXML
    private TableColumn<Poste, String> titreColumn;
    @FXML
    private TableColumn<Poste, String> descriptionColumn;
     @FXML
    private TableColumn<Poste, String> imageColumn;


    ObservableList <Poste> posteList2 = FXCollections.observableArrayList(); 
    
    ObservableList <Poste> posteList = FXCollections.observableArrayList(); 
     
    private static Poste selectedPoste;

    private PosteService posteService;


    private int posteId;
    private String imagePath;
    private int id_selected;
    
 
    
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
                    rs.getString("dicription"),
                    rs.getString("image")
                );
                posteList.add(poste);
            }
            posteTable.setItems(posteList);
            titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("deicription"));
            imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
         
        } catch (SQLException ex) {
            Logger.getLogger(GestionPosteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        posteService = new PosteService();
    }
@FXML
private void handleSearch(ActionEvent event) {
    String titre = titreField2.getText();
    List<Poste> postes = posteService.getByTitre(titre);
    ObservableList<Poste> observableList = FXCollections.observableArrayList(postes);
    posteTable.setItems(observableList);
    
}




@FXML
private void handleAdd(ActionEvent event) {
    PosteService ps = new PosteService();
    String titre = titreField.getText();
    String deicription = descriptionArea.getText();
    String image = imageField.getText();
    if(!titre.isEmpty() && !deicription.isEmpty() && !image.isEmpty()){
        try {
            Poste poste = new Poste(titre, deicription, image);
            ps.addPoste(poste);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Poste ajouté");
            alert.show();
            // Update the UI to show the new state of the database
            List<Poste> postes = ps.getAll();
            ObservableList<Poste> observableList = FXCollections.observableArrayList(postes);
            posteTable.setItems(posteList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Erreur : un champ est vide");
    }
}



@FXML
    private void tableview_clicked(MouseEvent event) {

            selectedPoste = (Poste) posteTable.getItems();
            int index = posteTable.getSelectionModel().getSelectedItem().getId();
            titreField.setText(posteTable.getSelectionModel().getSelectedItem().getTitre());
            descriptionArea.setText(posteTable.getSelectionModel().getSelectedItem().getDescription());
            imageField.setText(posteTable.getSelectionModel().getSelectedItem().getImage()+"");
             id_selected=posteTable.getSelectionModel().getSelectedItem().getId();
    }



@FXML
private void handleUpdate(ActionEvent event) {
    int index = id_selected;
    PosteService service = new PosteService();
    Poste poste = new Poste(titreField.getText(), descriptionArea.getText(), imageField.getText());

    if (!poste.getTitre().isEmpty() && !poste.getDescription().isEmpty()) {
        try {
            service.updatePoste(poste, index);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Poste updated");
            alert.show();

            // Refresh the table view with updated data
            posteList.clear();
            posteList.addAll(service.getAll());

        } catch (SQLException ex) {
            Logger.getLogger(GestionPosteController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Failed to update poste");
            alert.show();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("Please fill in all fields");
        alert.show();
    }
}


@FXML
private void handleDelete(javafx.event.ActionEvent event) throws IOException {
    PosteService posteService = new PosteService();

    try {
        Poste selectedPoste = posteTable.getSelectionModel().getSelectedItem();
        posteService.deletePoste(selectedPoste.getId());

        // Remove the deleted Poste from the table view
        posteTable.getItems().remove(selectedPoste);

        // Show a success message
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Poste deleted successfully!");
        alert.showAndWait();

    } catch (Exception e) {
        // Show an error message if there was a problem deleting the Poste
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Error deleting Poste: " + e.getMessage());
        alert.showAndWait();
    }
}

@FXML
private void refresh_pressed()
{
    posteList = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `Poste`");
            while (rs.next()) {
                Poste poste = new Poste(
                    rs.getInt("id"),
                    rs.getString("titre"),
                    rs.getString("deicription"),
                    rs.getString("image")
                );
                posteList.add(poste);
            }
            posteTable.setItems(posteList);
            titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("deicription"));
            imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        }
        catch (SQLException ex) {
            Logger.getLogger(GestionPosteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    titreField.setText("");
    descriptionArea.setText("");
    imageField.setText("");
    posteTable.setItems(posteList);
}
}
/*
@FXML
private void handleBackButtonAction(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
public void generatePDF(Poste poste) {
    Document document = new Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream("poste.pdf"));
        document.open();
        document.add(new Paragraph("ID: " + poste.getId()));
        document.add(new Paragraph("Titre: " + poste.getTitre()));
        document.add(new Paragraph("Description: " + poste.getDescription()));
        document.close();
        System.out.println("PDF file created!");
    } catch (DocumentException | IOException e) {
        e.printStackTrace();
    }
/*

}
}

public void handlepdf(ActionEvent event) {
    Poste poste = new Poste(1, "Titre", "Description", "image.png");
    generatePDF(poste);
}


}





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
*/  


/*

@FXML
private void handleRefresh(ActionEvent event) {
    posteTable.setItems(posteList);
}
*/

/*    
@FXML
private void handleSearch(ActionEvent event) {
      PosteService ps2 = new PosteService();
      String titre = titreField2.getText();
  
    if(titre = titreField2){
        try {  
            Poste postes = Poste( titre );
            ps2.getByTitre(titre)
            alert.show();                      
            List<Poste> postes = ps.getAll();
            ObservableList<Poste> observableList = FXCollections.observableArrayList(postes);
            posteTable.setItems(posteList2);
        } catch (SQLException ex) {           
            System.out.println(ex.getMessage());           
        }
    } else {
        JOptionPane.showMessageDialog(null, "Erreur : un champ est vide");
    }

}
    
*/    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Video;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static jdk.nashorn.internal.objects.NativeJava.type;
import static jdk.nashorn.internal.runtime.Debug.id;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import services.ServiceVideo;

/**
 * FXML Controller class
 *
 * @author dorra
 */
public class Home2Controller implements Initializable {

    @FXML
    private TextField txtTITRE;
    @FXML
    private TextField txtTYPE;
    @FXML
    private TextArea txtIMAGE;
    @FXML
    private TextArea txtFICHIER;
    @FXML
    private TableView<Video> table;
    @FXML
    private TableColumn<Video, Integer> IDCol;
    @FXML
    private TableColumn<Video, String> TITRECol;
    @FXML
    private TableColumn<Video, String> TYPECol;
    @FXML
    private TableColumn<Video, String> IMAGECol;
    @FXML
    private TableColumn<Video, String> FICHIERCol;
    @FXML
    private TableColumn<Video, String> RATINGCol;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnMdf;
    @FXML
    private Button btnSup;
    @FXML
    private AnchorPane tftitre;
    File file;
    File file1;
    File file2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceVideo sv = new ServiceVideo();
        ObservableList<Video> listtt = FXCollections.observableArrayList();

        try {
            listtt.addAll(sv.getAll());

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TITRECol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        TYPECol.setCellValueFactory(new PropertyValueFactory<>("description"));
        FICHIERCol.setCellValueFactory(new PropertyValueFactory<>("video"));
        IMAGECol.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        table.setItems(listtt);
        // TODO
    }

    @FXML
    private void add(ActionEvent event) throws FileNotFoundException, IOException {
        try {
            String titre = txtTITRE.getText();
            String description = txtTYPE.getText();

            Boolean titreEmpty = false;
            Boolean descriptionEmpty = false;
            Boolean videoEmpty = false;
            Boolean imageEmpty = false;
            

            if (titre.trim().isEmpty()) {
                txtTITRE.setStyle("-fx-border-color: red;");
                titreEmpty = true;
            }
            if (description.trim().isEmpty()) {
                txtTYPE.setStyle("-fx-border-color: red;");
                descriptionEmpty = true;
            }

            if (titreEmpty || descriptionEmpty || imageEmpty || videoEmpty) {
                Alert alert = new Alert(Alert.AlertType.WARNING);

                String str = "";
                if (titreEmpty) {
                    str += "Titre manquant!";
                    System.out.println("Error: Titre manquant");
                }
                if (descriptionEmpty) {
                    if (!str.isEmpty()) {
                        str += System.lineSeparator();
                    }
                    str += "type manquant!";
                    System.out.println("Error: type manquant");

                }
                if (imageEmpty) {
                    if (!str.isEmpty()) {
                        str += System.lineSeparator();
                    }
                    str += "Image manquante!";
                    System.out.println("Error: Image manquante");

                }
                if (videoEmpty) {
                    if (!str.isEmpty()) {
                        str += System.lineSeparator();
                    }
                    str += "fichier manquant!";
                    System.out.println("Error: fichier manquant");

                }
                str += System.lineSeparator() + System.lineSeparator() + "Veuillez verifier vos données!";

                alert.setTitle("Information invalid");
                alert.setHeaderText(str);
                alert.showAndWait();
            } else {

                FileInputStream fl = new FileInputStream(file2);
                byte[] data = new byte[(int) file2.length()];
                String image = file2.getName();
                fl.read(data);
                fl.close();
                
                FileInputStream fll = new FileInputStream(file1);
                byte[] dataa = new byte[(int) file1.length()];
                String video;
                video = file1.getName();
                fll.read(dataa);
                fll.close();
                
                Video v = new Video(titre, description, video, image);
                System.out.print(v);
                ServiceVideo sv = new ServiceVideo();

                sv.ajouter(v);
            }

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selection(MouseEvent event) {
        Video v = table.getSelectionModel().getSelectedItem();

        txtTITRE.setText(v.getTitre());
        txtTYPE.setText(v.getDescription());
        txtFICHIER.setText(v.getVideo());
        txtIMAGE.setText(v.getImage());
        

    }

    @FXML
    private void mdf(ActionEvent event) {
        Video v = table.getSelectionModel().getSelectedItem();
        if (v != null) {
            try {
                // Vérifier si un objet Audio est sélectionné
                // Mettre à jour les valeurs de l'objet Audio avec les nouvelles valeurs des champs de texte
                v.setTitre(txtTITRE.getText());
                v.setDescription(txtTYPE.getText());
                v.setVideo(txtFICHIER.getText());
                v.setImage(txtIMAGE.getText());
                

                // Mettre à jour la table pour refléter les modifications
                table.refresh();

                // Réinitialiser les champs de texte
                txtTITRE.setText("");
                txtTYPE.setText("");
                txtIMAGE.setText("");
                txtFICHIER.setText("");
                int id = v.getId(); // Get the ID of the selected audio
                ServiceVideo sv = new ServiceVideo();
                sv.modifier(v, id);

                // Réinitialiser l'objet Audio sélectionné
                v = null;
            } catch (SQLException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void sup(ActionEvent event) {

        try {
            Video v = table.getSelectionModel().getSelectedItem();
            // Get the selected audio from the table view

            if (v!= null) {
                int id = v.getId(); // Get the ID of the selected audio
                ServiceVideo sv = new ServiceVideo();
                sv.supprimer(id); // Call the supprimer method with the ID to delete the audio from the database
                table.getItems().remove(v);

            }
        } catch (SQLException ex) {
            System.out.println("Error deleting video: " + ex.getMessage());
        }
    }

    @FXML
    private File addimage(MouseEvent event) {
        Path to1 = null;

        String m = null;
        String path = "C:\\xampp\\htdocs\\img";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file2 = chooser.getSelectedFile();
            String fileName = file2.getName();

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //           to2 = Paths.get("src\\"+path+"\\"+file.getName()+".png");

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    System.out.println(file2);

                } catch (IOException ex) {
                    System.out.println();
                }
                String iamge = file2.getName();
                txtIMAGE.setText(iamge);

            }

        }
        return file2;

    }

    private void addfichier(ActionEvent event) {

    }

    @FXML
    private File addfichier(MouseEvent event) {
        Path to1 = null;

        String m = null;
        String path = "C:\\xampp\\htdocs\\img";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "mp4","MP4", "MP4");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file1 = chooser.getSelectedFile();
            String fileName = file1.getName();

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //           to2 = Paths.get("src\\"+path+"\\"+file.getName()+".png");

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    System.out.println(file1);

                } catch (IOException ex) {
                    System.out.println();
                }
                String iamge = file1.getName();
                txtIMAGE.setText(iamge);
            }
        }
        return file1;
        
        
        /*FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier audio");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MP3 Fichier", "*.mp3")
        );
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Path sourcePath = selectedFile.toPath();
            String fileName = selectedFile.getName();
            Path targetPath = Paths.get("C:/Users/dorra/OneDrive/Documents/NetBeansProjects/dorra2/test/fichierr/" + fileName);
            try {
                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File uploaded successfully to: " + targetPath.toString());
            } catch (IOException ex) {
                System.err.println("Error uploading file: " + ex.getMessage());
            }
        }*/
    }
}

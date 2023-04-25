/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Audio;
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
import services.ServiceAudio;
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

/**
 * FXML Controller class
 *
 * @author dorra
 */
public class HomeController implements Initializable {

    @FXML
    private TextField txtTITRE;
    @FXML
    private TextField txtTYPE;
    @FXML
    private TextArea txtIMAGE;
    @FXML
    private TextArea txtFICHIER;
    @FXML
    private TableView<Audio> table;
    @FXML
    private TableColumn<Audio, Integer> IDCol;
    @FXML
    private TableColumn<Audio, String> TITRECol;
    @FXML
    private TableColumn<Audio, String> TYPECol;
    @FXML
    private TableColumn<Audio, String> IMAGECol;
    @FXML
    private TableColumn<Audio, String> FICHIERCol;
    @FXML
    private TableColumn<Audio, String> RATINGCol;
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
        ServiceAudio sa = new ServiceAudio();
        ObservableList<Audio> listtt = FXCollections.observableArrayList();

        try {
            listtt.addAll(sa.getAll());

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TITRECol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        TYPECol.setCellValueFactory(new PropertyValueFactory<>("type"));
        IMAGECol.setCellValueFactory(new PropertyValueFactory<>("image"));
        FICHIERCol.setCellValueFactory(new PropertyValueFactory<>("fichier"));
        table.setItems(listtt);
        // TODO
    }

    @FXML
    private void add(ActionEvent event) throws FileNotFoundException, IOException {
        try {
            String titre = txtTITRE.getText();
            String type = txtTYPE.getText();

            Boolean titreEmpty = false;
            Boolean typeEmpty = false;
            Boolean imageEmpty = false;
            Boolean fichierEmpty = false;

            if (titre.trim().isEmpty()) {
                txtTITRE.setStyle("-fx-border-color: red;");
                titreEmpty = true;
            }
            if (type.trim().isEmpty()) {
                txtTYPE.setStyle("-fx-border-color: red;");
                typeEmpty = true;
            }

            if (titreEmpty || typeEmpty || imageEmpty || fichierEmpty) {
                Alert alert = new Alert(Alert.AlertType.WARNING);

                String str = "";
                if (titreEmpty) {
                    str += "Titre manquant!";
                    System.out.println("Error: Titre manquant");
                }
                if (typeEmpty) {
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
                if (fichierEmpty) {
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
                String fichier = file1.getName();
                fll.read(dataa);
                fll.close();
                
                Audio a = new Audio(titre, type, image, fichier);
                System.out.print(a);
                ServiceAudio sa = new ServiceAudio();

                sa.ajouter(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selection(MouseEvent event) {
        Audio a = table.getSelectionModel().getSelectedItem();

        txtTITRE.setText(a.getTitre());
        txtTYPE.setText(a.getType());
        txtIMAGE.setText(a.getImage());
        txtFICHIER.setText(a.getFichier());

    }

    @FXML
    private void mdf(ActionEvent event) {
        Audio a = table.getSelectionModel().getSelectedItem();
        if (a != null) {
            try {
                // Vérifier si un objet Audio est sélectionné
                // Mettre à jour les valeurs de l'objet Audio avec les nouvelles valeurs des champs de texte
                a.setTitre(txtTITRE.getText());
                a.setType(txtTYPE.getText());
                a.setImage(txtIMAGE.getText());
                a.setFichier(txtFICHIER.getText());

                // Mettre à jour la table pour refléter les modifications
                table.refresh();

                // Réinitialiser les champs de texte
                txtTITRE.setText("");
                txtTYPE.setText("");
                txtIMAGE.setText("");
                txtFICHIER.setText("");
                int id = a.getId(); // Get the ID of the selected audio
                ServiceAudio sa = new ServiceAudio();
                sa.modifier(a, id);

                // Réinitialiser l'objet Audio sélectionné
                a = null;
            } catch (SQLException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void sup(ActionEvent event) {

        try {
            Audio a = table.getSelectionModel().getSelectedItem();
            // Get the selected audio from the table view

            if (a != null) {
                int id = a.getId(); // Get the ID of the selected audio
                ServiceAudio sa = new ServiceAudio();
                sa.supprimer(id); // Call the supprimer method with the ID to delete the audio from the database
                table.getItems().remove(a);

            }
        } catch (SQLException ex) {
            System.out.println("Error deleting audio: " + ex.getMessage());
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
                "mp3","MP3", "MP4");
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

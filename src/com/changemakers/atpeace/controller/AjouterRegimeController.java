/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entites.Regime;
import com.changemakers.atpeace.services.ServiceRegime;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjouterRegimeController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfDesc;
    @FXML
    private TextField tfListe;

    @FXML
    private ComboBox<?> combolevel;
    private TextField tfLevel;
    @FXML
    private AnchorPane tftitre;
    File file;
    File file1;
    File file2;
    @FXML
    private TextArea tfimagepath;
    @FXML
    private Label lbt;
    @FXML
    private Button btnajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<String> list = new ArrayList<>();
        list.add("ideal");
        list.add("overweghit");
        list.add("underweghit");

        ObservableList obList = FXCollections.observableList(list);
        combolevel.getItems().clear();
        combolevel.setItems(obList);
    }

    

    

    
    



    @FXML
    private File addimage(MouseEvent event) {
        
         Path to1 = null;
        
        String m = null;
        String path = "C:\\Users\\DELL\\Downloads\\WorkshopJDBC3A37\\WorkshopJDBC3A37\\src\\assets";
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
                        tfimagepath.setText(iamge);

            }

        }
        return file2;

        
        
        
    }

    @FXML
    private void ajouterRegime(ActionEvent event) throws SQLException, IOException {
        controleRegime controle = new controleRegime();
    String titre = tfTitre.getText();
    String discription = tfDesc.getText();
    String liste_alement = tfListe.getText();
    String level = "";
    if (combolevel.getSelectionModel().getSelectedItem() != null){
        level = combolevel.getSelectionModel().getSelectedItem().toString();
    }
         if (tfimagepath.getText().isEmpty()) {
    // show an error message if the TextField is empty
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Please enter a value in an image .");
    alert.showAndWait();
}else{
    FileInputStream fl = new FileInputStream(file2);
        byte[] data = new byte[(int) file2.length()];
        String image = file2.getName();
        fl.read(data);
        fl.close();
        

    Boolean retourstr = true;

    Regime r = new Regime(titre, discription,liste_alement ,image,level);
    if (!controle.ControleTitreUpdate(r)){
        retourstr = false;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText("Titre invalide!");
        alert.showAndWait();
    } else if (!controle.ControleDesc(r)) {
        retourstr = false;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText("Description invalide!");
        alert.showAndWait();
    }   else if (!controle.Controleliste(r)) {
        retourstr = false;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText("liste invalide!");
        alert.showAndWait();
    }  else if (!controle.Controlelevel(r)) {
        retourstr = false;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText("level invalide!");
        alert.showAndWait();
    }
    else {
        ServiceRegime sp = new ServiceRegime();
        sp.insertOne1(r);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Ajouter   avec succès!");
        alert.showAndWait();
    }
    }

    }

}

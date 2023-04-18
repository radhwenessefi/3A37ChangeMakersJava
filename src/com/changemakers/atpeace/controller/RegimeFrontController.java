/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entites.Regime;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class RegimeFrontController implements Initializable {

    @FXML
    private Label labelTitre1;
    @FXML
    private ImageView imageContainer;
    @FXML
    private Label labelDescription;
    @FXML
    private Label labelListe;
    @FXML
    private Label labelLevel;
    @FXML
    private Rating tfrate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

 public void setData(Regime regimes) throws SQLException {
    String image = regimes.getImage();
    File file = new File("C:\\Users\\DELL\\Downloads\\WorkshopJDBC3A37\\WorkshopJDBC3A37\\src\\assets\\" + image);
    Image image1 = new Image(file.toURI().toString());
    imageContainer.setImage(image1);
    labelTitre1.setText(regimes.getTitle());
    labelDescription.setText(regimes.getDiscription());
    labelListe.setText(regimes.getListe_alement());
    labelLevel.setText(regimes.getLevel());
}

    @FXML
    private void ratRegime(MouseEvent event) {
        
    }
}

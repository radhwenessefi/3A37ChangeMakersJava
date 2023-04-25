/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Audio;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import services.ServiceAudio;

/**
 * FXML Controller class
 *
 * @author dorra
 */
public class FRONTTController implements Initializable {

    private List<File> list;
    @FXML
    private TextField search;
    @FXML
    private ScrollPane scrollView;

    private List<Audio> listAudio;
    private ServiceAudio sa = new ServiceAudio();
    @FXML
    private HBox cardLayout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            List<Audio> ls = new ArrayList<>();
            ls = sa.getAll();
            for (Audio audio : ls){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("AudioFront.fxml"));
                HBox cardBox = loader.load();
                AudioFrontController afc = loader.getController();
                afc.setData(audio);
                cardLayout.getChildren().add(cardBox);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FRONTTController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FRONTTController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

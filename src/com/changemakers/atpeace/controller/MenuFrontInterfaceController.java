/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.services.ServicePatient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author gille
 */
public class MenuFrontInterfaceController implements Initializable {

    @FXML
    private TextField search;
    
    int id;
    @FXML
    private ImageView phregime;
    @FXML
    private ImageView phsport;
    @FXML
    private Label lbusername;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setLbusename(String username) {
        this.lbusername.setText(username);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void profil(MouseEvent event) {
        /*  FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuInterface.fxml"));

                try {                  
                    Parent root = loader.load();
                    BaseFrontController mn = loader.getController();
                    mn.setId(id);
                   
                    mn.setLbusename(lbusername.getText());
                   

                    phregime.getScene().setRoot(root);
                    
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }*/
    }

    @FXML
    private void regime(MouseEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/baseFront.fxml"));

                try {                  
                    Parent root = loader.load();
                    BaseFrontController mn = loader.getController();
                 /*   mn.setId(id);
                   
                    mn.setLbusename(lbusername.getText());*/
                               System.out.println(id);


                    phregime.getScene().setRoot(root);
                    
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
        
    }

    @FXML
    private void sport(MouseEvent event) {
    }
        
    

   
    
}

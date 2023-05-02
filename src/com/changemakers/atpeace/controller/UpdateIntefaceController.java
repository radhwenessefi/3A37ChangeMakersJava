/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author gille
 */
public class UpdateIntefaceController implements Initializable {

    @FXML
    private Label tbusername;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tftelphone;
    @FXML
    private PasswordField tfmdp;
    @FXML
    private TextField tfnom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setTbusername(String tbusername) {
        this.tbusername.setText(tbusername);
    }

    public void setTfprenom(String tfprenom) {
        this.tfprenom.setText(tfprenom);
    }

    public void setTfemail(String tfemail) {
        this.tfemail.setText(tfemail);
    }

    public void setTfadresse(String tfadresse) {
        this.tfadresse.setText(tfadresse);
    }

    public void setTftelphone(String tftelphone) {
        this.tftelphone.setText(tftelphone);
    }

    public void setTfnom(String tfnom) {
        this.tfnom.setText(tfnom);
    }
    
    
}

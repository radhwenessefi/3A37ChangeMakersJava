/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author zaiir
 */
public class DashboardController implements Initializable {

    @FXML
    private BorderPane mainPane;
    @FXML
    private Button ao_btn_dashboard;
    @FXML
    private Button ao_btn_manage_users;
    @FXML
    private Button ao_btn_manage_users1;
    @FXML
    private Button ao_btn_manage_users11;
    @FXML
    private Button ao_btn_manage_users12;
    @FXML
    private Button ao_btn_manage_users13;
    @FXML
    private Button ao_btn_manage_users131;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoToDashboard(ActionEvent event) {
    }

    @FXML
    private void GoToManagUsers(ActionEvent event) {
    }

    @FXML
    private void GoToManagProd(ActionEvent event) {
    }

    @FXML
    private void GoToManagMusic(ActionEvent event) {
    }

    @FXML
    private void GoToManagSport(ActionEvent event) {
    }

    @FXML
    private void GoToManagReclamation(ActionEvent event) {
    }

    @FXML
    private void GoToManagBlog(ActionEvent event) {
    }
    
}

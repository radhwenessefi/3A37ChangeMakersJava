/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package changemakers.gui;


import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *
 * @author LENOVO
 */
public class NewFXMMain extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) {
       
        try {
            
            Parent root;
            root = FXMLLoader.load(getClass().getResource("MenuInterface.fxml"));
            
            Scene scene = new Scene(root);
        
            primaryStage.setTitle("Projet X");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

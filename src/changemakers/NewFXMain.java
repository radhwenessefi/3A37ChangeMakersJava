/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package changemakers;

import java.io.IOException;
import javafx.application.Application;
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
 * @author zaiir
 */
public class NewFXMain extends Application {
    
      @Override
    public void start(Stage primaryStage) {
     
    
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/changemakers/gui/Panier.fxml"));
        Scene scene = new Scene(root, 350, 350);
        primaryStage.setTitle("Ajout");
        primaryStage.setScene(scene);
        primaryStage.show();
    } catch (IOException ex) {
        System.err.println("Error loading FXML file: " + ex.getMessage());
    } catch (Exception ex) {
        System.err.println("Error in start() method: " + ex.getMessage());
    }
}

     
     
    
        
        
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

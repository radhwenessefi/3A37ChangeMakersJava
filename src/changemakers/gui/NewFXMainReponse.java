/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package changemakers.gui;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public class NewFXMainReponse extends javafx.application.Application {
    
    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
       
        try {
            
            Parent root;
           root = FXMLLoader.load(getClass().getResource("MenuInterface_1.fxml"));
            //root = FXMLLoader.load(getClass().getResource("reponseScene.fxml"));
            
            Scene scene_1 = new Scene(root);
        
            primaryStage.setTitle("Projet X");
            primaryStage.setScene(scene_1);
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

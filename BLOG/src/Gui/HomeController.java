package Gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {
    
    @FXML
    private Button addPostesButton;
    
    @FXML
    private Button postesListButton;
    

    /*
     @FXML
    private Button commentairesListButton;
       
    */
    
    @FXML
    void handleAddPostesButtonAction(ActionEvent event) throws IOException {
        Parent addPostesParent = FXMLLoader.load(getClass().getResource("PosteDetails.fxml"));
        Scene addPostesScene = new Scene(addPostesParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(addPostesScene);
        appStage.show();
    }
    
    @FXML
    void handlePostesListButtonAction(ActionEvent event) throws IOException {
        Parent postesListParent = FXMLLoader.load(getClass().getResource("Poste.fxml"));
        Scene postesListScene = new Scene(postesListParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(postesListScene);
        appStage.show();
    }
    

    
        
    /*
        @FXML
    void handleCommentairesListButtonAction(ActionEvent event) throws IOException {
        Parent commentairesListParent = FXMLLoader.load(getClass().getResource("CommentaireDetails.fxml"));
        Scene commentairesListScene = new Scene(commentairesListParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(commentairesListScene);
        appStage.show();
    }
    */
    
}

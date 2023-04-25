package Gui;

import Entities.Commentaire;
import Entities.Poste;
import Services.CommentaireService;
import Services.PosteService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PosteDetailsController implements Initializable {

    @FXML
    private ImageView posteImageView;
    @FXML
    private Label posteTitreLabel;
    @FXML
    private Label posteDescriptionLabel;
    @FXML
    private Label commentaireTitleLabel;
    @FXML
    private VBox commentairesVBox;

    private PosteService posteService = new PosteService();
    private CommentaireService commentaireService = new CommentaireService();

    private Poste poste;

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (poste != null) {
            File file = new File(poste.getImage());
            Image image = new Image(file.toURI().toString());
            posteImageView.setImage(image);

            posteTitreLabel.setText(poste.getTitre());
            posteDescriptionLabel.setText(poste.getDescription());

            List<Commentaire> commentaires = commentaireService.getByPoste(poste.getId());

            for (Commentaire commentaire : commentaires) {
                Label label = new Label(commentaire.getContinueCommentaire());
                label.setWrapText(true);
                commentairesVBox.getChildren().add(label);
            }
        }
    }
@FXML
private TextArea newCommentTextArea;

public void submitComment() {
String commentText = newCommentTextArea.getText();
if (!commentText.isEmpty()) {
CommentaireService commentaireService = new CommentaireService();
Commentaire commentaire = new Commentaire();
commentaire.setContinueCommentaire(commentText);
commentaire.setPoste(poste);
commentaireService.addCommentaire(commentaire);
newCommentTextArea.clear();
// Refresh the comments list
List<Commentaire> commentaires = commentaireService.getByPoste(poste.getId());
commentairesVBox.getChildren().clear();
for (Commentaire c : commentaires) {
Label label = new Label(c.getContinueCommentaire());
label.setWrapText(true);
commentairesVBox.getChildren().add(label);
}
}
}
@FXML
private Button backButton;

@FXML
private void goBack(ActionEvent event) throws IOException {
    // Load the previous FXML file and show it
    FXMLLoader loader = new FXMLLoader(getClass().getResource("previous.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}



}

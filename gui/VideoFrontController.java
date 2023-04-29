/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entites.Video;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;

import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import org.controlsfx.control.Rating;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;



import services.ServiceVideo;

/**
 * FXML Controller class
 *
 * @author dorra
 */
public class VideoFrontController implements Initializable {

    private Label labelTitre;
    @FXML
    private Label labelType;
    private Video video;

    private int vidsNum;
    private Timer timer;
    private TimerTask timerTask;
    private boolean running;

    private ArrayList<File> vids;
    private File[] files;

    private Media media;
    private MediaPlayer mediaPlayer;

    private File directory;

    @FXML
    private Label titre;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfType;
    private Integer like;
    private Integer dislike;
    @FXML
    private Label labelDislikes;
    @FXML
    private Label labelLikes;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private MediaView mediaView;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        vids = new ArrayList<File>();
        directory = new File("C:\\Users\\dorra\\OneDrive\\Documents\\NetBeansProjects\\dorra2\\test\\fichierr");
        files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
              vids.add(file);
                System.out.println(file);
            }
        }
        media = new Media(vids.get(vidsNum).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
    }

    void setData(Video video) {
        this.video =video;
        if (tfTitre == null) {
            System.out.println("null");
        } else {
            tfTitre.setText(video.getTitre());
            tfType.setText(video.getDescription());
        }
        System.out.println(video.getTitre());
        System.out.println(video.getDescription());
    }

   

    @FXML
    private void dislike(MouseEvent event) throws SQLException {
        if (labelDislikes.getText() == "") {
            int id = this.video.getId();
            String titre = video.getTitre();
            String type = video.getDescription();
            int rating = video.getRating();
            String image = video.getImage();
            String fichier = video.getVideo();
            int like = video.getLike();

            int dislike = video.getDislike() + 1;
            video.setDislike(dislike);

            ServiceVideo sv = new ServiceVideo();
            Video video_change = new Video(titre, type, rating, image, fichier, like, dislike);
            System.out.print("dorrrrrrrrrrrrrrrra" + video);
            sv.modifier(video_change, id);
            
        }

    }

    @FXML
    private void like(MouseEvent event) throws SQLException {

        if (labelLikes.getText() == "") {
            int id = this.video.getId();
            String titre = video.getTitre();
            String type = video.getDescription();
            int rating = video.getRating();
            String image = video.getImage();
            String fichier = video.getImage();
            int dislike = video.getDislike();

            int like = video.getLike() + 1;
            video.setLike(like);

            ServiceVideo sv= new ServiceVideo();
            Video video_change = new Video(titre, type, rating, image, fichier, like, dislike);
            System.out.print("dorrrrrrrrrrrrrrrra" + video);
            sv.modifier(video_change, id);
          
        }
    }

    public void refresh() {

    }

    @FXML
    private void pauseMedia(ActionEvent event) {
         mediaPlayer.pause();
    }

    @FXML
    private void playMedia(ActionEvent event) {
        mediaPlayer.play();
    }
}

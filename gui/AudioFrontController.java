/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Audio;
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

import services.ServiceAudio;

/**
 * FXML Controller class
 *
 * @author dorra
 */
public class AudioFrontController implements Initializable {

    private Label labelTitre;
    @FXML
    private Label labelType;
    @FXML
    private ProgressBar progress;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnPause;
    @FXML
    private Slider slider;
    @FXML
    private ImageView img;
    private Audio audio;

    private int songNum;
    private Timer timer;
    private TimerTask timerTask;
    private boolean running;

    private ArrayList<File> songs;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        songs = new ArrayList<File>();
        directory = new File("C:\\Users\\dorra\\OneDrive\\Documents\\NetBeansProjects\\dorra2\\test\\fichierr\\");
        files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                songs.add(file);
                System.out.println(file);
            }
        }
        media = new Media(songs.get(songNum).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    void setData(Audio audio) {
        this.audio = audio;
        if (tfTitre == null) {
            System.out.println("null");
        } else {
            tfTitre.setText(audio.getTitre());
            tfType.setText(audio.getType());
        }
        System.out.println(audio.getTitre());
        System.out.println(audio.getType());
    }

    @FXML
    private void playMusic(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    private void pauseMusic(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    private void dislike(MouseEvent event) throws SQLException {
        if (labelDislikes.getText() == "") {
            int id = this.audio.getId();
            String titre = audio.getTitre();
            String type = audio.getType();
            int rating = audio.getRating();
            String image = audio.getImage();
            String fichier = audio.getFichier();
            int like = audio.getLike();

            int dislike = audio.getDislike() + 1;
            audio.setDislike(dislike);

            ServiceAudio sa = new ServiceAudio();
            Audio audio_change = new Audio(titre, type, rating, image, fichier, like, dislike);
            System.out.print("dorrrrrrrrrrrrrrrra" + audio);
            sa.modifier(audio_change, id);
            labelDislikes.setText(audio.getDislike().toString());
        }

    }

    @FXML
    private void like(MouseEvent event) throws SQLException {

        if (labelLikes.getText() == "") {
            int id = this.audio.getId();
            String titre = audio.getTitre();
            String type = audio.getType();
            int rating = audio.getRating();
            String image = audio.getImage();
            String fichier = audio.getImage();
            int dislike = audio.getDislike();

            int like = audio.getLike() + 1;
            audio.setLike(like);

            ServiceAudio sr = new ServiceAudio();
            Audio audio_change = new Audio(titre, type, rating, image, fichier, like, dislike);
            System.out.print("dorrrrrrrrrrrrrrrra" + audio);
            sr.modifier(audio_change, id);
            labelLikes.setText(audio.getLike().toString());
        }
    }

    public void refresh() {

    }

    void setData(Video video) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

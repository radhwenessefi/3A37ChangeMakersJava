/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.entities.Favoris;
import com.changemakers.atpeace.entites.Rate;
import com.changemakers.atpeace.entites.Regime;
import com.changemakers.atpeace.entities.Patient;
import com.changemakers.atpeace.entities.Session;
import com.changemakers.atpeace.services.ServiceFavoris;
import com.changemakers.atpeace.services.ServicePatient;
import com.changemakers.atpeace.services.ServiceRate;
import com.changemakers.atpeace.services.SessionService;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class RegimeFrontController implements Initializable {

    @FXML
    private Label labelTitre1;
    @FXML
    private ImageView imageContainer;
    @FXML
    private Label labelDescription;
    @FXML
    private Label labelListe;
    @FXML
    private Label labelLevel;
    @FXML
    private Rating tfrate;

    /**
     * Initializes the controller class.
     */
    private Regime regime;
    int regime_id;
    String name_regime;
    int rating;
    int num_totale;
    int nub_of_rate;
    int id;
Session s = new Session();
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         SessionService ss = new SessionService();
        s = ss.ConnectUser();
        id = s.getId_user();
        System.out.println(id);
    }

 public void setData(Regime regimes) throws SQLException {
    String image = regimes.getImage();
    File file = new File("C:\\Users\\DELL\\Desktop\\WorkshopJDBC3A37\\WorkshopJDBC3A37\\src\\assets\\" + image);
    Image image1 = new Image(file.toURI().toString());
    imageContainer.setImage(image1);
    labelTitre1.setText(regimes.getTitle());
    labelDescription.setText(regimes.getDiscription());
    labelListe.setText(regimes.getListe_alement());
    labelLevel.setText(regimes.getLevel());
    this.regime=regimes;
    
}

    @FXML
    private void ratRegime(MouseEvent event) throws SQLException {
        double sum_rate = 0;
        List<Rate> listrate = null;
          controlleRate controle = new controlleRate();
          listrate=controle.AfficheToutRate();
           for (Rate rate_tout : listrate) {
           sum_rate+=rate_tout.getNum_totale();
           
           }
        Rate rate = new Rate();
     
        regime_id=regime.getId();
        name_regime=regime.getTitle();
        ServiceRate sr = new ServiceRate();
        
       List<Rate> rate_test=sr.selectByIdRegime(regime_id);
        if (!rate_test.isEmpty()) { // check if the list is not empty
    for (Rate rate_t : rate_test) {
       num_totale= (int) (rate_t.getNum_totale()+tfrate.getRating()); 
        nub_of_rate=rate_t.getNub_of_rate()+1;
        rating= (int) tfrate.getRating();
        int id=rate_t.getId();
                Rate rate_change = new Rate(id,regime_id,rating,num_totale,nub_of_rate,name_regime);
                 System.out.print("donnnnnne"+rate_change);
                sr.updateOne(rate_change);

    } 
            
            
}else {
       num_totale= (int) (tfrate.getRating()); 
           nub_of_rate=1;
           rating=(int) tfrate.getRating();
             Rate rate_change = new Rate(regime_id,rating,num_totale,nub_of_rate,name_regime);
                sr.insertOne1(rate_change);
           
        }
    }

    @FXML
    private void addFavoris(MouseEvent event) throws SQLException {
              
        Favoris rate = new Favoris();
       
        regime_id=regime.getId();
        System.out.print("IIIIIIIIIIIIIID"+regime_id);
                name_regime=regime.getTitle();

        ServiceFavoris sr = new ServiceFavoris();
        
       List<Favoris> favoris_test=sr.selectByIdRegime(regime_id);
        System.out.print("tesst"+favoris_test);
       
       ServicePatient sp = new ServicePatient();
                   System.out.println("Avant la recherche" + id);

         Patient p = sp.VerifierI(id);
        if (!favoris_test.isEmpty()) { // check if the list is not empty
            System.out.println(p);
    for (Favoris favoris_t : favoris_test) {
        int nb_total = favoris_t.getNb_total()+ 1; 
        int nb_favori = favoris_t.getNb_favori()+1;
        rating= (int) tfrate.getRating();
        int id=favoris_t.getId();
         
                   System.out.println(p.getId());

                Favoris favoris_change = new Favoris(id,regime,p,nb_favori,nb_total);
                sr.updateOne(favoris_change);

    } 
            
            
}else {
             int nb_total = 1; 
        int nb_favori = 1;
                     System.out.println(p);
            System.out.println(p.getId());
               Favoris favoris_new = new Favoris(regime,p,nb_favori,nb_total);
                sr.insertOne(favoris_new);
           
        }
        
    }
}

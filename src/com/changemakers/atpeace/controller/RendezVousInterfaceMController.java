/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.controller.ProfilInterfaceMController;
import com.changemakers.atpeace.entities.Medecin;
import com.changemakers.atpeace.entities.Patient;
import com.changemakers.atpeace.entities.RendezVous;
import com.changemakers.atpeace.services.MailSender;
import com.changemakers.atpeace.services.RdvService;
import com.changemakers.atpeace.services.ServiceMedecin;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author gille
 */
public class RendezVousInterfaceMController implements Initializable {

    @FXML
    private Label tbusername;
    @FXML
    private Label lbEt;
    @FXML
    private Label lbetat;
    @FXML
    private Label lbDi;
    @FXML
    private Label lbdiplome;
    @FXML
    private TableView<RendezVous> tabrdv;
    @FXML
    private TableColumn<RendezVous, Integer> colid;
    @FXML
    private TableColumn<Patient, String> colnom;
    @FXML
    private TableColumn<RendezVous, Date> coldate;
    @FXML
    private TableColumn<RendezVous, String> coletat;
    @FXML
    private DatePicker datepk;
    
   
    @FXML
    private Label lbid;

    /*public RendezVousInterfaceMController() {
        this.id = Integer.parseInt(lbid.getText());
    }*/
    @FXML
    private ImageView pfil;

     private int id;
     
     private String email;
     
    public void setLbid(String e) {
        this.lbid.setText(e);
    }
      
    static Medecin m = new Medecin();
    
    ObservableList rdvdata = FXCollections.observableArrayList();
    
    RendezVous rdv = new RendezVous();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            // TODO
         //  id = Integer.parseInt(lbid.getText());
           RdvService sm = new RdvService();
           ServiceMedecin ms = new ServiceMedecin();
           m = ms.Verifier(lbid.getText());
           List<RendezVous> lrdv = sm.ListRdv(7);
            m.setRdv(lrdv);
            
            if (!lrdv.isEmpty()) {
                for (int i = 0; i < lrdv.size(); i++) {
                    rdvdata.add(lrdv.get(i));
                }
            }
            
            colid.setCellValueFactory(new PropertyValueFactory<RendezVous, Integer>("id"));
            colnom.setCellValueFactory(new PropertyValueFactory<Patient, String>("rdvdata.patient.nom"));
            coletat.setCellValueFactory(new PropertyValueFactory<RendezVous, String>("etat"));
            coldate.setCellValueFactory(new PropertyValueFactory<RendezVous, Date>("dateRdv"));
            // coltel.setCellValueFactory(new PropertyValueFactory<Medecin, String>("telephone"));
            tabrdv.setItems(rdvdata);
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousInterfaceMController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdM() {
        return email;
    }

    public void setIdM(String email) {
        this.email = email;
    }

    
    public static void refreshTableView(TableView tableView) {
        try {
            ObservableList dataList = FXCollections.observableArrayList();
            RdvService sm = new RdvService();
            ServiceMedecin ms = new ServiceMedecin();
            //Medecin medecin = ms.VerifierD(0);
            List<RendezVous> lrdv = sm.ListRdv(7);
            
            
            if (!lrdv.isEmpty()) {
                for (int i = 0; i < lrdv.size(); i++) {
                    dataList.add(lrdv.get(i));
                }
            }
            tableView.getItems().setAll(dataList);
            tableView.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousInterfaceMController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void selectItems(MouseEvent event) {
        
         rdv = tabrdv.getSelectionModel().getSelectedItem();
         
    }

    @FXML
    private void updateRdv(ActionEvent event) {
         LocalDate localDate = datepk.getValue();
        if (localDate == null) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veiller entrer une date.");
            alert.showAndWait();
        } else
        {
            Date date = null;
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            date = Date.from(instant);
            RdvService rdvs = new RdvService();
            rdvs.ModifierD(date,rdv.getId());
           refreshTableView(tabrdv);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Date du rendez-vous modifié avec succes.");
            alert.showAndWait();
        }
            
    }

    @FXML
    private void annulerRdv(ActionEvent event) {
        
        if(rdv.getEtat() == "Approuver")
        {
             RdvService sm = new RdvService();
             rdv.setEtat("Annuler");
            sm.ModifierE(rdv.getEtat(), rdv.getId());
              MailSender mailSender = new MailSender();
            try {
                mailSender.sendEmail(rdv.getPatient().getEmail(), "Votre rendez vous à été annuler","Annuler");
            } catch (MessagingException ex) {
                Logger.getLogger(RendezVousInterfaceMController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Alert alert = new Alert(Alert.AlertType.INFORMATION, "rendez-vous valider.");
            alert.showAndWait();
        }
       /* else  if(rdv.getEtat() == "En attente")
        {
             RdvService sm = new RdvService();
             rdv.setEtat("Refuser");
            sm.ModifierE(rdv.getEtat(), rdv.getId());
             MailSender mailSender = new MailSender();
            try {
                mailSender.sendEmail(rdv.getPatient().getEmail(), "Votre rendez vous à été réfuser");
            } catch (MessagingException ex) {
                Logger.getLogger(RendezVousInterfaceMController.class.getName()).log(Level.SEVERE, null, ex);
            }
             Alert alert = new Alert(Alert.AlertType.INFORMATION, "rendez-vous valider.");
            alert.showAndWait();
        }*/
        else if(rdv.getEtat() == "Annuler")
        {   
           
             RdvService sm = new RdvService();
             sm.Delete(rdv.getId());
              ServiceMedecin ms = new ServiceMedecin();
              Medecin m = ms.VerifierD(7);
              m.setEtat("Disponible");
            ms.ModifierD(m.getEtat(), 7);
              Alert alert = new Alert(Alert.AlertType.INFORMATION, "rendez-vous Supprimer.");
              alert.showAndWait();
        }
          refreshTableView(tabrdv);   
    }

    @FXML
    private void validerRdv(ActionEvent event) { 
        try {
            /*ServiceMedecin ms = new ServiceMedecin();
            Medecin medecin = ms.VerifierD(id);*/
            RdvService sm = new RdvService();
            rdv.setEtat("Approuver");
            sm.ModifierE(rdv.getEtat(), rdv.getId());
            MailSender mailSender = new MailSender();
            mailSender.sendEmail(rdv.getPatient().getEmail(), "Votre rendez vous à été approuver","Approuver");
             refreshTableView(tabrdv);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Rendez-vous approuver .");
            alert.showAndWait();
        } catch (MessagingException ex) {
            Logger.getLogger(RendezVousInterfaceMController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void profil(MouseEvent event) {
        
         Medecin medecin = new Medecin();
                ServiceMedecin sm = new ServiceMedecin();
                medecin = sm.VerifierD(7);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilInterfaceM.fxml"));

                try {
                    Parent root = loader.load();
                    ProfilInterfaceMController mn = loader.getController();
                    mn.setId(medecin.getId());
                    mn.setTfemail(medecin.getEmail());
                    mn.setTfnom(medecin.getNom());
                    mn.setTfprenom(medecin.getPrenom());
                    mn.setTftelphone(medecin.getTelephone());
                    mn.setTbusername(medecin.getNom());
                    mn.setTfadresse(medecin.getAdresse());
                    mn. setTadiplome(medecin.getDiplome());
                    pfil.getScene().setRoot(root);
                   // btseconnecter.getScene().setRoot(root);
                    } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
    }
    
    
}

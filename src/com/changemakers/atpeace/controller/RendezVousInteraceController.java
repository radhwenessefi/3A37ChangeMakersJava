/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.controller;

import com.changemakers.atpeace.controller.MenuInterfaceController;
import com.changemakers.atpeace.entities.Medecin;
import com.changemakers.atpeace.entities.Patient;
import com.changemakers.atpeace.entities.RendezVous;
import com.changemakers.atpeace.services.MailSender;
import com.changemakers.atpeace.services.RdvService;
import com.changemakers.atpeace.services.ServiceMedecin;
import com.changemakers.atpeace.services.ServicePatient;
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
public class RendezVousInteraceController implements Initializable {

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
    private TableView<Medecin> tabmedecin;
    @FXML
    private TableColumn<Medecin, Integer> colid;
    @FXML
    private TableColumn<Medecin, String> colnom;
    @FXML
    private TableColumn<Medecin, String> colprenom;
    @FXML
    private TableColumn<Medecin, String> coletat;
    @FXML
    private TableColumn<Medecin, String> coldiplome;
    @FXML
    private TableColumn<Medecin, String> coltel;

    ObservableList medecindata = FXCollections.observableArrayList();
    @FXML
    private DatePicker datepk;
    /*  @FXML
    private TableView<Medecin> tabmedecin1;
    @FXML
    private TableColumn<Medecin,Integer> colid1;
    @FXML
    private TableColumn<Medecin,String> colnom1;
    @FXML
    private TableColumn<Medecin,String> colprenom1;
    @FXML
    private TableColumn<Medecin,String> coletat1;
    @FXML
    private TableColumn<Medecin,String> coldiplome1;
    @FXML
    private TableColumn<Medecin,String> coltel1;*/

    Medecin m = new Medecin();
    Patient p = new Patient();
    private int id;
    //private 
    @FXML
    private ImageView pfil;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // ObservableList medecindata = FXCollections.observableArrayList();
        ServiceMedecin sm = new ServiceMedecin();
        List<Medecin> medecin = null;

        medecin = sm.Read();

        if (!medecin.isEmpty()) {
            for (int i = 0; i < medecin.size(); i++) {
                medecindata.add(medecin.get(i));
            }
        }

        colid.setCellValueFactory(new PropertyValueFactory<Medecin, Integer>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<Medecin, String>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<Medecin, String>("prenom"));
        coletat.setCellValueFactory(new PropertyValueFactory<Medecin, String>("etat"));
        coldiplome.setCellValueFactory(new PropertyValueFactory<Medecin, String>("diplome"));
        coltel.setCellValueFactory(new PropertyValueFactory<Medecin, String>("telephone"));
        tabmedecin.setItems(medecindata);
        // TODO
    }

    public static void refreshTableView(TableView tableView) {
        ObservableList dataList = FXCollections.observableArrayList();
        ServiceMedecin sm = new ServiceMedecin();
        List<Medecin> medecin = sm.Read();

        if (!medecin.isEmpty()) {
            for (int i = 0; i < medecin.size(); i++) {
                dataList.add(medecin.get(i));
            }
        }
        tableView.getItems().setAll(dataList);
        tableView.refresh();
    }

    public static void refreshTableViewR(TableView tableView) {
        ObservableList dataList = FXCollections.observableArrayList();
        ServiceMedecin sm = new ServiceMedecin();
        List<Medecin> medecin = sm.Read();

        if (!medecin.isEmpty()) {
            for (int i = 0; i < medecin.size(); i++) {
                dataList.add(medecin.get(i));
            }
        }
        tableView.getItems().setAll(dataList);
        tableView.refresh();
    }

    @FXML
    private void validerRdv(ActionEvent event) {
        LocalDate localDate = datepk.getValue();
        if (localDate == null) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veiller entrer une date.");
            alert.showAndWait();
        } else if (m.getEtat() == "Non disponible") {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Impossible de prendre un rendez vous le medecin n'est pas disponible.");
            alert.showAndWait();
        } else {
            ServicePatient sp = new ServicePatient();
            p = sp.VerifierI(id);
            p.setRdv(sp.VerifierRdv(id));
            if (p.getRdv().getEtat() == null) {
                Date date = null;
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                date = Date.from(instant);
                RendezVous rdv = new RendezVous(id, m.getId(), date);
                RdvService rdvs = new RdvService();
                rdvs.Insert(rdv);
                m.setEtat("Non disponible");

                ServiceMedecin sm = new ServiceMedecin();
                sm.ModifierD(m.getEtat(), m.getId());
                MailSender mailSender = new MailSender();
                try {
                    mailSender.sendEmail(m.getEmail(), "Votre rendez vous à été annuler","Approuver");
                } catch (MessagingException ex) {
                    Logger.getLogger(RendezVousInterfaceMController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Demande de rendez-vous envoyer.");
                alert.showAndWait();
                refreshTableView(tabmedecin);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Vous avez une demande en cours.");
                alert.showAndWait();

            }

        }
    }

    @FXML
    private void updateRdv(ActionEvent event) {

        LocalDate localDate = datepk.getValue();
        if (localDate == null) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veiller entrer une date.");
            alert.showAndWait();
        } else {
            ServicePatient sp = new ServicePatient();
            p = sp.VerifierI(id);
            p.setRdv(sp.VerifierRdv(id));
            if (p.getRdv().getEtat() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Vous n'avez effectuer aucune demande.");
                alert.showAndWait();
            } else {
                Date date = null;
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                date = Date.from(instant);
                RdvService rdvs = new RdvService();
                rdvs.ModifierD(date, p.getRdv().getId());
                refreshTableView(tabmedecin);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Date du rendez-vous modifié avec succes.");
                alert.showAndWait();
            }

        }
    }

    @FXML
    private void annulerRdv(ActionEvent event) {
        ServicePatient sp = new ServicePatient();
        p = sp.VerifierI(id);
        p.setRdv(sp.VerifierRdv(id));
        if (p.getRdv().getEtat() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vous n'avez effectuer aucune demande.");
            alert.showAndWait();
        } else {
            if (p.getRdv().getEtat() == "Approuver") {
                RdvService sm = new RdvService();
                p.getRdv().setEtat("Annuler");
                sm.ModifierE(p.getRdv().getEtat(), p.getRdv().getId());
                MailSender mailSender = new MailSender();
                try {
                    mailSender.sendEmail(m.getEmail(), "Votre rendez vous à été annuler","Annuler");
                } catch (MessagingException ex) {
                    Logger.getLogger(RendezVousInterfaceMController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "rendez-vous valider.");
                alert.showAndWait();
            } else if ((p.getRdv().getEtat() == "Annuler") || (p.getRdv().getEtat() == "En attente")) {

                RdvService sm = new RdvService();
                sm.Delete(p.getRdv().getId());
                ServiceMedecin ms = new ServiceMedecin();
                m.setEtat("Disponible");
                ms.ModifierD(m.getEtat(), id);
                refreshTableView(tabmedecin);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "rendez-vous Supprimer.");
                alert.showAndWait();
            }
        }

    }

    @FXML
    private void selectItems(MouseEvent event) {

        m = tabmedecin.getSelectionModel().getSelectedItem();

        // int num = tbUser.getSelectionModel().getSelectedIndex();
        System.out.println(m);
        /*  if ((num - 1)<-6) {
        return;
    }*/
 /*tfNom.setText(u.getNom());
        tfPrenom.setText(u.getPrenom());
        tfUsername.setText(u.getUsername());
        tfEmail.setText(u.getEmail());
        tfMdp.setText(u.getPassword());
        tfTel.setText(Integer.toString(u.getTel()));*/

    }

    @FXML
    private void profil(MouseEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuInterface.fxml"));

                try {
                    ServicePatient sp = new ServicePatient();
        p = sp.VerifierI(id);
                    Parent root = loader.load();
                    MenuInterfaceController mn = loader.getController();
                    mn.setId(p.getId());
                    mn.setLbemail(p.getEmail());
                    mn.setLbnom(p.getNom());
                    mn.setLbprenom(p.getPrenom());
                    mn.setLbtelephone(p.getTelephone());
                    mn.setLbusename(p.getNom());
                    mn.setLbadresse(p.getAdresse());

                    pfil.getScene().setRoot(root);
                    
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
    }

}

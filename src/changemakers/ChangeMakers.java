/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package changemakers;

import changemakers.utils.MyConnection;

import changemakers.entities.Reclamation;
import changemakers.entities.Reponse;
import changemakers.services.ReclamationCrud;
import changemakers.services.ReponseCrud;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author zaiir
 */
public class ChangeMakers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        MyConnection mc = MyConnection.getInstance();
        Reclamation Rec = new Reclamation();
        Reclamation reclamation = new Reclamation();
        Reponse reponse = new Reponse();
        ReclamationCrud rr=new ReclamationCrud();
        String dateRecstr = "2023-03-30";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateRec = null;
        try {
            dateRec = format.parse(dateRecstr);
        } catch (ParseException ex) {
            Logger.getLogger(ChangeMakers.class.getName()).log(Level.SEVERE, null, ex);
        }
        Reclamation rec=new Reclamation(75,"test5100", "nononono", "non", "ceci est ...", dateRec);
         

      
        //String s=ChercherReclamation(260);

    }

    public ChangeMakers() {
    }
}

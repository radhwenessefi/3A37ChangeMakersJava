/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.test;

import com.changemakers.atpeace.entities.Medecin;
import com.changemakers.atpeace.entities.Patient;
import com.changemakers.atpeace.entities.RendezVous;
import com.changemakers.atpeace.services.MailSender;
import com.changemakers.atpeace.services.RdvService;
import com.changemakers.atpeace.services.ServiceMedecin;
import com.changemakers.atpeace.services.ServicePatient;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author gille
 */
public class Myapplication {
    
    public static void main(String[] args) {
             
        /*   try {
        String destinataire = "black2608cat@gmail.com";
        int code = 12345;
        
        MailSender mailSender = new MailSender();
        mailSender.sendEmail(destinataire, "");
        } catch (MessagingException ex) {
        ex.printStackTrace();
        }*/
        
        RdvService sm = new RdvService();
        /* List<RendezVous> rdv = null;
        /*
        rdv = sm.ListRdv(2);
        System.out.println(rdv);*/
        /*ServicePatient sp = new ServicePatient();
        Patient p = sp.VerifierI(1);
        p.setRdv(sp.VerifierRdv(1));
        RendezVous r = p.getRdv();*/
        Medecin m = new Medecin();
        ServiceMedecin ms = new ServiceMedecin();
        m = ms.VerifierD(7);
        System.out.println(m);
        ServicePatient sp = new ServicePatient();
                Patient p = sp.VerifierI(10);
        System.out.println(p);
        //System.out.println(r);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.services;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Kenza
 */
public class MailSender { 
    public static void sendEmail(String destinataire, String mes,String sujet) throws MessagingException {
 String username = "gillesadrien.koueboudjonko@esprit.tn";
            String password ="201JME55102A18";
            System.out.println("Entrain d'envoyer un email de vérification !! ");
            // Etape 1 : Création de la session
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");

            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.port","587");
           
            Session session = Session.getInstance(props,new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);}
            });
           
            Message message = prepareMessage(session,username,destinataire,mes,sujet);
            Transport.send(message);
            System.out.println("Message envoyé !!");
}
       
   

    private static Message prepareMessage(Session session, String username,String destinataire, String mes,String sujet) throws MessagingException {
       
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(destinataire));
            message.setSubject(sujet);
           
            message.setText(mes);
            return message;
        } catch (AddressException ex) {
            Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   
    
}

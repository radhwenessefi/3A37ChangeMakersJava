/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.entities;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class Reclamation {

    private int id_reclamation;
    private String titre_reclamation;
    private String type_reclamation;
    private String etat_reclamation;
    private String description_reclamation;
    private Date date;


    public Reclamation(int id_reclamation, String titre_reclamation, String type_reclamation, String etat_reclamation, String description_reclamation, Date date) {
        this.id_reclamation = id_reclamation;
        this.titre_reclamation = titre_reclamation;
        this.type_reclamation = type_reclamation;
        this.etat_reclamation = etat_reclamation;
        this.description_reclamation = description_reclamation;
        this.date = date;

    }

    public Reclamation(String titre_reclamation, String type_reclamation, String etat_reclamation, String description_reclamation, Date date) {
        this.titre_reclamation = titre_reclamation;
        this.type_reclamation = type_reclamation;
        this.etat_reclamation = etat_reclamation;
        this.description_reclamation = description_reclamation;
        this.date = date;
        
}

public Reclamation() {
    }

    

    public Reclamation(int id_reclamation, String titre_reclamation, String type_reclamation, String etat_reclamation, Date date) {
        this.id_reclamation = id_reclamation;
        this.titre_reclamation = titre_reclamation;
        this.type_reclamation = type_reclamation;
        this.etat_reclamation = etat_reclamation;
        this.date = date;
    }

    public void setDescription_reclamation(String description_reclamation) {
        this.description_reclamation = description_reclamation;
    }

    public String getDescription_reclamation() {
        return description_reclamation;
    }
    private Reponse reponse;

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    
    
    
    
    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    

    public String getTitre_reclamation() {
        return titre_reclamation;
    }

    public void setTitre_reclamation(String titre_reclamation) {
        this.titre_reclamation = titre_reclamation;
    }

    public String getType_reclamation() {
        return type_reclamation;
    }

    public void setType_reclamation(String type_reclamation) {
        this.type_reclamation = type_reclamation;
    }

    public String getEtat_reclamation() {
        return etat_reclamation;
    }

    public void setEtat_reclamation(String etat_reclamation) {
        this.etat_reclamation = etat_reclamation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    
    
}

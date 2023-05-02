/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.entities;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 *
 * @author gille
 */
public class RendezVous {

    private String etat;
    private int id;
    private int id_patient;
    private int id_medecin;
    private Date dateRdv;
    private Patient patient;
    private Medecin medecin;

    public RendezVous() {
    }

    public RendezVous(int id_patient, int id_medecin, Date dateRdv) {
        //  this.etat = etat;
        this.id_patient = id_patient;
        this.id_medecin = id_medecin;
        this.dateRdv = dateRdv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public int getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(int id_medecin) {
        this.id_medecin = id_medecin;
    }

    public Date getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(Date dateRdv) {
        this.dateRdv = dateRdv;
    }
    
    public ZonedDateTime getZoneDate()
    {
                ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(dateRdv.toInstant(), ZoneId.systemDefault());
   return zonedDateTime;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "etat=" + etat + ", id_patient=" + id_patient + ", id_medecin=" + id_medecin + ", dateRdv=" + dateRdv + '}';
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }
    
    

}

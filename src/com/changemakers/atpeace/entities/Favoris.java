/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.entities;

import com.changemakers.atpeace.entites.Regime;
import com.changemakers.atpeace.entities.Patient;

/**
 *
 * @author DELL
 */
public class Favoris {
    int id ;
    Regime regime_id;
    Patient patient;
    int nb_favori;
    int nb_total;
  
    public Favoris() {
    }

    public Favoris(Regime regime_id, Patient patient, int nb_favori, int nb_total) {
        this.regime_id = regime_id;
        this.patient = patient;
        this.nb_favori = nb_favori;
        this.nb_total = nb_total;
    }

    public Favoris(int id, Regime regime_id, Patient patient, int nb_favori, int nb_total) {
        this.id = id;
        this.regime_id = regime_id;
        this.patient = patient;
        this.nb_favori = nb_favori;
        this.nb_total = nb_total;
    }

 

    public Regime getRegime_id() {
        return regime_id;
    }

    public void setRegime_id(Regime regime_id) {
        this.regime_id = regime_id;
    }

   /* public Patient getId_patient() {
        return id_patient;
    }

    public void setId_patient(Patient id_patient) {
        this.id_patient = id_patient;
    }*/

   

 

 

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

   

    public int getNb_favori() {
        return nb_favori;
    }

    public void setNb_favori(int nb_favori) {
        this.nb_favori = nb_favori;
    }

    public int getNb_total() {
        return nb_total;
    }

    public void setNb_total(int nb_total) {
        this.nb_total = nb_total;
    }

    @Override
    public String toString() {
        return "Favoris{" + "id=" + id + ", regime_id=" + regime_id + ", nb_favori=" + nb_favori + ", nb_total=" + nb_total + ", regime_name=" +  '}';
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    

  
    
}

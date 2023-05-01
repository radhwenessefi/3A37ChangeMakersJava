/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.entities;

import java.util.List;

/**
 *
 * @author gille
 */
public class Medecin extends User {
    
    private String diplome;
    private String etat;   
    protected List<RendezVous> rdv;

    public Medecin() {
    }

    public Medecin(int id,String nom, String prenom, String telephone, String adresse, String email, String password,String[] roles,String diplome, String etat) {
        super(id,nom,prenom,telephone,adresse,email,password,roles);
        this.diplome = diplome;
        this.etat = etat;
    }

    public Medecin(String diplome, String etat, String nom, String prenom, String telephone, String adresse, String email, String password) {
        super(nom, prenom, telephone, adresse, email, password);
        this.diplome = diplome;
        this.etat = etat;
    }

    public Medecin(int id,String diplome, String nom, String prenom, String telephone, String adresse, String email) {
        super(id,nom, prenom, telephone, adresse, email);
        this.diplome = diplome;
    }
    
    

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public List<RendezVous> getRdv() {
        return rdv;
    }

    public void setRdv(List<RendezVous> rdv) {
        this.rdv = rdv;
    }

   
   
  
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.entities;

/**
 *
 * @author gille
 */
public class Patient extends User{
      
     private RendezVous rdv;
     

    public Patient() {
    } 
    
    

     // Constructeur sans l'identifiant (utilisé lors de la création d'un nouveau patient)
  /*  public Patient(String nom, String prenom, String telephone, String adresse, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
    }*/
    
   public Patient(int id,String nom, String prenom, String telephone, String adresse, String email, String password) {
        super(id,nom,prenom,telephone,adresse,email,password);
     }

    public Patient(String nom, String prenom, String telephone, String adresse, String email, String password) {
        super(nom, prenom, telephone, adresse, email, password);
    }

    public Patient(int id, String nom, String prenom, String telephone, String adresse, String email) {
        super(id, nom, prenom, telephone, adresse, email);
    }
   
   
    
      // Constructeur avec tous les attributs (utilisé lors de la récupération d'un patient existant)
    public Patient(int id, String nom, String prenom, String telephone, String adresse, String email, String password, String[] roles) {
         super(id,nom,prenom,telephone,adresse,email,password,roles);
    }
 
    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", adresse=" + adresse + ", email=" + email + ", password=" + password + ", role=" + role + '}';
    }

    public RendezVous getRdv() {
        return rdv;
    }

    public void setRdv(RendezVous rdv) {
        this.rdv = rdv;
    }
    
    
    
    
}

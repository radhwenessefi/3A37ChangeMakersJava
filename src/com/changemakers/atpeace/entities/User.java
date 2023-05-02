/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author gille
 */
public class User {
     protected int id;
    protected String nom;
    //  protected StringProperty nom;
    protected String prenom;
    protected String telephone;
    protected String adresse;
    protected String email;
    protected String password;
    protected String[] role ;
    
    
 
    public User() {
    }
    
     /*public User(String nom) {
        this.nom = new SimpleStringProperty(nom);
    }
     
      public StringProperty nomProperty() {
        return nom;
    }*/
      
    public User(int id,String nom, String prenom, String telephone, String adresse, String email, String password) {
       // this.nom.set(nom);
       this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.id = id;
     
     }
    
     public User(String nom, String prenom, String telephone, String adresse, String email, String password) {
        this.nom = nom;
      // this.nom.set(nom);
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
    }
     
      public User(int id, String nom, String prenom, String telephone, String adresse, String email, String password, String[] roles) {
        this.id = id;
        this.nom = nom;
       //  this.nom.set(nom);
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.role = roles;
    }

    public User(int id, String nom, String prenom, String telephone, String adresse, String email) {
        this.id = id;
       this.nom = nom;
       // this.nom.set(nom);
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.email = email;
    }

    /*public User(int id,String nom, String prenom, String telephone, String adresse, String email) {
         this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.email = email;
    }*/
      
      
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        //return nom.get();
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
      //  this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getRole() {
        return role;
    }

    public void setRole(String[] role) {
        this.role = role;
    }

    
    
}

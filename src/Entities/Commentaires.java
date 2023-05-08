/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author khaled
 */
public class Commentaires {


    int id;
     private int poste_id;
     String continueCommentaire;

    public Commentaires() {
    }

    public Commentaires(int id, int poste_id, String continueCommentaire) {
        this.id = id;
        this.poste_id = poste_id;
        this.continueCommentaire = continueCommentaire;
    }

    public Commentaires(int poste_id, String continueCommentaire) {
        this.poste_id = poste_id;
        this.continueCommentaire = continueCommentaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoste_id() {
        return poste_id;
    }

    public void setPoste_id(int poste_id) {
        this.poste_id = poste_id;
    }

    public String getContinueCommentaire() {
        return continueCommentaire;
    }

    public void setContinueCommentaire(String continueCommentaire) {
        this.continueCommentaire = continueCommentaire;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", poste_id=" + poste_id + ", continueCommentaire=" + continueCommentaire + '}';
    }
 
   
   
}



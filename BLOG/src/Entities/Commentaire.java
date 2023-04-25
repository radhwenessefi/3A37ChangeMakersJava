/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author khaled
 */
public class Commentaire {


    private int id;
    private String continueCommentaire;
    private Poste poste;

    public Commentaire() {}

    public Commentaire(int id, String continueCommentaire, Poste poste) {
        this.id = id;
        this.continueCommentaire = continueCommentaire;
        this.poste = poste;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContinueCommentaire() {
        return continueCommentaire;
    }

    public void setContinueCommentaire(String continueCommentaire) {
        this.continueCommentaire = continueCommentaire;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }
}



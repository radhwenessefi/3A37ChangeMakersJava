/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.entites;

/**
 *
 * @author DELL
 */
public class Sport {
    int id ;
    String titre;
    String discription ;
    String niveaux;
    String image;
    String level;

    public Sport() {
    }

    public Sport(int id, String titre, String discription, String niveaux, String image, String level) {
        this.id = id;
        this.titre = titre;
        this.discription = discription;
        this.niveaux = niveaux;
        this.image = image;
        this.level = level;
    }

    public Sport(String titre, String discription, String niveaux, String image, String level) {
        this.titre = titre;
        this.discription = discription;
        this.niveaux = niveaux;
        this.image = image;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDiscription() {
        return discription;
    }

    public String getNiveaux() {
        return niveaux;
    }

    public String getImage() {
        return image;
    }

    public String getLevel() {
        return level;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setNiveaux(String niveaux) {
        this.niveaux = niveaux;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Sport{" + "id=" + id + ", titre=" + titre + ", discription=" + discription + ", niveaux=" + niveaux + ", image=" + image + ", level=" + level + '}';
    }
    
}

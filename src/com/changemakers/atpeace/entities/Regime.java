/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changemakers.atpeace.entites;

/**
 *
 * @author bhk
 */
public class Regime {
    private int id ;
    private String title;
    private String discription;
    private String liste_alement;
    private String image;
    private String[] sports;
    private String level;

    public Regime() {
    }

    public Regime( String title, String discription, String liste_alement,String image, String level) {
      
        this.title = title;
        this.discription = discription;
        this.liste_alement = liste_alement;
        this.image = image;
        this.level = level;
    }

    public Regime(int id, String title, String discription, String liste_alement, String image, String level) {
        this.id = id;
        this.title = title;
        this.discription = discription;
        this.liste_alement = liste_alement;
        this.image = image;
        
        this.level = level;
    }

   
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getListe_alement() {
        return liste_alement;
    }

    public void setListe_alement(String liste_alement) {
        this.liste_alement = liste_alement;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getSports() {
        return sports;
    }

    public void setSports(String[] sports) {
        this.sports = sports;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Regime{" + "id=" + id + ", title=" + title + ", discription=" + discription + ", liste_alement=" + liste_alement + ", image=" + image + ", sports=" + sports + ", level=" + level + '}';
    }

  
    
    
}

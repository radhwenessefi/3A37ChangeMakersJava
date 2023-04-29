/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.entites;

/**
 *
 * @author DELL
 */
public class Favoris {
    int id ;
    int regime_id;
    int nb_favori;
    int nb_total;
    String regime_name;

    public Favoris() {
    }

    public Favoris(int id, int regime_id, int nb_favori, int nb_total, String regime_name) {
        this.id = id;
        this.regime_id = regime_id;
        this.nb_favori = nb_favori;
        this.nb_total = nb_total;
        this.regime_name = regime_name;
    }

    public Favoris(int regime_id, int nb_favori, int nb_total, String regime_name) {
        this.regime_id = regime_id;
        this.nb_favori = nb_favori;
        this.nb_total = nb_total;
        this.regime_name = regime_name;
    }

    public String getRegime_name() {
        return regime_name;
    }

    public void setRegime_name(String regime_name) {
        this.regime_name = regime_name;
    }

 

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegime_id() {
        return regime_id;
    }

    public void setRegime_id(int regime_id) {
        this.regime_id = regime_id;
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
        return "Favoris{" + "id=" + id + ", regime_id=" + regime_id + ", nb_favori=" + nb_favori + ", nb_total=" + nb_total + ", regime_name=" + regime_name + '}';
    }
    

  
    
}

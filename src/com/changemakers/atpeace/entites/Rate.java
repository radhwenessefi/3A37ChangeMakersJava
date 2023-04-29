/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.changemakers.atpeace.entites;

/**
 *
 * @author DELL
 */
public class Rate {
    int id ;
    int regime_id;
    int rating;
    int num_totale;
    int nub_of_rate;
    String name_regime;

    public Rate() {
    }

    public Rate(int id, int regime_id, int rating, int num_totale, int nub_of_rate, String name_regime) {
        this.id = id;
        this.regime_id = regime_id;
        this.rating = rating;
        this.num_totale = num_totale;
        this.nub_of_rate = nub_of_rate;
        this.name_regime = name_regime;
    }

    public Rate(int regime_id, int rating, int num_totale, int nub_of_rate, String name_regime) {
        this.regime_id = regime_id;
        this.rating = rating;
        this.num_totale = num_totale;
        this.nub_of_rate = nub_of_rate;
        this.name_regime = name_regime;
    }

    public String getName_regime() {
        return name_regime;
    }

    public void setName_regime(String name_regime) {
        this.name_regime = name_regime;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNum_totale() {
        return num_totale;
    }

    public void setNum_totale(int num_totale) {
        this.num_totale = num_totale;
    }

    public int getNub_of_rate() {
        return nub_of_rate;
    }

    public void setNub_of_rate(int nub_of_rate) {
        this.nub_of_rate = nub_of_rate;
    }

    @Override
    public String toString() {
        return "Rate{" + "id=" + id + ", regime_id=" + regime_id + ", rating=" + rating + ", num_totale=" + num_totale + ", nub_of_rate=" + nub_of_rate + ", name_regime=" + name_regime + '}';
    }

  
    
}

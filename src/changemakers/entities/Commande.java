/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package changemakers.entities;
import java.util.Date;

/**
 *
 * @author zaiir
 */
public class Commande {
    private  int id_commande ;
    private float frais_commande ;
    private Date date_livraison ;
    private String etat;
    private  int quantite_livre ;
    
    public Commande() {
    }

    public Commande(int id_commande, float frais_commande, Date date_livraison, String etat, int quantite_livre) {
        this.id_commande = id_commande;
        this.frais_commande = frais_commande;
        this.date_livraison = date_livraison;
        this.etat = etat;
        this.quantite_livre = quantite_livre;
    }

 
      
      
      

    public int getId_commande() {
        return id_commande;
    }

    public float getFrais_commande() {
        return frais_commande;
    }

  

    public String getEtat() {
        return etat;
    }

    public int getQuantite_livre() {
        return quantite_livre;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setFrais_commande(float frais_commande) {
        this.frais_commande = frais_commande;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }



    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setQuantite_livre(int quantite_livre) {
        this.quantite_livre = quantite_livre;
    }
     
      
      
     
    
}

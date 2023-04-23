/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package changemakers.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author zaiir
 */
public class Commande {

    private int id_commande;
    private float frais_commande;
    private Date date_livraison;
    private String etat;
    private List<ProduitCommande> produits_commandes;

    public Commande(int id_commande, float frais_commande, Date date_livraison, String etat) {
        this.id_commande = id_commande;
        this.frais_commande = frais_commande;
        this.date_livraison = date_livraison;
        this.etat = etat;
        this.produits_commandes = new ArrayList<>();
    }

    public Commande(float parseFloat, Timestamp dateLivraison, String selectedEtat) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId_commande() {
        return id_commande;
    }

    public float getFrais_commande() {
        return frais_commande;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public String getEtat() {
        return etat;
    }

    public void ajouterProduit(Produit produit, int quantite) {
        boolean produitExistant = false;
        for (ProduitCommande produitCommande : produits_commandes) {
            if (produitCommande.getProduit().equals(produit)) {
                produitCommande.setQuantite(produitCommande.getQuantite() + quantite);
                produitExistant = true;
                break;
            }
        }

        if (!produitExistant) {
            produits_commandes.add(new ProduitCommande(produit, quantite));
        }

        produit.decrementerQuantite(quantite);
    }

    public List<ProduitCommande> getProduits_commandes() {
        return produits_commandes;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Commande(float frais_commande, Date date_livraison, String etat) {
        this.frais_commande = frais_commande;
        this.date_livraison = date_livraison;
        this.etat = etat;
    }

    public Commande() {
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public void setProduits_commandes(List<ProduitCommande> produits_commandes) {
        this.produits_commandes = produits_commandes;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setFrais_commande(float frais_commande) {
        this.frais_commande = frais_commande;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package changemakers.entities;

/**
 *
 * @author zaiir
 */
public class Produit {
    private  int id_produit ;
    private String nom_produit ;
    private float prix_produit ;
    private  int quantite_produit ;
    private String categorie_produit;
    private String image_produit ;
    private String description;
 
    
    public Produit() {
    }
    
    
    
    public Produit(int id_produit, String nom_produit, float prix_produit, int quantite_produit, String categorie_produit, String image_produit, String description) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.categorie_produit = categorie_produit;
        this.image_produit = image_produit;
        this.description = description;
    }
    
    public int getId_produit() {
        return id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public float getPrix_produit() {
        return prix_produit;
    }

    public int getQuantite_produit() {
        return quantite_produit;
    }

    public String getCategorie_produit() {
        return categorie_produit;
    }

    public String getImage_produit() {
        return image_produit;
    }

    public String getDescription() {
        return description;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public void setPrix_produit(float prix_produit) {
        this.prix_produit = prix_produit;
    }

    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
    }

    public void setCategorie_produit(String categorie_produit) {
        this.categorie_produit = categorie_produit;
    }
          @Override
    public String toString() {
        return "Produit{" + "Id=" + id_produit + ", NomProduit=" + nom_produit + ", PrixProd=" + prix_produit +  ", CategorieProd=" + categorie_produit + ", Desc=" + description + ", image=" + image_produit + '}';
    }

    public void setImage_produit(String image_produit) {
        this.image_produit = image_produit;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
  
    
    

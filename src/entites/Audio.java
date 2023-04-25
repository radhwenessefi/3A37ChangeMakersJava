/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

/**
 *
 * @author dorra
 */
public class Audio {
    private int id ;
    private String titre;
    private String type;
    private int rating;
    private String image;
    private String fichier;
    int like;
    int dislike;

    public Audio(int id, String titre, String type, int rating, String image, String fichier, int like, int dislike) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.rating = rating;
        this.image = image;
        this.fichier = fichier;
        this.like = like;
        this.dislike = dislike;
    }

    public Audio(String titre, String type, int rating, String image, String fichier, int like, int dislike) {
        this.titre = titre;
        this.type = type;
        this.rating = rating;
        this.image = image;
        this.fichier = fichier;
        this.like = like;
        this.dislike = dislike;
    }

    public Audio(String titre, String type, String image, String fichier) {
        this.titre = titre;
        this.type = type;
       
        this.image = image;
        this.fichier = fichier;
    }
    

    public Audio() {
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getType() {
        return type;
    }

    public int getRating() {
        return rating;
    }

    public String getImage() {
        return image;
    }

    public String getFichier() {
        return fichier;
    }

    public Integer getLike() {
        return like;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    @Override
    public String toString() {
        return "Audio{" + "id=" + id + ", titre=" + titre + ", type=" + type + ", rating=" + rating + ", image=" + image + ", fichier=" + fichier + ", like=" + like + ", dislike=" + dislike + '}';
    }
   
    
}


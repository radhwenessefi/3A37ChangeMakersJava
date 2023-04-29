/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

/**
 *
 * @author dorra
 */
public class Video {
    private int id ;
    private String titre;
   private String description;
     private String video;
    
    private String image;
    private int rating;
  
    int like;
    int dislike;

    public Video(int id, String titre, String description, String video, String image, int rating, int like, int dislike) {
        this.id = id;
        this.titre = titre;
        this.description=description;
        this.video=video;
        this.image = image;
        
        this.rating = rating;
        this.like = like;
        this.dislike = dislike;
    }

    public Video(String titre, String description, String video, String image,int rating, int like, int dislike) {
        this.titre = titre;
        this.description=description;
       this.video=video;
        this.image = image;
         this.rating = rating;
        
        this.like = like;
        this.dislike = dislike;
    }

    public Video(String titre, String description, String video, String image) {
        this.titre = titre;
        this.description=description;
       this.video=video;
        this.image = image;
        
    }
    

    public Video() {
    }

    public Video(String titre, String type, int rating, String image, String fichier, int like, int dislike) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }
    public String getVideo() {
        return video;
    }
    
     public String getImage() {
        return image;
    }

    public int getRating() {
        return rating;
    }

   

    

    public int getLike() {
        return like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description=description;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
     public void setVideo(String videor) {
        this.video=video;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    

   

    public void setLike(int like) {
        this.like = like;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    @Override
    public String toString() {
        return "Video{" + "id=" + id + ", titre=" + titre + ", description=" + description +", image=" + image + ", video=" + video + ", rating=" + rating +  ", like=" + like + ", dislike=" + dislike + '}';
    }
   
    
}


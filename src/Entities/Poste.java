
package Entities;

import javafx.scene.image.Image;

/**
 *
 * @author khaled
 */
public class Poste {


        private int id;
    private String titre;
    private String dicription;
    private String image;
   // private String continueCommentaireField2;

    
    public Poste() {}

    public Poste(int id, String titre, String description, String image) {
        this.id = id;
        this.titre = titre;
        this.dicription = description;
        this.image = image;
    }

    public Poste(String titre, String description, String image) {
         
        this.titre = titre;
        this.dicription = description;
        this.image = image;
    }

   
    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return dicription;
    }

    public void setDescription(String description) {
        this.dicription = description;
    }
    
    public void setImage(String image) {
        this.image = image;
    }

        public String getImage() {
        return dicription;
    }
        
 
        
    public void setId(int aInt) {
        this.id=aInt;
    }
    


}


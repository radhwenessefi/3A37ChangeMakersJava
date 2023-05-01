/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package changemakers.gui;

import changemakers.entities.Reclamation;

/**
 *
 * @author LENOVO
 */
public class CombinedData {
    private Reclamation reclamation;
    private String titre;

    public CombinedData(Reclamation reclamation, String titre) {
        this.reclamation = reclamation;
        this.titre = titre;
    }

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}

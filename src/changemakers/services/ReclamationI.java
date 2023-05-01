/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package changemakers.services;

import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface ReclamationI<T> {

    List<T> ChercherReclamation(String titre);
     // Recupérer tous les réclamations
     List<T> ChercherReclamation();
          List<T> ChercherReclamationByTitel( int id);

    void updateReclamation(T r);

    void supprimerReclamation(String titre);

    void ajouterReclamation(T c);
}

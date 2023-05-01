/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package changemakers.entities;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author SAMI
 */
@Entity
@Table(name = "reponse")
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_reponse;
    private Date date_reponse;
    private String solution_reponse;
    private int reclamation_id;

    

    public Reponse(int id_reponse, Date date_reponse, String solution_reponse) {
        this.id_reponse = id_reponse;
        this.date_reponse = date_reponse;
        this.solution_reponse = solution_reponse;
    }

    public Reponse() {

    }

    public Reponse(int id_reponse, Date date_reponse, String solution_reponse, int reclamation_id) {
        this.id_reponse = id_reponse;
        this.date_reponse = date_reponse;
        this.solution_reponse = solution_reponse;
        this.reclamation_id = reclamation_id;
    }

    public Reponse(Date date_reponse, String solution_reponse) {
        this.date_reponse = date_reponse;
        this.solution_reponse = solution_reponse;
    }

    public int getId_reponse() {
        return id_reponse;
    }

    public Date getDate_reponse() {
        return date_reponse;
    }

    public String getSolution_reponse() {
        return solution_reponse;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public void setDate_reponse(Date date_reponse) {
        this.date_reponse = date_reponse;
    }

    public void setSolution_reponse(String solution_reponse) {
        this.solution_reponse = solution_reponse;
    }

    

    public void setReclamation_id(int reclamation_id) {
        this.reclamation_id = reclamation_id;
    }

    public int getReclamation_id() {
        return reclamation_id;
    }

    public Reponse(Date date_reponse, String solution_reponse, int reclamation_id) {
        this.date_reponse = date_reponse;
        this.solution_reponse = solution_reponse;
        this.reclamation_id = reclamation_id;
    }

    
}

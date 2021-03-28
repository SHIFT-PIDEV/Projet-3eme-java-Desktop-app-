/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Timestamp;

/**
 *
 * @author mahdi
 */
public class InscripExam {
     private int idinscri;
    private int idClient;
    private int idExam;
    private Timestamp dateInscri;
    private String nom;
     private String prenom;
      private String email;

    public int getIdinscri() {
        return idinscri;
    }

    public void setIdinscri(int idinscri) {
        this.idinscri = idinscri;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdExam() {
        return idExam;
    }

    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }

    public Timestamp getDateInscri() {
        return dateInscri;
    }

    public void setDateInscri(Timestamp dateInscri) {
        this.dateInscri = dateInscri;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "InscripExam{" + "idinscri=" + idinscri + ", idClient=" + idClient + ", idExam=" + idExam + ", dateInscri=" + dateInscri + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + '}';
    }
    
      
      
    
}

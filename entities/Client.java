/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author asus
 */
public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String userName;
    private String mdp;
    private String email;
    private int formateur ;

    public Client() {
    }

    public Client(int id, String nom, String prenom, String userName, String mdp, String email,int formateur) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.userName = userName;
        this.mdp = mdp;
        this.email = email;
        this.formateur=formateur ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFormateur() {
        return formateur;
    }

    public void setFormateur(int formateur) {
        this.formateur = formateur;
    }
    

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", userName=" + userName + ", mdp=" + mdp + ", email=" + email + '}';
    }
    
}

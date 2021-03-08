/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Entities;

/**
 *
 * @author Fedy
 */
public class PaymentMethod {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String pays;
    private int codepostal;
    private int num;
    private String datecarte;
    private int cvc;
    
    
    public PaymentMethod() {
    }

    public PaymentMethod(int id, String nom, String prenom, String email, String pays, int codepostal, int num, String datecarte, int cvc) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pays = pays;
        this.codepostal = codepostal;
        this.num = num;
        this.datecarte = datecarte;
        this.cvc = cvc;
    }

    public PaymentMethod(String nom, String prenom, String email, String pays, int codepostal, int num, String datecarte, int cvc) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pays = pays;
        this.codepostal = codepostal;
        this.num = num;
        this.datecarte = datecarte;
        this.cvc = cvc;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPays() {
        return pays;
    }

    public int getCodepostal() {
        return codepostal;
    }

    public int getNum() {
        return num;
    }

    public String getDatecarte() {
        return datecarte;
    }

    public int getCvc() {
        return cvc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setCodepostal(int codepostal) {
        this.codepostal = codepostal;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDatecarte(String datecarte) {
        this.datecarte = datecarte;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", pays=" + pays + ", codepostal=" + codepostal + ", num=" + num + ", datecarte=" + datecarte + ", cvc=" + cvc + '}';
    }

    
   
    
}

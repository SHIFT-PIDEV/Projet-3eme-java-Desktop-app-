/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Entities;

import java.util.Objects;

/**
 *
 * @author Fedy
 */
public class Panier {
    
    
    private int  id;
    private String nom;
    private String prix;
    
    public Panier () {
    
}
    public Panier (int id, String nom,String prix){
        this.id = id;
        this.nom = nom;
        this.prix = prix;
       
    }
    public Panier ( String nom, String prix ){
          this.nom  = nom;
          this.prix= prix;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrix() {
        return prix;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
    
        return hash;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + '}';
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Panier other = (Panier) obj;
        return Objects.equals(this.id, other.id);
    }     
}

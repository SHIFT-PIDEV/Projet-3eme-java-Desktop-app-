/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Entities;

import java.util.Objects;

/**
 *
 * @author Aziz
 */
public class categorie {
          private int  id;
    private String nomcategorie;
    
    public categorie () {
    
}
    public categorie (int id , String nomcategorie){
            this.id = id;
        this.nomcategorie = nomcategorie;
       
    }
    public categorie ( String nomcategorie ){
          this.nomcategorie  = nomcategorie;
    }

    public int getId() {
        return id;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

  

   
   
   
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
    
        return hash;
    }

    @Override
    public String toString() {
        return "categorie{" + "id=" + id + ", nomcategorie=" + nomcategorie + '}';
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
        final categorie other = (categorie) obj;
        return Objects.equals(this.id, other.id);
    }

    public void getnomcategorie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getNomcategorieProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Id() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

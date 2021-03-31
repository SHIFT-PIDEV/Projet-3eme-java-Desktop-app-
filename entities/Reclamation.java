/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author wiemhjiri
 */
public class Reclamation {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty objet;
    private SimpleStringProperty description;

    public Reclamation() {
    }

    public Reclamation(int id, String objet, String description) {
        this.id =new SimpleIntegerProperty(id);
        this.objet =  new SimpleStringProperty(objet);
        this.description =  new SimpleStringProperty(description);
    }
    
    public Reclamation( String objet, String description) {
  
        this.objet =  new SimpleStringProperty(objet);
        this.description =  new SimpleStringProperty(description);
    }

    public int getId() {
        return id.get();
    }

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }
 public SimpleIntegerProperty getIdProperty(){
        return id;
    }
    public String getObjet() {
        return objet.get();
    }
   public String getDescription() {
        return description.get();
    }
    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }

   
    public SimpleStringProperty getDescriptionProperty(){
        return description;
    }
    public SimpleStringProperty getobjetProperty(){
        return objet;
    }
    public void setObjet(String objet) {
        this.objet = new SimpleStringProperty(objet);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
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
        final Reclamation other = (Reclamation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", objet=" + objet + ", description=" + description + '}';
    }

   
    
}

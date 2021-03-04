/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.util.Date;
//import java.util.Date;

/**
 *
 * @author asus
 */
public class Event {
    private int idE;
    private int idF;
    private String nomE;
    private LocalDate dateD;
    private float duree;
    private String descE;
    
    public Event(){
        
    }
    public Event(int idF, String nomE, float duree, String descE) {
        this.idF = idF;
        this.nomE = nomE;
        
        this.duree = duree;
        this.descE = descE;
    }
    public Event(int idF, String nomE, LocalDate dateD, float duree, String descE) {
        this.idF = idF;
        this.nomE = nomE;
        this.dateD = dateD;
        this.duree = duree;
        this.descE = descE;
    }

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public String getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public LocalDate getDateD() {
        return dateD;
    }

    public void setDateD(LocalDate dateD) {
        this.dateD = dateD;
    }

    public float getDuree() {
        return duree;
    }

    public void setDuree(float duree) {
        this.duree = duree;
    }

    public String getDescE() {
        return descE;
    }

    public void setDescE(String descE) {
        this.descE = descE;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.idE;
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
        final Event other = (Event) obj;
        if (this.idE != other.idE) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Event{" + "idE=" + idE + ", idF=" + idF + ", nomE=" + nomE + ", dateD=" + dateD + ", duree=" + duree + ", descE=" + descE + '}';
    }
    
    
}

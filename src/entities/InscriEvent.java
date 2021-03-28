/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 *
 * @author asus
 */
public class InscriEvent {
    private int idinscri;
    private int idClient;
    private int idEvent;
    private Timestamp dateInscri;
    
    public InscriEvent(){
        
    }

    public InscriEvent(int idinscri, int idClient, int idEvent, Timestamp dateInscri) {
        this.idinscri = idinscri;
        this.idClient = idClient;
        this.idEvent = idEvent;
        this.dateInscri = dateInscri;
    }

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

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public Timestamp getDateInscri() {
        return dateInscri;
    }

    public void setDateInscri(Timestamp dateInscri) {
        this.dateInscri = dateInscri;
    }

    @Override
    public String toString() {
        return "InscriEvent{" + "idinscri=" + idinscri + ", idClient=" + idClient + ", idEvent=" + idEvent + ", dateInscri=" + dateInscri + '}';
    }
    
    
}

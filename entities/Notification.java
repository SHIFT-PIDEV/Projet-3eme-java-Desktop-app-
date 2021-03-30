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
public class Notification {
    private int idcD;
    private int idcS;
    private int idevent;

    public Notification() {
    }

    public int getIdcD() {
        return idcD;
    }

    public void setIdcD(int idcD) {
        this.idcD = idcD;
    }

    public int getIdcS() {
        return idcS;
    }

    public void setIdcS(int idcS) {
        this.idcS = idcS;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    @Override
    public String toString() {
        return "notificaton{" + "idcD=" + idcD + ", idcS=" + idcS + ", idevent=" + idevent + '}';
    }
    
}

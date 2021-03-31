/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Fedy
 */
public class Panier {
    
    
    private int id ;
    private int idcour;
    private int idclient;
    
    public Panier(){
        
    }
    public Panier(int id, int idcour, int idclient) {
        this.id = id;
        this.idcour = idcour;
        this.idclient = idclient;
    }

    public Panier(int idcour, int idclient) {
        this.idcour = idcour;
        this.idclient = idclient;
    }

    public int getId() {
        return id;
    }

    public int getIdcour() {
        return idcour;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdcour(int idcour) {
        this.idcour = idcour;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", idcour=" + idcour + ", idclient=" + idclient + '}';
    }
    
    
    
}

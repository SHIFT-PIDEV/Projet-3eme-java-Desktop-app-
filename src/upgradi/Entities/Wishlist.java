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
public class Wishlist {
    private int id;
    private int idcour;

    public Wishlist(int id, int idcour) {
        this.id = id;
        this.idcour = idcour;
    }

    public Wishlist(int idcour) {
        this.idcour = idcour;
    }

    public int getId() {
        return id;
    }

    public int getIdcour() {
        return idcour;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdcour(int idcour) {
        this.idcour = idcour;
    }

    @Override
    public String toString() {
        return "Wishlist{" + "id=" + id + ", idcour=" + idcour + '}';
    }
    
}

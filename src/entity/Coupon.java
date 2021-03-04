/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;

/**
 *
 * @author pc
 */
public class Coupon {
      private int id;
    private String type;
    private String validite;

    public Coupon() {
    }

    public Coupon(String type, String validite) {
        this.type = type;
        this.validite = validite;
    }

    
    
    public Coupon(int id, String type, String validite) {
        this.id = id;
        this.type = type;
        this.validite = validite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValidite() {
        return validite;
    }

    public void setValidite(String validite) {
        this.validite = validite;
    }

    @Override
    public String toString() {
        return "Coupon{" + "id=" + id + ", type=" + type + ", validit\u00e9=" + validite + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.type);
        hash = 83 * hash + Objects.hashCode(this.validite);
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
        final Coupon other = (Coupon) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.validite, other.validite)) {
            return false;
        }
        return true;
    }
    
    
}

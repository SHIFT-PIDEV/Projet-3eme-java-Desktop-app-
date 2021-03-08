/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Entities;

import java.util.Date;

/**
 *
 * @author Fedy
 */
public class cmd {
    private int id;
    private String username;
    private String nomCour;
    private Date cmdDate;
    private String paymentMethod;

    public cmd(int id, String username, String nomCour, Date cmdDate, String paymentMethod) {
        this.id = id;
        this.username = username;
        this.nomCour = nomCour;
        this.cmdDate = cmdDate;
        this.paymentMethod = paymentMethod;
    }

    public cmd(String username, String nomCour, Date cmdDate, String paymentMethod) {
        this.username = username;
        this.nomCour = nomCour;
        this.cmdDate = cmdDate;
        this.paymentMethod = paymentMethod;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNomCour() {
        return nomCour;
    }

    public Date getCmdDate() {
        return cmdDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNomCour(String nomCour) {
        this.nomCour = nomCour;
    }

    public void setCmdDate(Date cmdDate) {
        this.cmdDate = cmdDate;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "cmd{" + "id=" + id + ", username=" + username + ", nomCour=" + nomCour + ", cmdDate=" + cmdDate + ", paymentMethod=" + paymentMethod + '}';
    }
    
}

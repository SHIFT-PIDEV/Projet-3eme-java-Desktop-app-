/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Panier;
import utils.DbConnexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fedy
 */
public class PanierService {

    private static PanierService instance;
    private Statement st;
    private ResultSet rs;
    private ResultSet rs1;
    private Statement st1;

    private PanierService() {
        DbConnexion cs = DbConnexion.getInstance();
        try {
            st = cs.getConnection().createStatement();
            st1 = cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static PanierService getInstance() {
        if (instance == null) {
            instance = new PanierService();
        }
        return instance;

    }

    public void insert(Panier i) {

        String req = "insert into panier(idcour,idclient) values('" + i.getIdcour() + "','" + i.getIdclient() + "') ";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Panier displayeByIdcourIdclient(int idcour, int idclient) {
        String req = "select * from panier where idcour='" + idcour + "'&&idclient='" + idclient + "'";
        Panier ie = new Panier();
        try {

            rs = st.executeQuery(req);
            rs.next();
            ie.setId(rs.getInt(1));
            System.out.println(" teeeee" + ie.getId());
        } catch (SQLException ex) {
            ie.setId(-1);
        }

        return ie;
    }

    public void ViderPanier(int idcour, int idclient) {
        String req = "delete from panier where idcour='" + idcour + "'&&idclient='" + idclient + "'";

        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ViderbyID(int id) {
        String req = "delete from panier where id='" + id + "'";

        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ViderAll(int idclient) {
        String req = "delete from panier where idclient='" + idclient + "'";

        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void delete() {
        String req = "delete from panier ";

        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Panier> displayAll() {
        String req = "select * from panier";
        List<Panier> list = new ArrayList<>();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Panier p = new Panier();
                p.setId(rs.getInt(1));
                p.setIdcour(rs.getInt(2));
                p.setIdclient(rs.getInt(3));

                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Panier> displayByIdClient(int idClient) {
        String req = "select * from panier where idclient='" + idClient + "' ";

        List<Panier> list = new ArrayList<>();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Panier i = new Panier();
                i.setId(rs.getInt(1));
                i.setIdcour(rs.getInt(2));
                i.setIdclient(rs.getInt(3));

                list.add(i);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

}

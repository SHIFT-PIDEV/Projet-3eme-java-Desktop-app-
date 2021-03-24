/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import upgradi.Entities.Panier;
import upgradi.Entities.Wishlist;
import upgradi.Utils.connexionBD;

/**
 *
 * @author Fedy
 */
public class WishlistService implements IserviceW<Wishlist> {

    private static WishlistService instance;
    private Statement st;
    private ResultSet rs;

    private WishlistService() {
        connexionBD cs = connexionBD.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(WishlistService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static WishlistService getInstance() {
        if (instance == null) {
            instance = new WishlistService();
        }
        return instance;
    }

    @Override
    public void insert(Panier o) {
        //String reqp= "select id from panier";
        String req = " insert into wishlist (courid) values ('" + o.getId() + "')";

        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Panier> displayAll(Panier p) {
         ObservableList<Panier> list = FXCollections.observableArrayList();
         //String reqp= "select id from panier";
         String req = "select * from panier";
        try {
            rs = st.executeQuery(req);
            while(rs.next()){
                //PanierService ps = new PanierService();
                Panier a = new Panier();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString("nom"));
                a.setPrix(rs.getString("prixTotal"));

                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(WishlistService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Wishlist displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

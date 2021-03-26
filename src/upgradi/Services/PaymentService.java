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
import upgradi.Entities.PaymentMethod;
import upgradi.Utils.connexionBD;

/**
 *
 * @author Fedy
 */
public class PaymentService implements IservicePayment<PaymentMethod> {

    private static PaymentService instance;
    private Statement st;
    private ResultSet rs;

    private PaymentService() {
        connexionBD cs = connexionBD.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PaymentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static PaymentService getInstance() {
        if (instance == null) {
            instance = new PaymentService();
        }
        return instance;

    }

    @Override
    public void insert(PaymentMethod o) {
        String req = " insert into paymentmethod (nom,prenom,email,pays,codepostal,numcarte,cvc,datecarte) values ('" + o.getNom() + "','" + o.getPrenom() + "','" + o.getEmail() + "','" + o.getPays() + "','" + o.getCodepostal() + "','" + o.getNum() + "','" + o.getCvc() + "','" + o.getDatecarte() + "')";

        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete() {
        String req = "delete from paymentmethod";

        try {

            st.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(PaymentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteB() {
        String req = "delete from paymentmethod";

        try {

            st.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(PaymentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<PaymentMethod> displayAll() {
        String req = "select * from paymentmethod";
        ObservableList<PaymentMethod> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                PaymentMethod a = new PaymentMethod();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("prenom"));
                a.setEmail(rs.getString("email"));
                a.setPays(rs.getString("pays"));
                a.setCodepostal(rs.getInt("codepostal"));
                a.setNum(rs.getInt("numcarte"));
                a.setCvc(rs.getInt("cvc"));
                a.setDatecarte(rs.getString("datecarte"));

                list.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public PaymentMethod displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

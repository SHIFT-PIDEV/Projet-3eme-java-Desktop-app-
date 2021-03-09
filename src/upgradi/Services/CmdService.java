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
import upgradi.Entities.cmd;
import upgradi.Utils.connexionBD;

/**
 *
 * @author Fedy
 */
public class CmdService implements Iservice<cmd>{

    private static CmdService  instance;
    private Statement st;
    private ResultSet rs;
    
    private CmdService(){
           connexionBD cs=connexionBD.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static CmdService getInstance() {
            if(instance==null) 
            instance=new CmdService();
        return instance;
        
    }
    
    @Override
    public void insert(cmd o) {
        String req = " insert into cmd (username,nomcour,cmdDate,paymentMethod) values ('"+o.getUsername()+"','"+o.getNomCour()+"','"+o.getCmdDate()+"','"+o.getPaymentMethod()+"')";
     
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
    public List<cmd> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public cmd displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

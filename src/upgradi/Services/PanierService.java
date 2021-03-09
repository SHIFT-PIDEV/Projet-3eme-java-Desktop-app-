/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Services;

import upgradi.Entities.Panier;
import upgradi.Utils.connexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author Fedy
 */
public class PanierService implements Iservice<Panier>{
    
    private static PanierService  instance;
    private Statement st;
    private ResultSet rs;
    
    private PanierService(){
           connexionBD cs=connexionBD.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static PanierService getInstance() {
            if(instance==null) 
            instance=new PanierService();
        return instance;
        
    }
    
    @Override
    public void insert (Panier o){
        String req = " insert into panier (nom,prixTotal) values ('"+o.getNom()+"','"+o.getPrix()+"')";
     
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }

  
    public void delete(int id) {
        String req="delete from panier where id="+id;
        //Panier p=displayById(id);
        
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Panier> displayAll() {
        String req="select * from panier";
        ObservableList<Panier> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Panier a=new Panier();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString("nom"));
                a.setPrix(rs.getString("prixTotal"));
                
                list.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Panier displayById(int id) {
    
      String req="select * from panier where id ="+id;
         Panier a=new Panier();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                a.setPrix(rs.getString("prixTotal"));

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return a;
        
    }

  /* @Override
    public boolean update(Panier os) {
        String req = "UPDATE panier SET nom = '"+os.getNom()+"', prixTotal = '" + os.getPrix()+"' WHERE id = "+os.getId();
        try {
            st.executeUpdate(req);
            System.out.println("test" + st.executeUpdate(req));
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }*/
    
}
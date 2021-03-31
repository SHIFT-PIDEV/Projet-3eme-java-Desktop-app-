/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Coupon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DbConnexion;

/**
 *
 * @author pc
 */
public class couponService implements Service <Coupon>{
    
    
      private static couponService instance;
    private Statement st;
    private ResultSet rs;
    
    private couponService() {
      DbConnexion cs=DbConnexion.getInstance();
        try {
            st=cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ExamenService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static couponService getInstance(){
        if(instance==null) 
            instance=new couponService();
        return instance;
    }

    @Override
    public void insert(Coupon o) {
 String req = "insert into coupon (type,validite) values ('"+o.getType()+"','"+o.getValidite()+"')";
         // String req= " insert into coupon values  ('"+o.getType()+"','"+o.getValidite()+"') ";
         System.out.println("tessssssst2");
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(couponService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
         String req="delete from coupon where idc = "+id;
        Coupon p=displayById(id);
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(Coupon.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public List<Coupon> displayAll() {
 String req="select * from coupon";
        ObservableList<Coupon> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Coupon p=new Coupon();
                p.setId(rs.getInt("idC"));
                p.setType(rs.getString("type"));
                p.setValidite(rs.getString("validite"));
               
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(couponService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Coupon> displayAllByDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Coupon displayById(int id) {
         String req="select * from coupon where idc ="+id;
           Coupon p=new Coupon();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("idc"));
                p.setType(rs.getString("type"));
                p.setValidite(rs.getString("validite"));
               
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(couponService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public void update(Coupon os) {
           String req = "UPDATE coupon SET type = '"+os.getType()+"', validite = '"+os.getValidite()+"' WHERE idc = "+os.getId();
       
       try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(couponService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static com.neovisionaries.i18n.LanguageCode.cs;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.Demande;
import utils.ConnexionSingleton;


/**
 *
 * @author LEGION
 */
public class DemandeDao implements Idao<Demande> {
    private static DemandeDao instance;
    private Statement st;
    private ResultSet rs;

    private DemandeDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DemandeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DemandeDao getInstance(){
        if(instance==null) 
            instance=new DemandeDao();
        return instance;
    }
    @Override
    public void insert(Demande o) {
        
          String req="insert into demande (id,objet,description,cv) values ('"+o.getId()+"','"+o.getObjet()+"','"+o.getDescription()+"','"+o.getCv()+"')";
        try {
              int executeUpdate = st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Demande o) {
          String req="delete from demande where id="+o.getId();
        Demande p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
        
    }

    @Override
    public ObservableList<Demande> displayAll() {
        
        String req="select * from demande";
        ObservableList<Demande> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Demande p=new Demande();
                p.setId(rs.getInt(1));
                p.setObjet(rs.getString("objet"));
                p.setDescription(rs.getString(3));
                p.setCv(rs.getString("cv"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
 public List<Demande> displayAllList() {
        String req="select * from demande";
        List<Demande> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Demande p=new Demande();
             p.setId(rs.getInt(1));
                p.setObjet(rs.getString("objet"));
                p.setDescription(rs.getString(3));
                    p.setCv(rs.getString(5));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Demande displayById(int id) {
          String req="select * from demande where id ="+id;
           Demande p=new Demande();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
       p.setId(rs.getInt(1));
                p.setObjet(rs.getString("objet"));
                p.setDescription(rs.getString(3));
                 
                    p.setCv(rs.getString(5));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
        
    }

    @Override
    public boolean update(Demande p) {
                String qry = "UPDATE demande SET id = '"+p.getId()+"', objet = '"+p.getObjet()+"', description = '"+p.getDescription()+"', cv = '"+p.getCv()+"' WHERE id = "+p.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
        
    }
    


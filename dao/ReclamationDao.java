/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entities.Reclamation;
import utils.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author wiemhjiri
 */
public class ReclamationDao implements Idao<Reclamation>{
    
    private static ReclamationDao instance;
    private Statement st;
    private ResultSet rs;
    
    private ReclamationDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ReclamationDao getInstance(){
        if(instance==null) 
            instance=new ReclamationDao();
        return instance;
    }

    @Override
    public void insert(Reclamation o) {
        String req="insert into reclamation (id,objet,description) values ('"+o.getId()+"','"+o.getObjet()+"','"+o.getDescription()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(Reclamation o) {
        String req="delete from reclamation where id="+o.getId();
        Reclamation p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<Reclamation> displayAll() {
        String req="select * from reclamation";
        ObservableList<Reclamation> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Reclamation p=new Reclamation();
                p.setId(rs.getInt(1));
                p.setObjet(rs.getString("objet"));
                p.setDescription(rs.getString(3));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Reclamation> displayAllList() {
        String req="select * from reclamtion";
        List<Reclamation> list=new ArrayList<>();
        
        try { 
            rs=st.executeQuery(req);
            while(rs.next()){
                Reclamation p=new Reclamation();
                p.setId(rs.getInt(1));
                p.setObjet(rs.getString("objet"));
                p.setDescription(rs.getString(3));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Reclamation displayById(int id) {
           String req="select * from reclamation where id ="+id;
           Reclamation p=new Reclamation();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setObjet(rs.getString("objet"));
                p.setDescription(rs.getString("description"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public boolean update(Reclamation p) {
        String qry = "UPDATE reclamation SET id = '"+p.getId()+"', objet = '"+p.getObjet()+"', description = '"+p.getDescription()+"' WHERE id = "+p.getId();
        
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

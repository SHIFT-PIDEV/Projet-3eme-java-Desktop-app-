/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Event;
import entities.Examen;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DbConnexion;

/**
 *
 * @author mahdi
 */
public class ExamenService  implements Service<Examen>{
  private static ExamenService instance;
    private Statement st;
    private ResultSet rs;
    
     private ExamenService() {
         DbConnexion cs=DbConnexion.getInstance();
        try {
            st=cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ExamenService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ExamenService getInstance(){
        if(instance==null) 
            instance=new ExamenService();
        return instance;
    }
    
    @Override
    public void insert(Examen o) {
String req= " insert into examen (titre,date,niveau,prix,support) values ('"+o.getTitreE()+"','"+o.getDate()+"','"+o.getNiveau()+"','"+o.getPrixE()+"','"+o.getSupport()+"') ";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ExamenService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
  String req="delete from examen where id="+id;
        Examen p=displayById(id);
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public List<Examen> displayAll() {
String req="select * from examen";
        ObservableList<Examen> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Examen p=new Examen();
                p.setIdE(rs.getInt("id"));
                p.setTitreE(rs.getString("titre"));
                p.setDate(rs.getDate("date").toLocalDate());
                p.setNiveau(rs.getString("niveau"));
                p.setPrixE(rs.getInt("prix"));
                p.setSupport(rs.getString("support"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ExamenService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Examen> displayAllByDate() {
String req="select * from examen order by date asc";
        ObservableList<Examen> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Examen p=new Examen();
                p.setIdE(rs.getInt("id"));
                p.setTitreE(rs.getString("titre"));
                p.setDate(rs.getDate("date").toLocalDate());
                p.setNiveau(rs.getString("niveau"));
                p.setPrixE(rs.getInt("prix"));
                p.setSupport(rs.getString("support"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ExamenService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Examen displayById(int id) {
 String req="select * from examen where id ="+id;
           Examen p=new Examen();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setIdE(rs.getInt("id"));
                p.setTitreE(rs.getString("titre"));
                p.setDate(rs.getDate("date").toLocalDate());
                p.setNiveau(rs.getString("niveau"));
                p.setPrixE(rs.getInt("prix"));
                p.setSupport(rs.getString("support"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ExamenService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p; 
    }

    @Override
    public void update(Examen os) {
String req = "UPDATE examen SET titre = '"+os.getTitreE()+"', date = '"+os.getDate()+"',niveau = '"+ os.getNiveau()+"', prix= '"+os.getPrixE()+"', support= '"+os.getSupport()+"' WHERE id = "+os.getIdE();
       
       try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ExamenService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public List<Examen> displayAllAfterSearch(String s) {
        String req="select * from examen where titre like '%"+s+"%'";
        List<Examen> list=new ArrayList<>();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Examen p=new Examen();
               p.setIdE(rs.getInt("id"));
                p.setTitreE(rs.getString("titre"));
                p.setDate(rs.getDate("date").toLocalDate());
                p.setNiveau(rs.getString("niveau"));
                p.setPrixE(rs.getInt("prix"));
                p.setSupport(rs.getString("support"));
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ExamenService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

  
    
}

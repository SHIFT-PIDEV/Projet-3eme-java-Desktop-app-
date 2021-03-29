/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Event;
import entities.Examen;
import entities.InscriEvent;
import entities.InscripExam;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
public class InscriptionExService implements Service<InscripExam> {
    
     private static InscriptionExService instance;
   
    private Statement st,st1;
    private ResultSet rs,rs1;
    
     private InscriptionExService() {
         DbConnexion cs=DbConnexion.getInstance();
        try {
            st=cs.getConnection().createStatement();
            st1=cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionExService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static InscriptionExService getInstance(){
        if(instance==null) 
            instance=new InscriptionExService();
        return instance;
    }

    @Override
    public void insert(InscripExam o) {
       Timestamp t=Timestamp.valueOf(LocalDateTime.now());
        String req="insert into inscripexam(idClient,idExam,dateInscri,nom,prenom,email) values('"+o.getIdClient()+"','"+o.getIdExam()+"','"+t+"','"+o.getNom()+"','"+o.getPrenom()+"','"+o.getEmail()+"') ";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionExService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InscripExam> displayAll() {
String req="select * from inscripexam";
        ObservableList<InscripExam> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
            InscripExam i=new InscripExam();
                 i.setIdinscri(rs.getInt(1));
                i.setIdClient(rs.getInt(2));
                i.setIdExam(rs.getInt(3));
                i.setDateInscri(rs.getTimestamp(4));
                i.setNom(rs.getString(5));
                i.setPrenom(rs.getString(6));
                i.setEmail(rs.getString(7));
                list.add(i);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ExamenService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;   
    }

    @Override
    public List<InscripExam> displayAllByDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public void update(InscripExam os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
      

    @Override
    public InscripExam displayById(int id) {
String req="select * from inscripexam where idClient ="+id;
     InscripExam i=new InscripExam();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
            
                i.setIdinscri(rs.getInt(1));
                i.setIdClient(rs.getInt(2));
                i.setIdExam(rs.getInt(3));
                i.setDateInscri(rs.getTimestamp(4));
                i.setNom(rs.getString(5));
                i.setPrenom(rs.getString(6));
                i.setEmail(rs.getString(7));
                
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ExamenService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return i;    
    }

    
    
     public InscripExam displayeByIdClientIdExam(int idClient,int idex){
        String req="select * from inscripexam where idClient='"+idClient+"'&& idExam='"+idex+"'";
        InscripExam ie=new InscripExam();
        try {
            rs=st.executeQuery(req);
            rs.next();
            ie.setIdinscri(rs.getInt(1));
        } catch (SQLException ex) {
           // Logger.getLogger(InscriEventS.class.getName()).log(Level.SEVERE, null, ex);
           ie.setIdinscri(-1);
        }
        return ie;
    }
     
    public void annulerInscri(int idClient,int idExam){
        String req="delete from inscripexam where idClient='"+idClient+"'&&idExam='"+idExam+"'";
        
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionExService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public List<Examen> displayByIdClient(int idClient) {
         String req="select * from inscripexam where idClient='"+idClient+"' ";
          
        
        List<Examen> list=new ArrayList<>();  
          try {
            rs=st.executeQuery(req);
            while(rs.next()){
                InscripExam i=new InscripExam();
                i.setIdinscri(rs.getInt(1));
                i.setIdClient(rs.getInt(2));
                i.setIdExam(rs.getInt(3));
                i.setDateInscri(rs.getTimestamp(4));
                i.setNom(rs.getString(5));
                i.setPrenom(rs.getString(6));
                i.setEmail(rs.getString(7));
                
                   String req1="select * from examen where id='"+i.getIdExam()+"'";
                  // System.out.println(i.getIdExam()+"hhhhhhhhhh");
                    rs1=st1.executeQuery(req1);
                    while(rs1.next()){
                        Examen e=new Examen();
                        e.setIdE(rs1.getInt(1));
                        e.setTitreE(rs1.getString(2));
                        e.setDate(rs1.getDate(3).toLocalDate());
                        e.setNiveau(rs1.getString(4));
                        e.setPrixE(rs1.getInt(5));
                         e.setSupport(rs1.getString(6));
                
                      list.add(e);
                    
                    }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(InscriEventS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<InscripExam> displayAllIdExam(int idExam) {
         String req="select * from inscripexam where idExam='"+idExam+"' ";
         
         List<InscripExam> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                 InscripExam i=new InscripExam();
                i.setIdinscri(rs.getInt(1));
                i.setIdClient(rs.getInt(2));
                i.setIdExam(rs.getInt(3));
                i.setDateInscri(rs.getTimestamp(4));
                i.setNom(rs.getString(5));
                i.setPrenom(rs.getString(6));
                i.setEmail(rs.getString(7));
                list.add(i);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(InscripExam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
            
        
    }
    
    
}

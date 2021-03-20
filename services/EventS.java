/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commentaire;
import entities.Event;
import entities.inscriView;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DbConnexion;

/**
 *
 * @author asus
 */
public class EventS {
    private static EventS instanceEventS;
    private Statement st;
    private ResultSet rs;
    
    private EventS(){
        DbConnexion cnx= DbConnexion.getInstance();
        try {
            st=cnx.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static EventS getEventS(){
        if(instanceEventS==null)
            instanceEventS=new EventS();
        return instanceEventS;
    }
    
    public void insert(Event e){
        String replace = e.getImage().replace("\\", "/");
        String req="insert into event(idFormateur,nomEvent,dateDebut,heure,duree,descEvent,image) values('"+e.getIdF()+"','"+e.getNomE()+"','"+e.getDateD()+"','"+e.getHeure()+"','"+e.getDuree()+"','"+e.getDescE()+"','"+replace+"') ";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Event> displayAll() {
        String req="select * from event";
        List<Event> list=new ArrayList<>();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Event e=new Event();
                e.setIdE(rs.getInt(1));
                e.setIdF(rs.getInt(2));
                e.setNomE(rs.getString(3));
                e.setDateD(rs.getDate(4).toLocalDate());
                e.setHeure(rs.getInt(5));
                e.setDuree(rs.getInt(6));
                e.setDescE(rs.getString(7));
                e.setImage(rs.getString(8));
                
                list.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public Event displayeById(int id){
        Event e=new Event();
        String req="select * from event where idEvent='"+id+"'";
        try {
            rs=st.executeQuery(req);
            rs.next();
            e.setIdE(rs.getInt(1));
                e.setIdF(rs.getInt(2));
                e.setNomE(rs.getString(3));
                e.setDateD(rs.getDate(4).toLocalDate());           
                e.setDuree(rs.getInt(5));
                e.setHeure(rs.getInt(6));
                e.setDescE(rs.getString(7));
                e.setImage(rs.getString(8));
                
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return e;
    }
    
    public void deleteEvent(int id){
        String req2="delete from commentaire where idEvent='"+id+"'";
        try {
            st.executeUpdate(req2);
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
        String req1="delete from inscrievent where idEvent='"+id+"'";
        try {
            st.executeUpdate(req1);
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
        String req="delete from event where idEvent='"+id+"'";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void updateEvent(Event e){
        String replace = e.getImage().replace("\\", "/");
        String req="update event set idFormateur='"+e.getIdF()+"',nomEvent='"+e.getNomE()+"',dateDebut='"+e.getDateD()+"',heure='"+e.getHeure()+"',duree='"+e.getDuree()+"',descEvent='"+e.getDescE()+"',image='"+replace+"' where idEvent='"+e.getIdE()+"' ";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Event> displayAllAfterSearch(String s) {
        String req="select * from event where nomEvent like '%"+s+"%'|| descEvent like '%"+s+"%' ";
        List<Event> list=new ArrayList<>();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Event e=new Event();
                e.setIdE(rs.getInt(1));
                e.setIdF(rs.getInt(2));
                e.setNomE(rs.getString(3));
                e.setDateD(rs.getDate(4).toLocalDate());
                e.setHeure(rs.getInt(5));
                e.setDuree(rs.getInt(6));
                e.setDescE(rs.getString(7));
                e.setImage(rs.getString(8));
                
                list.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public List<inscriView> laListeDesInscription(int idEvent){
         List<inscriView> list = new ArrayList<>();
         String req="select * from inscriList where idEvent='"+idEvent+"'";
          try {
            rs=st.executeQuery(req);
            while(rs.next()){
                inscriView iv=new inscriView();
                iv.setIdinscri(rs.getInt(1));
            iv.setIdClient(rs.getInt(2));
            iv.setIdEvent(rs.getInt(3));
            iv.setDateInscri(rs.getTimestamp(4));
            iv.setNomClient(rs.getString(5));
            iv.setEmailClient(rs.getString(6));
            list.add(iv);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
         return list;
         
     }
      public List<Commentaire> displayComm(int idEvent){
        
         List<Commentaire> list= new ArrayList();  
        String req="select * from commentaire where idEvent='"+idEvent+"'";
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Commentaire ie=new Commentaire();
                ie.setIdcomm(rs.getInt(1));
            ie.setIdClient(rs.getInt(2));
            ie.setIdEvent(rs.getInt(3));
            ie.setDatecomm(rs.getTimestamp(4));
            ie.setDesc(rs.getString(5));
            list.add(ie);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}

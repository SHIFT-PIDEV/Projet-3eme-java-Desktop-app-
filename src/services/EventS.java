/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Event;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        String req="insert into event(idFormateur,nomEvent,dateDebut,duree,descEvent) values('"+e.getIdF()+"','"+e.getNomE()+"','"+e.getDateD()+"','"+e.getDuree()+"','"+e.getDescE()+"') ";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Event> displayAll() {
        String req="select * from event";
        ObservableList<Event> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Event e=new Event();
                e.setIdE(rs.getInt(1));
                e.setIdF(rs.getInt(2));
                e.setNomE(rs.getString(3));
                e.setDateD(rs.getDate(4).toLocalDate());
                e.setDuree(rs.getInt(5));
                e.setDescE(rs.getString(6));
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
                e.setDescE(rs.getString(6));
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return e;
    }
    
    public void deleteEvent(int id){
        String req="delete from event where idEvent='"+id+"'";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateEvent(Event e){
        String req="update event set idFormateur='"+e.getIdF()+"',nomEvent='"+e.getNomE()+"',dateDebut='"+e.getDateD()+"',duree='"+e.getDuree()+"',descEvent='"+e.getDescE()+"' where idEvent='"+e.getIdE()+"' ";
        try {
            st.executeUpdate(req);
            System.out.println("Teeeeeeeeeeeeest"+st.executeUpdate(req));
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Event;
import entities.Notification;
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
public class NotificationS {
    private Statement st ;
    private ResultSet rs;
    private Statement st1 ;
    private ResultSet rs1;
    private static NotificationS notInsctance;
    
    private  NotificationS(){
        DbConnexion cnx=DbConnexion.getInstance();
        try {
            st=cnx.getConnection().createStatement();
            st1=cnx.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(NotificationS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static NotificationS getInsctance(){
        if(notInsctance==null)
            notInsctance=new NotificationS();
        return notInsctance;
    }
    
    public void insertNot(int idcS,int idcD,int idevent){
        String req="insert into notification(idcs,idcd,idevent) values('"+idcS+"','"+idcD+"','"+idevent+"') ";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(NotificationS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Event> displayByIdClientSource(int idcd){
        String req="select * from notification where idcd='"+idcd+"' ";
        
        List<Event> list=new ArrayList<>();       
       
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Notification i=new Notification();
                i.setIdcS(rs.getInt(2));
                i.setIdcD(rs.getInt(3));
                i.setIdevent(rs.getInt(4));
                    String req1="select * from event where idevent='"+i.getIdevent()+"'";
                    rs1=st1.executeQuery(req1);
                    while(rs1.next()){
                        Event e=new Event();
                        e.setIdE(rs1.getInt(1));
                        e.setIdF(rs1.getInt(2));
                        e.setNomE(rs1.getString(3));
                        e.setDateD(rs1.getDate(4).toLocalDate());
                        e.setHeure(rs1.getInt(5));
                        e.setDuree(rs1.getInt(6));
                        e.setDescE(rs1.getString(7));
                        e.setImage(rs1.getString(8));
                
                      list.add(e);
                    
                    }                 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(InscriEventS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; 
    }
    
     public List<Notification> displayNotifByIdClientSource(int idcd){
        String req="select * from notification where idcd='"+idcd+"' ";
        
        List<Notification> list=new ArrayList<>();       
       
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Notification i=new Notification();
                i.setIdcS(rs.getInt(2));
                i.setIdcD(rs.getInt(3));
                i.setIdevent(rs.getInt(4));
                list.add(i);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(InscriEventS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; 
    }
     
     public int displayAllbyIdEvent(int idevent){
        String req="select count(*) from notification where idevent='"+idevent+"' ";
        int n=0;
        try {
            rs=st.executeQuery(req);
            rs.next();
            n=rs.getInt(1);
                    
        } catch (SQLException ex) {
            Logger.getLogger(LikeS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
     
      public int displayAllShare(){
        String req="select count(*) from notification  ";
        int n=0;
        try {
            rs=st.executeQuery(req);
            rs.next();
            n=rs.getInt(1);
                    
        } catch (SQLException ex) {
            Logger.getLogger(LikeS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}

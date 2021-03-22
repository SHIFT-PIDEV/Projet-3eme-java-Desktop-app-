/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Event;
import entities.InscriEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DbConnexion;

/**
 *
 * @author asus
 */
public class InscriEventS {
    private static InscriEventS instanceInscriEventS;
    private Statement st,st1;
    private ResultSet rs,rs1;
    
    private InscriEventS(){
        DbConnexion cnx= DbConnexion.getInstance();
        try {
            st=cnx.getConnection().createStatement();
            st1=cnx.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static InscriEventS getInscriEvent(){
        if(instanceInscriEventS==null)
            instanceInscriEventS=new InscriEventS();
        return instanceInscriEventS;
    }
    public void insert(InscriEvent i){
        
        Timestamp t=Timestamp.valueOf(LocalDateTime.now());
        String req="insert into inscriEvent(idClient,idEvent,dateInscri) values('"+i.getIdClient()+"','"+i.getIdEvent()+"','"+t+"') ";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<InscriEvent> displayAll() {
        String req="select * from inscrievent";
        List<InscriEvent> list=new ArrayList<>();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                InscriEvent i=new InscriEvent();
                i.setIdinscri(rs.getInt(1));
                i.setIdClient(rs.getInt(2));
                i.setIdEvent(rs.getInt(3));
                i.setDateInscri(rs.getTimestamp(4));
                
                list.add(i);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public InscriEvent displayeByIdClientIdEvent(int idClient,int idEvent){
        String req="select * from inscrievent where idclient='"+idClient+"'&&idevent='"+idEvent+"'";
        InscriEvent ie=new InscriEvent();
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
    public void annulerInscri(int idClient,int idEvent){
        String req="delete from inscrievent where idclient='"+idClient+"'&&idevent='"+idEvent+"'";
        
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(InscriEventS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public List<Event> displayByIdClient(int idClient){
        String req="select * from inscrievent where idclient='"+idClient+"' ";
        
        List<Event> list=new ArrayList<>();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                InscriEvent i=new InscriEvent();
                i.setIdinscri(rs.getInt(1));
                i.setIdClient(rs.getInt(2));
                i.setIdEvent(rs.getInt(3));
                i.setDateInscri(rs.getTimestamp(4));
                    String req1="select * from event where idEvent='"+i.getIdEvent()+"'";
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
    
}

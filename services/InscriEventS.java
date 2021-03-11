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
    private Statement st;
    private ResultSet rs;
    
    private InscriEventS(){
        DbConnexion cnx= DbConnexion.getInstance();
        try {
            st=cnx.getConnection().createStatement();
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
    
}

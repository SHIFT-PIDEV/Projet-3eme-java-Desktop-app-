/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commentaire;
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
public class CommentaireS {
    private static CommentaireS instanceCommentaireS;
    private Statement st;
    private ResultSet rs;
    
    private CommentaireS(){
        DbConnexion cnx= DbConnexion.getInstance();
        try {
            st=cnx.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static CommentaireS getCommentaireS(){
        if(instanceCommentaireS==null)
            instanceCommentaireS=new CommentaireS();
        return instanceCommentaireS;
    }
    public void insert(Commentaire i){
        
        Timestamp t=Timestamp.valueOf(LocalDateTime.now());
        String req="insert into Commentaire(idClient,idEvent,dateComm,descComm) values('"+i.getIdClient()+"','"+i.getIdEvent()+"','"+t+"','"+i.getDesc()+"') ";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Commentaire> displayAll() {
        String req="select * from commentaire";
        List<Commentaire> list=new ArrayList<>();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Commentaire i=new Commentaire();
                i.setIdcomm(rs.getInt(1));
                i.setIdClient(rs.getInt(2));
                i.setIdEvent(rs.getInt(3));
                i.setDatecomm(rs.getTimestamp(4));
                i.setDesc(rs.getString(5));
                
                list.add(i);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}

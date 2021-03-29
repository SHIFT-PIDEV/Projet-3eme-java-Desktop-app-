/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Like;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DbConnexion;

/**
 *
 * @author asus
 */
public class LikeS {
    private Statement st;
    private ResultSet rs;
    private static LikeS ls;
    private LikeS(){
        DbConnexion cnx =DbConnexion.getInstance();
        try {
            st=cnx.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(LikeS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static LikeS getLikeS(){
        if(LikeS.ls==null){
            LikeS.ls=new LikeS();
        }
        return ls;
    }
    public void insertLike(Like like){
        String req="insert into likeevent values('"+like.getIdEvent()+"','"+like.getIdClient()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(LikeS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void deleteLike(int idevent,int idClient){
        String req="delete from likeevent where idevent='"+idevent+"'&&idclient='"+idClient+"'";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(LikeS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Like displayLike(int idevent,int idClient){
        Like like=new Like();
        String req="select * from likeevent where idevent='"+idevent+"'&&idclient='"+idClient+"'";
        try {
            rs=st.executeQuery(req);
            rs.next();
             like.setIdEvent(rs.getInt(1));
            like.setIdClient(rs.getInt(2));
           
        } catch (SQLException ex) {
            //Logger.getLogger(LikeS.class.getName()).log(Level.SEVERE, null, ex);
            like.setIdClient(-1);
            like.setIdEvent(-1);
        }
        return like;
    }
    public int displayAllbyIdEvent(int idevent){
        String req="select count(*) from likeevent where idevent='"+idevent+"' ";
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Client;
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
public class ClientS {
    private static ClientS instanceClientS;
    private Statement st;
    private ResultSet rs;
    
    private ClientS(){
        DbConnexion cnx=DbConnexion.getInstance();
        try {
            st=cnx.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ClientS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ClientS getClientS(){
        if(instanceClientS==null)
            instanceClientS=new ClientS();
        return instanceClientS;
    }
    
    public Client displayefoLogin(String userName,String mdp){
        Client e=new Client();
        String req="select * from client where userName='"+userName+"'&&mdp='"+mdp+"'";
        try {
            rs=st.executeQuery(req);
            rs.next();
                e.setId(rs.getInt(1));
                e.setNom(rs.getString(2));
                e.setPrenom(rs.getString(3));
                e.setUserName(rs.getString(4));           
                e.setMdp(rs.getString(5));
                e.setEmail(rs.getString(6));
                e.setFormateur(rs.getInt(7));
                System.out.println(e.getId());
                
        } catch (SQLException ex) {
            e.setId(-1);
        }
        
        return e;
        
    }
    public Client displayClientById(int id){
        Client e=new Client();
        String req="select * from client where id='"+id+"'";
        
        try {
            rs=st.executeQuery(req);
            rs.next();
                e.setId(rs.getInt(1));
                e.setNom(rs.getString(2));
                e.setPrenom(rs.getString(3));
                e.setUserName(rs.getString(4));           
                e.setMdp(rs.getString(5));
                e.setEmail(rs.getString(6));
                e.setFormateur(rs.getInt(7));
        } catch (SQLException ex) {
            e.setUserName("anonyme");
        }
        return e;
    }
}

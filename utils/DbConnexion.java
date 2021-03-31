/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class DbConnexion {
    private String url="jdbc:mysql://127.0.0.1/upgradi";
    private String login="root";
    private String mdp="";
    private Connection cnx;
    private static DbConnexion instanceUnique;

    private DbConnexion() {
        try {
            cnx=DriverManager.getConnection(url, login, mdp);
            System.out.println("connexon jawha mriguel");
        } catch (SQLException ex) {
            Logger.getLogger(DbConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DbConnexion getInstance(){
        if(instanceUnique==null){
            instanceUnique=new DbConnexion();
        }
        return instanceUnique;
    }
    
    public Connection getConnection(){
        return cnx;
    }
    
    
}

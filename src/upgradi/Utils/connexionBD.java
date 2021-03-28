/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aziz
 */
public class connexionBD {
       private String url="jdbc:mysql://localhost:3306/produit";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static connexionBD instance;

    public Connection getCnx() {
        return cnx;
    }
    
    
    private connexionBD() {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(connexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static connexionBD getInstance(){
       
       if(instance==null)
           instance=new connexionBD();
       return instance;
    
}
}

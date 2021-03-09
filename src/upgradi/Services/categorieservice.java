/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import upgradi.Entities.categorie;
import upgradi.Utils.connexionBD;

/**
 *
 * @author Aziz
 */
public class categorieservice implements Iservice<categorie> {
      private static categorieservice  instance;
    private Statement st;
    private ResultSet rs;
    
    private categorieservice(){
           connexionBD cs=connexionBD.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(categorieservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static categorieservice getInstance() {
            if(instance==null) 
            instance=new categorieservice();
        return instance;
        
    }
    
    @Override
    public void insert (categorie a){
        String  req;
            req = " insert into categorie (nom_cat) values ('"+a.getNomcategorie()+"') ";
     try {
           int executeUpdate = st.executeUpdate(req);
            
        }catch (SQLException ex){
              Logger.getLogger(categorieservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
          String req="delete from categorie where id="+id;
        categorie a=displayById(id);
        
        
          if(a!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(categorieservice.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
        
    }

    @Override
    public ObservableList<categorie> displayAll() {
          String req="select * from categorie";
        ObservableList<categorie> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                categorie a=new categorie();
                a.setId(rs.getInt("id"));
                a.setNomcategorie(rs.getString("nom_cat"));
                
                list.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(categorieservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public categorie displayById(int id) {
    
      String req="select * from categorie where id ="+id;
         categorie a=new categorie();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                a.setId(rs.getInt("id"));
                a.setNomcategorie(rs.getString("nom_cat"));

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(categorieservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    return a;
        
    }

    @Override
    public boolean update(categorie os) {
  String req="update categorie set nom_cat='"+os.getNomcategorie()+"' where id='"+os.getId()+"' ";
       try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(categorieservice.class.getName()).log(Level.SEVERE, null, ex);
        }
            return false;
    
    
    }
////////////////////////
//    @Override
//    public List<categorie> displayAllCat() {
//         String req="select nom_cat from categorie";
//        ObservableList<categorie> list=FXCollections.observableArrayList();       
//        
//        try {
//            rs=st.executeQuery(req);
//            while(rs.next()){
//                categorie a=new categorie();
//             
//                a.setNomcategorie(rs.getString("nom_cat"));
//                
//                list.add(a);
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(categorieservice.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
    ///////////////
    
    
}

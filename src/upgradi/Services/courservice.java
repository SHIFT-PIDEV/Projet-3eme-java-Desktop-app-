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
import upgradi.Entities.cour;
import upgradi.Utils.connexionBD;

/**
 *
 * @author Aziz
 */
public class courservice implements Iservice<cour> {
                 private static courservice  instance;
    private Statement st;
    private ResultSet rs;
    
    private courservice(){
           connexionBD cs=connexionBD.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(courservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static courservice getInstance() {
            if(instance==null) 
            instance=new courservice();
        return instance;
        
    }

    @Override
    public void insert(cour a) {
              
          String   req = " insert into cour (nom,nomcat,formateur,description,img,prix,niveau,duration) values ('"+a.getNom_cour()+"','"+a.getCategorie()+"','"+a.getFormateur()+"','"+a.getDescription()+"','"+a.getImage_v()+"','"+a.getPrix()+"','"+a.getNiveau()+"','"+a.getDuration()+"')";
     try {
           int executeUpdate = st.executeUpdate(req);
            
        }catch (SQLException ex){
              Logger.getLogger(courservice.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @Override
    public void delete(int id) {
      String req="delete from cour where idCour="+id;
        cour a=displayById(id);
        
        
          if(a!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(courservice.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public  ObservableList<cour> displayAll() {
                  String req="select * from  cour";
        ObservableList<cour> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                cour a=new cour();
                a.setId(rs.getInt("idCour"));
                a.setNom_cour(rs.getString("nom"));
                a.setCategorie(rs.getString("nomcat"));
                a.setFormateur(rs.getString("formateur"));
                a.setDescription(rs.getString("description"));
                a.setImage_v(rs.getString("img"));
                a.setPrix(rs.getFloat("prix"));
                a.setNiveau(rs.getString("niveau"));
                a.setDuration(rs.getString("duration"));
                
                
                
                list.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(courservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
    public cour displayById(int id) {
        
      String req="select * from cour where idCour ="+id;
         cour a=new cour();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                         a.setId(rs.getInt("idCour"));
                a.setNom_cour(rs.getString("nom"));
                  a.setCategorie(rs.getString("nomcat"));
                a.setFormateur(rs.getString("formateur"));
                a.setDescription(rs.getString("description"));
                a.setImage_v(rs.getString("img"));
                a.setPrix(rs.getFloat("prix"));
                a.setNiveau(rs.getString("niveau"));
                a.setDuration(rs.getString("duration"));

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(courservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    return a;
    }
    

    @Override
    public boolean update(cour os) {
     String req="update cour set nom='"+os.getNom_cour()+"',description='"+os.getDescription()+"',img='"+os.getImage_v()+"',prix='"+os.getPrix()+"',niveau='"+os.getNiveau()+"',duration='"+os.getDuration()+"' where idCour='"+os.getId()+"' ";
       try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(courservice.class.getName()).log(Level.SEVERE, null, ex);
        }
            return false;
    
    
    }
//  public   ObservableList fillCombobox()
//{
//    
//     ObservableList options = FXCollections.observableArrayList();
//   
//          String query =" select nom_cat from categorie   ";
//             
//       try {
//            rs=st.executeQuery(query);
//            while(rs.next()){
//             options.add(rs.getString(query));
//                
//                
//                
//            
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(courservice.class.getName()).log(Level.SEVERE, null, ex);        }
//                 return null;
//        
// 
//}
    
}

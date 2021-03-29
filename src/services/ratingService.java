/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Commentaire;
import entities.RatingR;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DbConnexion;


/**
 *
 * @author pc
 */
public class ratingService implements Service<RatingR> {
    private static ratingService instance;
    private Statement st;
    private ResultSet rs;
    
     private ratingService() {
         DbConnexion cs=DbConnexion.getInstance();
        try {
            st=cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ratingService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static ratingService getInstance(){
        if(instance==null) 
            instance=new ratingService();
        return instance;
    }

    @Override
    public void insert(RatingR o) {
        String req = "insert into rating (idClient,idExam,commentaire,rate) values ('"+o.getIdClient()+"','"+o.getIdExam()+"','"+o.getCommentaire()+"','"+o.getRate()+"')";
        System.out.println("tessssssst2");
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ratingService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RatingR> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RatingR> displayAllByDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RatingR displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(RatingR os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List<RatingR> displayRatings(int idExam){
        
         List<RatingR> list= new ArrayList();  
        String req="select * from rating where idExam='"+idExam+"'";
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                RatingR rate=new RatingR();
                rate.setIdClient(rs.getInt(1));
            rate.setIdExam(rs.getInt(2));
            rate.setIdR(rs.getInt(3));
            rate.setCommentaire(rs.getString(4));
            rate.setRate(rs.getInt(5));
            list.add(rate);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(EventS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
  

  
    
}

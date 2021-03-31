/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Coupon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;
import services.couponService;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class AjouterCouponController implements Initializable {

    @FXML
    private TextField tf_typeC;
    @FXML
    private TextField tf_validiteC;
    @FXML
    private Button btn_ajouterC;
    @FXML
    private Button btn_recuperer;
    @FXML
    private TextField tf_recuperer;
    @FXML
    private Button btn_modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterCoupon(ActionEvent event) {
         Coupon c = new Coupon(tf_typeC.getText(),tf_validiteC.getText());
         String rq="";
        if(tf_typeC.getText().isEmpty() )
        {
           rq+="veuillez remplir le champ Type Coupon !\n";
        }
        if(tf_validiteC.getText().isEmpty()){
            rq+="veuillez remplir le champ Validité Coupon !\n";
        }
        
       if(!rq.isEmpty())
       {    //////notificationnnnnnn  
            Notifications.create()
                 .title("AJOUT COUPON !")
                 .text("error << "+rq+" >>verifier vos champs !!")
                 .darkStyle().position(Pos.BOTTOM_RIGHT).showError();
             //////notificationnnnnnn 
                  
        }
       else
       {
          
            couponService cserv= couponService.getInstance();
            cserv.insert(c);
                //////notificationnnnnnn  
            Notifications.create()
                 .title("AJOUT COUPON Examen")
                 .text("COUPON de type<< "+tf_typeC.getText()+" >>est ajouté avec success")
                 .darkStyle().position(Pos.BOTTOM_RIGHT).showConfirm();
             //////notificationnnnnnn 
           // this.sendMail();
       }

       
        tf_typeC.setText("");
        tf_validiteC.setText("");
        
    }
    




    @FXML
    private void recuperer_coupon(ActionEvent event) {
          int idS=Integer.valueOf(tf_recuperer.getText());
                   couponService coupser = couponService.getInstance();
                
                  Coupon exx=new Coupon();
                  exx= coupser.displayById(idS);
              
              tf_typeC.setText(exx.getType());
              tf_validiteC.setText(String.valueOf(exx.getValidite()));
    }

    @FXML
    private void modifier_coupon(ActionEvent event) {
         int idS=Integer.valueOf(tf_recuperer.getText());  
       System.out.println(idS +"  "+ tf_typeC.getText()+"  "+tf_validiteC.getText());
        Coupon coup = new Coupon(idS, tf_typeC.getText(),tf_validiteC.getText());
                 
                 
                   String rq="";
        if(tf_typeC.getText().isEmpty() )
        {
           rq+="veuillez remplir le champ Type Coupon !\n";
        }
        if(tf_validiteC.getText().isEmpty()){
            rq+="veuillez remplir le champ Validité Coupon !\n";
        }
        
       if(!rq.isEmpty())
       {    //////notificationnnnnnn  
            Notifications.create()
                 .title("MODIFIER COUPON !")
                 .text("error << "+rq+" >>verifier vos champs !!")
                 .darkStyle().position(Pos.BOTTOM_RIGHT).showError();
             //////notificationnnnnnn 
                  
        }
       else
       {
           couponService coupser = couponService.getInstance();
                    coupser.update(coup);
           
                //////notificationnnnnnn  
            Notifications.create()
                 .title("MODIFIER COUPON ")
                 .text("COUPON de type<< "+tf_typeC.getText()+" >>est modifié avec success")
                 .darkStyle().position(Pos.BOTTOM_RIGHT).showConfirm();
             //////notificationnnnnnn 
           // this.sendMail();
       }

       
        tf_typeC.setText("");
        tf_validiteC.setText("");
             
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Coupon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.couponService;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ajouter_coupon_controller implements Initializable {

    @FXML
    private TextField tf_typeC;
    @FXML
    private TextField tf_validiteC;
    @FXML
    private Button btn_ajouterC;
    @FXML
    private Button btn_back_acceuil;
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
        
    }    

    @FXML
    private void ajouterCoupon(ActionEvent event) {
        System.out.println(tf_typeC.getText()+" "+tf_validiteC.getText());
         Coupon coup = new Coupon(tf_typeC.getText(),tf_validiteC.getText());
                 //  Examen exam = new Examen(titre,da,niveau,prix);
                   couponService cpSer = couponService.getInstance();
                    cpSer.insert(coup);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("coupon insérée avec succés!");
                   alert.show();
                   tf_typeC.setText("");
                   tf_validiteC.setText("");
                   
    }

    @FXML
    private void back_acceuil(ActionEvent event) {
        
         try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/acceuil_coupon.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(acceuil_coupon_controller.class.getName()).log(Level.SEVERE, null, ex);
            }
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
                 
                  couponService coupser = couponService.getInstance();
                    coupser.update(coup);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("coup modifiée avec succés!");
                   alert.show();
                   //bloc de passage vers page d'affichage 
                       try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/afficher_coupon.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(acceuil_coupon_controller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}

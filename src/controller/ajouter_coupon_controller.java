/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Coupon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import service.couponService;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ajouter_coupon_controller implements Initializable {

    private TextField tf_typeC;
    private TextField tf_validiteC;
    private TextField tf_recuperer;
    @FXML
    private Rectangle forme_coupon;
    @FXML
    private Label recup_idClient;
    @FXML
    private Label recup_validité;
    @FXML
    private Label recup_idCoupon;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    private void ajouterCoupon(ActionEvent event)throws MessagingException, SQLException{
        Coupon c = new Coupon(tf_typeC.getText(),tf_validiteC.getText());
        if(tf_typeC.getText().length() == 0 && tf_validiteC.getText().length() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir les champs !");
            alert.show();
        }
        else if(tf_typeC.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir le champ Type");
            alert.show();
        }
        else if(tf_validiteC.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir le champ Validité");
            alert.show();
        }
        else{
            couponService cserv= couponService.getInstance();
            cserv.insert(c);
            this.sendMail();
                  
        }
       try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/afficher_coupon.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(acceuil_coupon_controller.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        tf_typeC.setText("");
        tf_validiteC.setText("");
        
    }
    



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

    private void recuperer_coupon(ActionEvent event) throws MessagingException {
          int idS=Integer.valueOf(tf_recuperer.getText());
                   couponService coupser = couponService.getInstance();
                
                  Coupon exx=new Coupon();
                  exx= coupser.displayById(idS);
              
              tf_typeC.setText(exx.getType());
              tf_validiteC.setText(String.valueOf(exx.getValidite()));
           
            
    }

    private void modifier_coupon(ActionEvent event) {
        int idS=Integer.valueOf(tf_recuperer.getText());  
       System.out.println(idS +"  "+ tf_typeC.getText()+"  "+tf_validiteC.getText());
        Coupon coup = new Coupon(idS, tf_typeC.getText(),tf_validiteC.getText());
                 
                  couponService coupser = couponService.getInstance();
                    coupser.update(coup);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("Coupon modifié avec succés!");
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

    public void sendMail() throws MessagingException {
         Properties prop = System.getProperties();
        prop.put("mail.smtp.port", "587");
         prop.put("mail.smtp.auth", true);
         prop.put("mail.smtp.starttls.enable", "true");
         prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session newSession = Session.getDefaultInstance(prop, null);

        String emailsubject="UPGRADI inscription valideé";
        String emailbody="Bienvenue Mr/Mme ";
        Message message = new MimeMessage(newSession);
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("yasmine.chelbi@esprit.tn"));
        } catch (AddressException ex) {
           // Logger.getLogger(InscriptionEController.class.getName()).log(Level.SEVERE, null, ex);
        }

         message.setSubject(emailsubject);

         MimeBodyPart mimeBodyPart = new MimeBodyPart();
         mimeBodyPart.setContent(emailbody, "text/html");

          Multipart multipart = new MimeMultipart();
          multipart.addBodyPart(mimeBodyPart);


          message.setContent(multipart);

           String fromuser ="space.upgradi@gmail.com";
           String pass ="upgradi123456789";
           String emailhost="smtp.gmail.com";
           Transport transport =newSession.getTransport("smtp");
           transport.connect(emailhost,fromuser,pass);
           transport.sendMessage( message, message.getAllRecipients());
           transport.close();
       
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("MAIL modifié avec succés!");
                   alert.show();
    }
         
}

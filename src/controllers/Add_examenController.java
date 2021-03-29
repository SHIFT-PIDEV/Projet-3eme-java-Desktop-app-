/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import entities.Examen;
import java.awt.BorderLayout;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
import org.controlsfx.control.Notifications;
import services.ExamenService;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class Add_examenController implements Initializable {

    @FXML
    private TextField tf_titre;
    @FXML
    private DatePicker d_date;
    @FXML
    private ChoiceBox<String> s_niveau;
    @FXML
    private TextField s_prix;
    @FXML
    private TextField tf_support;
    @FXML
    private Button btn_modifier;
    @FXML
    private TextField tf_rec_mod;
    @FXML
    private Button btn_recuperer;
    @FXML
    private Text tf_control_saisie;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Rectangle rectangle_recaptcha;
    @FXML
    private CheckBox checkbox_recaptcha;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        s_niveau.setItems(FXCollections.observableArrayList("facile","moyen","difficile")); 
    }
   
    
   

    @FXML
    private void Modifier_exam(ActionEvent event) {
         String rq ="";
           //controle de saisie 
       if(tf_titre.getText().isEmpty())
       {
           rq+="veuillez remplir le champ Titre !\n";
       }
       if(d_date.getValue()==null)
       {
           
          rq+="veuillez remplir le champ Date !\n";
       }
       if(s_niveau.getSelectionModel().getSelectedItem()==null)
       {
           
           rq+="veuillez remplir le champ Niveau !\n";
       }
          if(String.valueOf(s_prix.getText()).isEmpty())
       {
           
           rq+="veuillez remplir le champ Prix !\n";
       }
             if(tf_support.getText().isEmpty())
       {
           
           rq+="veuillez remplir le champ support Link !\n";
       }
             
       if(!rq.isEmpty() && !checkbox_recaptcha.isSelected())
        {
            tf_control_saisie.setText(rq);
             checkbox_recaptcha.setStyle("-fx-border-color:red");
            
        }  
         else if(!rq.isEmpty() && checkbox_recaptcha.isSelected())
        {
            tf_control_saisie.setText(rq);
             checkbox_recaptcha.setStyle("-fx-border-color:transparent");
            
        }
        else if(!checkbox_recaptcha.isSelected()&&rq.isEmpty())
             {
                checkbox_recaptcha.setStyle("-fx-border-color:red"); 
                 tf_control_saisie.setText("");    
             }
        
        else 
        {
             checkbox_recaptcha.setStyle("-fx-border-color:transparent");
             tf_control_saisie.setText("");  
            
         int idS=Integer.valueOf(tf_rec_mod.getText());  
       //System.out.println(idS +"  "+ tf_titre.getText()+"  "+ Date.valueOf(d_date.getValue())+" "+s_niveau.getSelectionModel().getSelectedItem()+" "+s_prix.getText());
        Examen exam = new Examen(idS, tf_titre.getText(),d_date.getValue(),s_niveau.getSelectionModel().getSelectedItem(),Integer.valueOf(s_prix.getText()) ,tf_support.getText());
                ExamenService exSer = ExamenService.getInstance();
                    exSer.update(exam);
//                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Dialog");
//                   alert.setHeaderText(null);
//                   alert.setContentText("examen modifiée avec succés!");
//                   alert.show();
                Stage stage = (Stage) this.btn_modifier.getScene().getWindow();
                 stage.close();
                     //////notificationnnnnnn  
            Notifications.create()
                 .title("Modifier Examen")
                 .text("examen << "+tf_titre.getText()+" >>est modifié avec success")
                 .darkStyle().position(Pos.BOTTOM_RIGHT).showConfirm();
             //////notificationnnnnnn 
        }
                  
    }

    @FXML
    private void recuperer_exam(ActionEvent event) {
          String rq="";
   
          if(tf_rec_mod.getText().isEmpty())
       {
           
           rq+="veuillez remplir le champ id !\n";
       }
          
        if(!rq.isEmpty())
        {
            tf_control_saisie.setText(rq);
            
        }
        else 
        {
             tf_control_saisie.setText("");
        int idS=Integer.valueOf(tf_rec_mod.getText());
                   ExamenService exSer = ExamenService.getInstance();
                   
                  Examen exx=new Examen();
                  exx= exSer.displayById(idS);
//                  String s=exx.getTitreE();
//                  System.out.println(exx.getIdE());
                tf_titre.setText(exx.getTitreE());
              s_prix.setText(String.valueOf(exx.getPrixE()));
                 d_date.setValue(exx.getDate());
               s_niveau.setValue(exx.getNiveau());
               tf_support.setText(exx.getSupport());
                 //////notificationnnnnnn  
            Notifications.create()
                 .title("Récupérer Examen")
                 .text("Examen << "+exx.getTitreE()+" >>est recuperé avec success")
                 .darkStyle().position(Pos.BOTTOM_RIGHT).showInformation();
             //////notificationnnnnnn  
         
               
        }     
    }

   

    @FXML
    private void ajouterExamen(ActionEvent event) {
          String rq ="";
           //controle de saisie 
       if(tf_titre.getText().isEmpty())
       {
           rq+="veuillez remplir le champ Titre !\n";
       }
       if(d_date.getValue()==null)
       {
           
          rq+="veuillez remplir le champ Date !\n";
       }
       if(s_niveau.getSelectionModel().getSelectedItem()==null)
       {
           
           rq+="veuillez remplir le champ Niveau !\n";
       }
          if(String.valueOf(s_prix.getText()).isEmpty())
       {
           
           rq+="veuillez remplir le champ Prix !\n";
       }
                 if(tf_support.getText().isEmpty())
       {
           
           rq+="veuillez remplir le champ support Link !\n";
       }
                 if(!checkbox_recaptcha.isSelected())
             {
                checkbox_recaptcha.setStyle("-fx-border-color:red"); 
                     
             }
          
         if(!rq.isEmpty() && !checkbox_recaptcha.isSelected())
        {
            tf_control_saisie.setText(rq);
             checkbox_recaptcha.setStyle("-fx-border-color:red");
            
        }  
         else if(!rq.isEmpty() && checkbox_recaptcha.isSelected())
        {
            tf_control_saisie.setText(rq);
             checkbox_recaptcha.setStyle("-fx-border-color:transparent");
            
        }
        else if(!checkbox_recaptcha.isSelected()&&rq.isEmpty())
             {
                checkbox_recaptcha.setStyle("-fx-border-color:red"); 
                 tf_control_saisie.setText("");    
             }
        
        else 
        {
             checkbox_recaptcha.setStyle("-fx-border-color:transparent");
             tf_control_saisie.setText("");   
            
            Examen exam = new Examen(tf_titre.getText(),d_date.getValue(),s_niveau.getSelectionModel().getSelectedItem(),Integer.valueOf(s_prix.getText()) ,tf_support.getText());
            //  Examen exam = new Examen(titre,da,niveau,prix);
            ExamenService exSer = ExamenService.getInstance();
            exSer.insert(exam);
            Stage stagea = (Stage) this.btn_modifier.getScene().getWindow();
              stagea.close();
              
            //////notificationnnnnnn  
            Notifications.create()
                 .title("Ajout d'examen")
                 .text("examen << "+tf_titre.getText()+" >>est ajouté avec success")
                 .darkStyle().position(Pos.BOTTOM_RIGHT).showConfirm();
             //////notificationnnnnnn  
               

            
               


                
        }
        
   
    }
    
    
    public void setetxt(Examen exx)
    {
           tf_rec_mod.setText(toString().valueOf(exx.getIdE()));
           tf_titre.setText(exx.getTitreE());
              s_prix.setText(String.valueOf(exx.getPrixE()));
                 d_date.setValue(exx.getDate());
               s_niveau.setValue(exx.getNiveau());
               tf_support.setText(exx.getSupport());
    }

  
   

    
    
}

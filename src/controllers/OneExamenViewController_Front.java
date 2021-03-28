/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Client;
import entities.Examen;
import entities.InscriEvent;
import entities.InscripExam;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
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
import services.InscriEventS;
import services.InscriptionExService;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class OneExamenViewController_Front implements Initializable {

    @FXML
    private Text dateLabel;
    @FXML
    private ImageView detail;
    @FXML
    private WebView quiz_view;
    @FXML
    private Text Titre_examen;
    @FXML
    private TextField date_ex;
    @FXML
    private TextField prix_ex;
    @FXML
    private Text link_ex;

     private Examen  exam;
       public Client c;
    
    @FXML
    private Text nbrParticip1;
    @FXML
    private Button btn_inscrire;
    @FXML
    private Button btn_annulerInsc;
    
      /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }  
    public void setData(Examen ex, Client cl) {
        this.c=cl;
       
        this.exam = ex;
        prix_ex.setText(String.valueOf(exam.getPrixE()));
        date_ex.setText(exam.getDate().toString());
        link_ex.setText(exam.getSupport());
        
        Titre_examen.setText(exam.getTitreE());
        dateLabel.setText(exam.getDate().toString());
        
        final WebEngine web = quiz_view.getEngine();
       String urlweb = exam.getSupport();
       web.load(urlweb);
        
     
    }
     

    @FXML
    private void inscrirption_exam(ActionEvent event) throws MessagingException {
      InscriptionExService ies= InscriptionExService.getInstance();
        InscripExam ie=new InscripExam();
      //  System.out.println(this.exam.getTitreE()+" "+this.c.getNom());
        
        ie=ies.displayeByIdClientIdExam(this.c.getId(),this.exam.getIdE());
//        System.out.println(ie);
        if(ie.getIdinscri()==-1){
            FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/inscriExam.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(EventsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               InscriExamController iec=Loader.getController();
                iec.c=this.c;
                iec.ex=this.exam;
               iec.setInfoExam(iec.ex);
              iec.setInfoUser();
                Parent p=Loader.getRoot();
                Stage inscriE=new Stage();
                Scene scene = new Scene(p);
                inscriE.setScene(scene);
                inscriE.show();
        }
        else {
             Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Deja fait");
        alert.setHeaderText(null);
        alert.setContentText("vous êtes déjà inscrit dans cet événement");
        alert.show();
        }
    }

    @FXML
    private void annuler_inscrption(ActionEvent event) {
         InscriptionExService ies=InscriptionExService.getInstance();
        InscripExam ie=new InscripExam();
        ie=ies.displayeByIdClientIdExam(this.c.getId(),this.exam.getIdE());
        if(ie.getIdinscri()==-1){
             Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Pas d'inscription");
        alert.setHeaderText(null);
        alert.setContentText("il n'y a pas d'inscription avec votre nom Mr "+this.c.getNom()+" "+this.c.getPrenom());
        alert.show();
        }
        else {
            ies.annulerInscri(this.c.getId(), this.exam.getIdE());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Annulation");
        alert.setHeaderText(null);
        alert.setContentText("Inscription Annuler avec succées Mr "+this.c.getNom()+" "+this.c.getPrenom());
        alert.show();
        }
        
    }

}

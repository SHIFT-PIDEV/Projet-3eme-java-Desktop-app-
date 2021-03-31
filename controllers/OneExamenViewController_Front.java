/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Client;
import entities.Commentaire;
import entities.Examen;
import entities.InscriEvent;
import entities.InscripExam;
import entities.RatingR;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
import services.EventS;
import services.InscriEventS;
import services.InscriptionExService;
import services.ratingService;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class OneExamenViewController_Front implements Initializable {

    @FXML
    private Text dateLabel;
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
       private  List<RatingR> rates = new ArrayList<>();
    
    @FXML
    private Text nbrParticip1;
    @FXML
    private Button btn_inscrire;
    @FXML
    private Button btn_annulerInsc;
    @FXML
    private ImageView commenter_btn;
    @FXML
    private ScrollPane scrollPaneComm;
    @FXML
    private GridPane gridComm;
    
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
        ratingService es= ratingService.getInstance();
        this.exam = ex;
        prix_ex.setText(String.valueOf(exam.getPrixE()));
        date_ex.setText(exam.getDate().toString());
        link_ex.setText(exam.getSupport());
        
        Titre_examen.setText(exam.getTitreE());
        dateLabel.setText(exam.getDate().toString());
        
        final WebEngine web = quiz_view.getEngine();
       String urlweb = exam.getSupport();
       web.load(urlweb);
       
        //afficher les commentaires
        this.rates=es.displayRatings(ex.getIdE());
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <rates.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/one_rating_v.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                one_rating_vController ratingC = fxmlLoader.getController();
                ratingC.client=this.c;
               
                ratingC.setDataRating(rates.get(i), this.c);

                if (column == 1) {
                    column = 0;
                    row++;
                }
                
                 gridComm.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridComm.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridComm.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridComm.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridComm.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridComm.setMaxHeight(Region.USE_PREF_SIZE);
                gridComm.add(anchorPane, column++, row); 
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
     
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

    @FXML
    private void ajouterCom_ex(MouseEvent event) {
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/addRatingCom.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(EventForClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
              
               AddRatingComControllers aRC=Loader.getController();
                aRC.c=this.c;
                aRC.exam=this.exam;
              
                Parent p=Loader.getRoot();
                Stage comm=new Stage();
                Scene scene = new Scene(p);
                comm.initStyle(StageStyle.TRANSPARENT);
                comm.setScene(scene);
                comm.show();
    }

}

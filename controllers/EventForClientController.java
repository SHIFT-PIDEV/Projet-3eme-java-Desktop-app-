/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Client;
import entities.Commentaire;
import entities.Event;
import entities.InscriEvent;
import entities.Like;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.EventS;
import services.InscriEventS;
import services.LikeS;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EventForClientController implements Initializable {

    @FXML
    private Text dateLabel;
    @FXML
    private ImageView detail;
    @FXML
    private ImageView eventPic;
    @FXML
    private Text nameEvent;
    @FXML
    private Text nbrParticip;
    @FXML
    private Button inscritButton;
    @FXML
    private Button annulerButton;
    @FXML
    private TextArea desc;
    @FXML
    private TextField heure;
    @FXML
    private ScrollPane scrollPaneComm;
    @FXML
    private GridPane gridComm;
    private  List<Commentaire> comms = new ArrayList<>();
    @FXML
    private FontAwesomeIconView likeIcon;
    @FXML
    private Text likeNumber;
    @FXML
    private Text commNumber;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    private Event event;
     public Client c;
    public void setData(Event event,Client c) {
        this.event = event;
        this.c=c;
        EventS es= EventS.getEventS();
        
        nameEvent.setText(event.getNomE());
        dateLabel.setText(event.getDateD().toString());
        desc.setText(event.getDescE());
        heure.setText("De "+event.getHeure()+"H"+" Jusqu'à "+(int)(event.getHeure()+event.getDuree())+"H ");
        nbrParticip.setText("100");
        Image image = new Image(getClass().getResourceAsStream(event.getImage()));
        eventPic.setImage(image);
        nbrParticip.setText(String.valueOf(es.laListeDesInscription(event.getIdE()).size()));
        //afficher les commentaires
        this.comms=es.displayComm(event.getIdE());
        this.commNumber.setText(String.valueOf(this.comms.size()));
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <comms.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/comm.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CommController commController = fxmlLoader.getController();
                commController.setDataComm(comms.get(i));

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
    private void toSinscrireForm(ActionEvent event) {
        InscriEventS ies= InscriEventS.getInscriEvent();
        InscriEvent ie=new InscriEvent();
        ie=ies.displayeByIdClientIdEvent(this.c.getId(),this.event.getIdE());
       
        if(ie.getIdinscri()==-1){
            FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/inscriptionE.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(EventsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               InscriptionEController iec=Loader.getController();
                iec.c=this.c;
               iec.setNameImageEvent(this.event.getNomE(),this.eventPic.getImage(),this.event.getIdE()
                       ,String.valueOf(this.event.getDateD()),this.event.getHeure());
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
    private void annulerInscrit(ActionEvent event) {
        InscriEventS ies=InscriEventS.getInscriEvent();
        InscriEvent ie=new InscriEvent();
        ie=ies.displayeByIdClientIdEvent(this.c.getId(),this.event.getIdE());
        if(ie.getIdinscri()==-1){
             Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Pas d'inscription");
        alert.setHeaderText(null);
        alert.setContentText("il n'y a pas d'inscription avec votre nom Mr "+this.c.getNom()+" "+this.c.getPrenom());
        alert.show();
        }
        else {
            ies.annulerInscri(this.c.getId(), this.event.getIdE());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Annulation");
        alert.setHeaderText(null);
        alert.setContentText("Inscription Annuler avec succées Mr "+this.c.getNom()+" "+this.c.getPrenom());
        alert.show();
        }
       
    }

    @FXML
    private void addRemoveLike(MouseEvent event) {
        LikeS ls=LikeS.getLikeS();
        Like k=new Like();
        k=ls.displayLike(this.event.getIdE(), this.c.getId());
        
        if(k.getIdClient()==-1&&k.getIdEvent()==-1){
            k.setIdClient(this.c.getId());
            k.setIdEvent(this.event.getIdE());
            ls.insertLike(k);
            this.likeIcon.setStyle("-fx-fill:red;");
            
        }
        else{
            ls.deleteLike(this.event.getIdE(), this.c.getId());
            this.likeIcon.setStyle("-fx-fill:#363434;");
        }
    }
    
    public void setLikeIconColor(){
        LikeS ls=LikeS.getLikeS();
        Like k=new Like();
        k=ls.displayLike(this.event.getIdE(), this.c.getId());
        int nbrLike=0;
        nbrLike=ls.displayAllbyIdEvent(this.event.getIdE());
        this.likeNumber.setText(String.valueOf(nbrLike));
        if(k.getIdClient()==-1&&k.getIdEvent()==-1){
            this.likeIcon.setStyle("-fx-fill:#363434;");
        }
        else{
            this.likeIcon.setStyle("-fx-fill:red;");
        }
    }
    @FXML
    private void addComm(MouseEvent event) {
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/addComm.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(EventForClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               AddCommController acc=Loader.getController();
                acc.client=this.c;
                acc.event=this.event;
                Parent p=Loader.getRoot();
                Stage comm=new Stage();
                Scene scene = new Scene(p);
                comm.initStyle(StageStyle.TRANSPARENT);
                comm.setScene(scene);
                comm.show();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Commentaire;
import entities.Event;
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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.EventS;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    private Event event;
     
    public void setData(Event event) {
        this.event = event;
        EventS es= EventS.getEventS();
        
        nameEvent.setText(event.getNomE());
        dateLabel.setText(event.getDateD().toString());
        desc.setText(event.getDescE());
        heure.setText("De "+event.getHeure()+"H"+" Jusqu'à "+(int)(event.getHeure()+event.getDuree())+"H ");
        nbrParticip.setText("100");
        Image image = new Image(getClass().getResourceAsStream(event.getImage()));
        eventPic.setImage(image);
        nbrParticip.setText(String.valueOf(es.displayInscriptions(event.getIdE()).size()));
        //afficher les commentaires
        this.comms=es.displayComm(event.getIdE());
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
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/inscriptionE.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(EventsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               InscriptionEController iec=Loader.getController();
               iec.setNameImageEvent(this.event.getNomE(),this.eventPic.getImage(),this.event.getIdE()
                       ,String.valueOf(this.event.getDateD()),this.event.getHeure());
                Parent p=Loader.getRoot();
                Stage inscriE=new Stage();
                Scene scene = new Scene(p);
                inscriE.setScene(scene);
                inscriE.show();
                
    }

    @FXML
    private void annulerInscrit(ActionEvent event) {
    }
    
}

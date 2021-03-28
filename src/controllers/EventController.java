/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import javafx.scene.image.Image;
import entities.Event;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.EventS;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EventController implements Initializable {

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
    private Button modifButton;
    @FXML
    private Button suppButton;
    @FXML
    private Button inscritsButton;
    @FXML
    private TextArea desc;
    @FXML
    private TextField heure;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
        Image image = new Image(getClass().getResourceAsStream(event.getImage()));
        eventPic.setImage(image);
        nbrParticip.setText(String.valueOf(es.laListeDesInscription(event.getIdE()).size()));
    }
    
    @FXML
    private void toUpdateForm(ActionEvent event) {
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/updateEvent.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(EventsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               UpdateEventController uec=Loader.getController();
               uec.setTexts(String.valueOf(this.event.getIdE()),String.valueOf(this.event.getIdF()), this.event.getNomE(), this.event.getDateD(),
                       String.valueOf((int)this.event.getDuree()), String.valueOf(this.event.getHeure()),
                       this.event.getDescE(), this.event.getImage());
                Parent p=Loader.getRoot();
                Stage updateStage=new Stage();
                Scene scene = new Scene(p);
                updateStage.setScene(scene);
                updateStage.show();
                
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
        EventS es ;
        es=EventS.getEventS();
        es.deleteEvent(this.event.getIdE());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Event supprimée avec succés!");
        alert.show();
    }

    @FXML
    private void lesInscriptions(ActionEvent event) {
        EventS es;
        es=EventS.getEventS();
        this.event.listInscri=es.laListeDesInscription(this.event.getIdE());
        
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/inscriList.fxml"));
        try {
            loader.load();
           
               
        } catch (IOException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
         InscriListController ilc=loader.getController();
               ilc.setText(this.nbrParticip.getText(),this.event.listInscri);
               ilc.afficher();
        Parent p=loader.getRoot();
                Stage lesInscrits=new Stage();
                Scene scene = new Scene(p);
                lesInscrits.setScene(scene);
                lesInscrits.show();
        
    }

    
}

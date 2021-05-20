/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ClientS;
import services.InscriEventS;
import services.LikeS;
import services.NotificationS;
import upgradi.Upgradi;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class Dashbordv2Controller implements Initializable {

    @FXML
    private Text iconUserDef;
    @FXML
    private HBox dashbord;
    @FXML
    private Button createEventBt;
    @FXML
    private Text nbrUsers;
    @FXML
    private Text nbrLikes;
    @FXML
    private Text nbrInscri;
    @FXML
    private Text nbrShare;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(){
        ClientS cs=ClientS.getClientS();
        this.nbrUsers.setText(String.valueOf(cs.displayAllUsers()));
        
        LikeS ls=LikeS.getLikeS();
        this.nbrLikes.setText(String.valueOf(ls.nbrAllLike()));
        
        NotificationS ns=NotificationS.getInsctance();
        this.nbrShare.setText(String.valueOf(ns.displayAllShare()));
        
        InscriEventS ies=InscriEventS.getInscriEvent();
        this.nbrInscri.setText(String.valueOf(ies.displayAllInscriEvent()));
    }
    @FXML
    private void showDashbord(MouseEvent event) {        
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/dashbordv2.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(MyNotificationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               Dashbordv2Controller dv2=Loader.getController();
              dv2.setData();
                Parent p=Loader.getRoot();
                Stage s ;
                s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(p);
                s.setScene(scene);
                s.show(); 
    }

    @FXML
    private void eventsView(MouseEvent event) {
        Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/eventsView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
       }
     @FXML
    private void deconnexion(MouseEvent event) {
         Upgradi u=new Upgradi();
        Stage s=(Stage)this.createEventBt.getScene().getWindow();
        s.close();
        u.callStart();
    }

    @FXML
    private void createEvent(ActionEvent event) {
    }

    @FXML
    private void toCours(MouseEvent event) {
    }

    @FXML
    private void toUsers(MouseEvent event) {
    }

    @FXML
    private void toReclamations(MouseEvent event) {
    }

    @FXML
    private void toDemande(MouseEvent event) {
    }

    @FXML
    private void toCommande(MouseEvent event) {
    }

    @FXML
    private void toCoupons(MouseEvent event) {
    }

    @FXML
    private void toPackets(MouseEvent event) {
    }
    }

   
    


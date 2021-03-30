/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Client;
import entities.Event;
import entities.Notification;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ClientS;
import services.NotificationS;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ShareController implements Initializable {
      
    @FXML
    private JFXTextField friendUserName;
    public Client c;
    public Event e;
    @FXML
    private JFXButton share;
    @FXML
    private Text checkTheUeserName;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void share(ActionEvent event) {
        
         ClientS csS =ClientS.getClientS();
        Client clientSource=new Client();
        clientSource=csS.displayForShare(this.friendUserName.getText());
        
        if(clientSource.getId()==-1){
            this.checkTheUeserName.setText("utilisateur introuvable");
        }
        else{
            NotificationS ns=NotificationS.getInsctance();
            ns.insertNot( this.c.getId(), clientSource.getId(),this.e.getIdE());
            csS.incrementNotif(clientSource.getId());
            this.checkTheUeserName.setText("event partagé avec success");
            Stage s= (Stage)this.share.getScene().getWindow();
             s.close();
        }
        
    }

    @FXML
    private void closeComm(MouseEvent event) {
        Stage s= (Stage)this.share.getScene().getWindow();
        s.close();
    }
    
}

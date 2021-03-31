/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import entities.Client;
import entities.Commentaire;
import entities.Event;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.CommentaireS;

/**
 * FXML Controller class
 *
 * @author asus
 */

public class AddCommController implements Initializable {

    @FXML
    private JFXTextArea commText;
    
    public Client client;
    public Event event;
    @FXML
    private JFXButton button;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterComm(ActionEvent event) {
        Commentaire com=new Commentaire();
        com.setIdClient(this.client.getId());
        com.setIdEvent(this.event.getIdE());
        com.setDesc(commText.getText());
        if(com.getDesc().equals("")==false&&com.getDesc().equals(" ")==false){
            CommentaireS cs= CommentaireS.getCommentaireS();
         cs.insert(com);
         Stage s= (Stage)this.button.getScene().getWindow();
        s.close();
        }
        
    }

    @FXML
    private void closeComm(MouseEvent event) {
        Stage s= (Stage)this.button.getScene().getWindow();
        s.close();
    }
    
}

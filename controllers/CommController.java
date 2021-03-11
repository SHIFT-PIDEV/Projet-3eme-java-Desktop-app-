/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Commentaire;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import services.CommentaireS;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class CommController implements Initializable {

    @FXML
    private AnchorPane comm;
    @FXML
    private Text userName;
    @FXML
    private Text commentaire;
    
    private Commentaire c;
     public void setDataComm(Commentaire c) {
        this.c = c;
        CommentaireS es= CommentaireS.getCommentaireS();
        
        userName.setText("User x :");
        commentaire.setText(c.getDesc());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Client;
import entities.Commentaire;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import services.ClientS;
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
    
    private Commentaire co;
    private Client client;
     public void setDataComm(Commentaire c) {
        this.co = c;
        CommentaireS es= CommentaireS.getCommentaireS();
        ClientS cs=ClientS.getClientS();
        this.client=cs.displayClientById(this.co.getIdClient());
        userName.setText(this.client.getUserName()+" :");
        commentaire.setText(c.getDesc());
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

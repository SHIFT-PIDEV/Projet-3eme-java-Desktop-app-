/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import entities.Client;
import entities.commentairecour;
import entities.cour;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.commentairecservice;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class AjoutercommController implements Initializable {

    @FXML
    private JFXTextArea commText;
    @FXML
    private JFXButton button;
 public Client client;
    public cour cour;
    /**
     * Initializes the controller class.
     */
   public void setinfouserandcours(Client c, cour cour)
   {
         System.out.println("hhhhhhhni lenna a a a a a ");
       this.client=c;
       System.out.println(c+"hhhhhhhni lenna a a a a a ");
       this.cour=cour;
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterComm(ActionEvent event) {
            commentairecour com=new commentairecour();
        com.setIdClient(this.client.getId());
        System.out.println(this.client.getId());
        com.setIdCour(this.cour.getId());
        com.setDesc(commText.getText());
        if(com.getDesc().equals("")==false&&com.getDesc().equals(" ")==false){
            commentairecservice cs= commentairecservice.getCommentairecservice();
         cs.insert(com);
         Stage s= (Stage)this.button.getScene().getWindow();
        s.close();
        }
    }

    @FXML
    private void closeComm(MouseEvent event) {
    }
    
}

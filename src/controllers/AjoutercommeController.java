/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import entities.Client;
import entities.commentairepack;
import entities.packge;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.commentairepackservice;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class AjoutercommeController implements Initializable {

    @FXML
    private JFXTextArea commText;
    @FXML
    private JFXButton button;

     public Client client;
    public packge packge;
    /**
     * Initializes the controller class.
     */
     public void setinfouserandcours(Client c, packge packge)
   {
        // System.out.println("hhhhhhhni lenna a a a a a ");
       this.client=c;
      // System.out.println(c+"hhhhhhhni lenna a a a a a ");
       this.packge=packge;
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterComm(ActionEvent event) {
             commentairepack com=new commentairepack();
        com.setIdClient(this.client.getId());
        System.out.println(this.client.getId());
      com.setIdpackage(this.packge.getId());
              com.setDesc(commText.getText());
        System.out.println(com.toString());
       if(com.getDesc().equals("")==false&&com.getDesc().equals(" ")==false){
            commentairepackservice cs= commentairepackservice.getcommentairepackservice() ;
            
         cs.insert(com);
         Stage s= (Stage)this.button.getScene().getWindow();
        s.close();
       }
        }
    

    @FXML
    private void closeComm(MouseEvent event) {
    }
    
    
}

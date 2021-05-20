/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import upgradi.Upgradi;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class MonPanierController implements Initializable {

    @FXML
    private Text notifNumbre;
    @FXML
    private FontAwesomeIconView notif;
    @FXML
    private Text iconUserDef;
    @FXML
    private HBox dashbord;
    @FXML
    private HBox eventsInEventView;
    @FXML
    private TextField searchBar;
    @FXML
    private Button devFormateur;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button devFormateur1;
    @FXML
    private Button devFormateur11;
    
    Client c=new Client();
    
          public void setNameUser(){
              this.iconUserDef.setText(this.c.getNom()+" "+this.c.getPrenom());
          }
          public void afficherAll(){
              
          }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void toEventShare(MouseEvent event) {
    }

    @FXML
    private void showDashbord(MouseEvent event) {
    }

    @FXML
    private void eventsView(MouseEvent event) {
    }

    @FXML
    private void deconnecter(MouseEvent event) {
        Upgradi u=new Upgradi();
        Stage s=(Stage)this.devFormateur.getScene().getWindow();
        s.close();
        u.callStart();
    }

    @FXML
    private void trierLesEvents(MouseEvent event) {
    }

    @FXML
    private void refreshPage(MouseEvent event) {
    }

    @FXML
    private void recherche(KeyEvent event) {
    }

    @FXML
    private void searchByNameAndDesc(MouseEvent event) {
    }

    @FXML
    private void devenirFormateur(ActionEvent event) {
    }
    
}

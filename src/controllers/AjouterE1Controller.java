/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Event;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.EventS;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterE1Controller implements Initializable {

    @FXML
    private TextField idf;
    @FXML
    private TextField nomE;
    @FXML
    private DatePicker dd;
    @FXML
    private TextField duree;
    @FXML
    private TextArea desc;
    @FXML
    private Button btn_s;
    @FXML
    private Button btn_r;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void ajouter_events(ActionEvent event) {
        
        Event e=new Event(Integer.parseInt(idf.getText()),nomE.getText(),dd.getValue(),
                Integer.parseInt(duree.getText()),desc.getText());
            EventS es= EventS.getEventS();
            es.insert(e);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Event insérée avec succés!");
        alert.show();
        System.out.println("Timeeeeeeee"+dd.getValue());
        
        
    }
    
}

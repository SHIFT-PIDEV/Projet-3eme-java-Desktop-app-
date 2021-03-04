/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Event;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.EventS;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class UpdateDeleteEController implements Initializable {

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
    private Button btn_update;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_find;
    @FXML
    private TextField idE;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void update_event(ActionEvent event) {
        Event e=new Event();
        e.setIdE(Integer.parseInt(idE.getText()));
        e.setIdF(Integer.parseInt(idf.getText()));
        e.setNomE(nomE.getText());
        e.setDateD(dd.getValue());
        e.setDuree(Float.parseFloat(duree.getText()));
        e.setDescE(desc.getText());
        
        EventS es= EventS.getEventS();
        es.updateEvent(e);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Event Modifier avec succés!");
        alert.show();
        idf.setText("");
        nomE.setText("");
        duree.setText("");
        desc.setText("");
    }

    @FXML
    private void delete_event(ActionEvent event) {
        int id=Integer.parseInt(idE.getText());
        EventS es=EventS.getEventS();
        es.deleteEvent(id);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Event supprimée avec succés!");
        alert.show();
        idf.setText("");
        nomE.setText("");
        duree.setText("");
        desc.setText("");
         
    }

    @FXML
    private void find_event(ActionEvent event) {
        int id=Integer.parseInt(idE.getText());
        EventS es;
        es=EventS.getEventS();
        Event e=es.displayeById(id);
        
        idf.setText(String.valueOf(e.getIdF()));
        nomE.setText(e.getNomE());
        dd.setValue(e.getDateD());
        duree.setText(String.valueOf(e.getDuree()));
        desc.setText(e.getDescE());
        
        
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Event;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JFileChooser;
import services.EventS;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class UpdateEventController implements Initializable {

    @FXML
    private TextField idf1;
    @FXML
    private TextField nomE1;
    @FXML
    private DatePicker dd1;
    @FXML
    private TextField heure1;
    @FXML
    private TextField duree1;
    @FXML
    private TextArea desc1;
    @FXML
    private Button btn_choipic;
    @FXML
    private Button btn_updateEvent;
    @FXML
    private TextField picPath;
    private int idEvent=10;
    @FXML
    private TextField idE;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }  
    public void setTexts(String idEvent,String idFormat,String nomEv,LocalDate dd1E,String dureeE ,String heureE,String descE,String path){
        idE.setText(idEvent);
        idf1.setText(idFormat);
        nomE1.setText(nomEv);
        dd1.setValue(dd1E);
        duree1.setText(dureeE);
        heure1.setText(heureE);
        desc1.setText(descE);
        picPath.setText(path);
    }

    @FXML
    private void choisirImage(ActionEvent event) {
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        if(f!=null){
            String filename=f.getAbsolutePath();
        String s= "C:\\Users\\asus\\Documents\\NetBeansProjects\\upgradi\\src\\";
        picPath.setText(filename.substring(s.length()-1));
        }
        
    }
public boolean isInt(TextField t){
        try{
            int value=Integer.parseInt(t.getText());
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    public boolean checkHeure(TextField t){
        return Integer.parseInt(t.getText())>=0&&Integer.parseInt(t.getText())<=23;
    }
    @FXML
    private void updateEvent(ActionEvent event) {
        if(idf1.getText().isEmpty()||nomE1.getText().isEmpty()||heure1.getText().isEmpty()||duree1.getText().isEmpty()
             || (!isInt(idf1))||(!isInt(heure1))||(!isInt(duree1))||(!checkHeure(heure1))||dd1.getValue()==null)
         { 
             if(idf1.getText().isEmpty()||(!isInt(idf1)))
            idf1.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         else
             idf1.setStyle("-fx-border-color: #0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         
         if(nomE1.getText().isEmpty())  
            nomE1.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         else
             nomE1.setStyle("-fx-border-color:#0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         
         if(heure1.getText().isEmpty()||(!isInt(heure1))||(!checkHeure(heure1)))
            heure1.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         else 
             heure1.setStyle("-fx-border-color:#0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         
         if(duree1.getText().isEmpty()||(!isInt(duree1)))
            duree1.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         else
            duree1.setStyle("-fx-border-color:#0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         
          if(dd1.getValue()==null)   
            dd1.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
          else
            dd1.setStyle("-fx-border-color:#0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        
        alert.setHeaderText(null);
        alert.setContentText("check your inputs" );
        alert.show(); 
         }
        else{
            Event e=new Event();
        e.setIdE(Integer.parseInt(idE.getText()));
        e.setIdF(Integer.parseInt(idf1.getText()));
        e.setNomE(nomE1.getText());
        e.setDateD(dd1.getValue());
        e.setHeure(Integer.parseInt(heure1.getText()));
        e.setDuree(Float.parseFloat(duree1.getText()));
        e.setDescE(desc1.getText());
        e.setImage(picPath.getText());
        
        EventS es= EventS.getEventS();
        es.updateEvent(e);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Event Modifier avec succés!");
        alert.show();
        idf1.setStyle("-fx-border-color: #0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
        
        nomE1.setStyle("-fx-border-color: #0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         
        heure1.setStyle("-fx-border-color: #0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         
        duree1.setStyle("-fx-border-color: #0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
        
        dd1.setStyle("-fx-border-color:#0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;"); 
        }
        
        
        
    }
    
}

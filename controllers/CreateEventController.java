/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Event;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javax.swing.JFileChooser;
import services.EventS;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class CreateEventController implements Initializable {

    @FXML
    private TextField idf;
    @FXML
    private TextField nomE;
    @FXML
    private DatePicker dd;
    @FXML
    private TextField heure;
    @FXML
    private TextField duree;
    @FXML
    private TextArea desc;
    @FXML
    private TextField picPath;
    private ImageView pic;
    @FXML
    private Button btn_choipic;
    @FXML
    private Button btn_createEvent;
    @FXML
    private Button btn_resetAll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void choisirImage(ActionEvent event) {
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        if(chooser.getSelectedFile()!=null){
            File f=chooser.getSelectedFile();
        
        String filename=f.getAbsolutePath();
        String s= "D:\\--Etudes--\\3eme\\S2\\PI-DEV\\JAVA\\upgradi skander\\upgradi\\src\\";
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
    private void createEvent(ActionEvent event) {
         if(idf.getText().isEmpty()||nomE.getText().isEmpty()||heure.getText().isEmpty()||duree.getText().isEmpty()
                 ||(!isInt(idf))||(!isInt(heure))||(!isInt(duree))||(!checkHeure(heure))||dd.getValue()==null)
         {
         if(idf.getText().isEmpty()||(!isInt(idf)))
            idf.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         else
             idf.setStyle("-fx-border-color: #0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         
         if(nomE.getText().isEmpty())  
            nomE.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         else
             nomE.setStyle("-fx-border-color:#0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         
         if(heure.getText().isEmpty()||(!isInt(heure))||(!checkHeure(heure)))
            heure.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         else 
             heure.setStyle("-fx-border-color:#0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         
         if(duree.getText().isEmpty()||(!isInt(duree)))
            duree.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         else
            duree.setStyle("-fx-border-color:#0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         
          if(dd.getValue()==null)   
            dd.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
          else
            dd.setStyle("-fx-border-color:#0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        
        alert.setHeaderText(null);
        alert.setContentText("check your inputs" );
        alert.show(); 
         }
            
         else{
              Event e=new Event(Integer.parseInt(idf.getText()),nomE.getText(),dd.getValue(),Integer.parseInt(heure.getText()),
              Integer.parseInt(duree.getText()),desc.getText(),picPath.getText());
            EventS es= EventS.getEventS();
            es.insert(e);
             System.out.println("lenaaaaaa"+dd.getValue());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Event insérée avec succés!");
        alert.show();
        
        idf.setStyle("-fx-border-color: #0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
          
        nomE.setStyle("-fx-border-color: #0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         
        heure.setStyle("-fx-border-color: #0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
         
        duree.setStyle("-fx-border-color: #0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
        
        dd.setStyle("-fx-border-color:#0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");  

         }
       
    }

    @FXML
    private void resretAll(ActionEvent event) {
        idf.setText("");
        nomE.setText("");
        dd.setValue(null);
        heure.setText("");
        duree.setText("");
        desc.setText("");
        picPath.setText("");
        
    }

    
}

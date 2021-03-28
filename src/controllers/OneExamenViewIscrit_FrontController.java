/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Client;
import entities.Examen;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class OneExamenViewIscrit_FrontController implements Initializable {

    @FXML
    private Text dateLabel;
    @FXML
    private ImageView detail;
    @FXML
    private WebView quiz_view;
    @FXML
    private TextField date_ex;
    @FXML
    private TextField prix_ex;
    @FXML
    private Text link_ex;
    @FXML
    private Text Titre_examen;
    @FXML
    private Text nbrParticip1;
    @FXML
    private Button btn_passerex;
        private Examen  exam;
       public Client c;
    
     public void setData(Examen ex, Client cl) {
        this.c=cl;
       
        this.exam = ex;
        prix_ex.setText(String.valueOf(exam.getPrixE()));
        date_ex.setText(exam.getDate().toString());
        link_ex.setText(exam.getSupport());
        
        Titre_examen.setText(exam.getTitreE());
        dateLabel.setText(exam.getDate().toString());
        
        final WebEngine web = quiz_view.getEngine();
       String urlweb = exam.getSupport();
       web.load(urlweb);
        
     
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void passerExamen(ActionEvent event) {
         FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/navigateurEx.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(NavigateurExController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               NavigateurExController iec=Loader.getController();
                iec.c=this.c;
                iec.exam=this.exam;
                iec.lien=this.exam.getSupport();
                iec.setNameUser();
               iec.setinfoQuiz(iec.exam.getSupport());
              
                Parent p=Loader.getRoot();
                Stage inscriE=new Stage();
                Scene scene = new Scene(p);
                inscriE.setScene(scene);
                inscriE.show();
    }
    
    
}

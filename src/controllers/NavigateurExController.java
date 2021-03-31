/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Client;
import entities.Examen;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class NavigateurExController implements Initializable {

    @FXML
    private ImageView icon_google;
    @FXML
    private ImageView icon_youtube;
    @FXML
    private ImageView icon_facebook;
    @FXML
    private ImageView icon_quiz;
    @FXML
    private ImageView icon_meet;
    @FXML
    private ImageView icon_classroom;
    @FXML
    private WebView view_web;
public String lien;
public Client c;
public Examen exam;
    @FXML
    private Text iconUserDef;
    @FXML
    private Label labelchrono;
    
     public long  min , sec , hr  , totalsec=0;
    @FXML
    private Button butn_startchrono;
    @FXML
    private AnchorPane all_page;
     
     private  String format(long value)
     {
         if(value<10)
         {
             return 0+""+value;
         }
         return value+"";
     }
     public void convertTime()
     {
         min= TimeUnit.SECONDS.toMinutes(totalsec);
        sec =totalsec - (min * 60);
        hr =TimeUnit.MINUTES.toHours(min);
        min =min - (hr *60);
        String t=format(hr)+ ":" +format(min)+ ":" +format(sec);
        System.out.println(t);
        
        labelchrono.setText(format(hr)+ ":" +format(min)+ ":" +format(sec));
         totalsec--;
     }
    public void setinfoQuiz(String link){
        this.lien=link;
        final WebEngine web = view_web.getEngine();
       String urlweb = this.lien;
       web.load(urlweb);
       this.lien.toString();
    }
    
    public void setNameUser(){
              this.iconUserDef.setText(this.c.getNom()+" "+this.c.getPrenom());
          }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    

    @FXML
    private void to_google(MouseEvent event) {
         final WebEngine web = view_web.getEngine();
       String urlweb = "https://google.com";
       web.load(urlweb);
    
    }

    @FXML
    private void to_youtube(MouseEvent event) {
        final WebEngine web = view_web.getEngine();
       String urlweb = "https://youtube.com";
       web.load(urlweb);
    }

    @FXML
    private void to_facebook(MouseEvent event) {
         final WebEngine web = view_web.getEngine();
       String urlweb = "https://facebook.com";
       web.load(urlweb);
    }

    @FXML
    private void to_quiz(MouseEvent event) throws AWTException {
         final WebEngine web = view_web.getEngine();
       String urlweb = this.lien;
       web.load(urlweb);

    }

    @FXML
    private void to_meet(MouseEvent event) {
             final WebEngine web = view_web.getEngine();
       String urlweb = "https://meet.google.com/";
       web.load(urlweb);
    }

    @FXML
    private void to_classroom(MouseEvent event) {
        final WebEngine web = view_web.getEngine();
       String urlweb = "https://classroom.google.com";
       web.load(urlweb);
    }

    @FXML
    private void startchrono(ActionEvent event) {
        this.setTimer();
       
    }
    public void setTimer()
    {
          System.out.println("hahani filex front view");
        totalsec=5 ;
          Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("after one sec :!");
                convertTime();
                if(totalsec<=0)
                {
                  timer.cancel();
                 all_page.setDisable(true);
                  labelchrono.setText("00:00:00");
             //////notificationnnnnnn  
            Notifications.create()
                 .title("time Fini !!!!!")
                 .text("vous avez deppassé le temps de votre examen ! bye bye")
                 .darkStyle().position(Pos.BOTTOM_RIGHT).showError();
             //////notificationnnnnnn 
             
            }
                    }
                });
            }
        };
        
          timer.schedule(timerTask, 0, 1000);
    }
    
}

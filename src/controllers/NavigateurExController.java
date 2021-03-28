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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.imageio.ImageIO;

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
    
}

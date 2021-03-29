/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import entities.Client;
import entities.Examen;
import entities.RatingR;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ratingService;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class AddRatingComControllers implements Initializable {

    @FXML
    private JFXButton button;
    @FXML
    private TextArea tf_commentaireR;
    @FXML
    private ImageView img_v1;
    @FXML
    private ImageView img_v2;
    @FXML
    private ImageView img_v3;
    @FXML
    private ImageView img_v4;
    @FXML
    private ImageView img_v5;
     public Client c;
     public Examen exam ;
    
      public int ratechoice=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         System.out.println("vous avez choisi le ratechoise num: "+this.ratechoice);
        
    }    

    @FXML
    private void ajouterComm(ActionEvent event) {
         System.out.println(this.c+"hhhhhhhhhhhhhh"+this.exam);
         RatingR rating = new RatingR (this.c.getId(),this.exam.getIdE(),tf_commentaireR.getText(),this.ratechoice);
          ratingService rSer;
        rSer = ratingService.getInstance();
                    rSer.insert(rating);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("Avis inséré avec succés!");
                   alert.show();
                   tf_commentaireR.setText("");
                  
                   
                   
                
    }

    @FXML
    private void closeComm(MouseEvent event) {
         Stage s= (Stage)this.button.getScene().getWindow();
        s.close();
    }

    @FXML
    private void changecolor1(MouseEvent event) {
        System.out.println("1");
        String photo;
          
          Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/yellow_star.png");
           Image imageURL2 = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/white_star.png");
         
        img_v1.setImage(imageURL);
         img_v2.setImage(imageURL2);
         img_v3.setImage(imageURL2);
         img_v4.setImage(imageURL2);
        img_v5.setImage(imageURL2);
         this.ratechoice=1;
        System.out.println("vous avez choisi le ratechoise num: "+this.ratechoice);
    }

    @FXML
    private void changecolor2(MouseEvent event) {
        System.out.println("2");String photo;
          
          Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/yellow_star.png");
           Image imageURL2 = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/white_star.png");
         
        img_v1.setImage(imageURL);
        img_v2.setImage(imageURL);
         img_v3.setImage(imageURL2);
         img_v4.setImage(imageURL2);
        img_v5.setImage(imageURL2);
        this.ratechoice=2;
        System.out.println("vous avez choisi le ratechoise num: "+this.ratechoice);
    }

    @FXML
    private void changecolor3(MouseEvent event) {
        System.out.println("3");
         Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/yellow_star.png");
          Image imageURL2 = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/white_star.png");
         
        img_v1.setImage(imageURL);
        img_v2.setImage(imageURL);
        img_v3.setImage(imageURL);
         img_v4.setImage(imageURL2);
        img_v5.setImage(imageURL2);
         this.ratechoice=3;
       System.out.println("vous avez choisi le ratechoise num: "+this.ratechoice);
    }

    @FXML
    private void changecolor4(MouseEvent event) {
        System.out.println("4");
        Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/yellow_star.png");
        Image imageURL2 = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/white_star.png");
         
        img_v1.setImage(imageURL);
        img_v2.setImage(imageURL);
        img_v3.setImage(imageURL);
        img_v4.setImage(imageURL);
        img_v5.setImage(imageURL2);
         this.ratechoice=4;
        System.out.println("vous avez choisi le ratechoise num: "+this.ratechoice);
    }

    @FXML
    private void changecolor5(MouseEvent event) {
        System.out.println("5");
         Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/yellow_star.png");
          Image imageURL2 = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/white_star.png");
         
        img_v1.setImage(imageURL);
        img_v2.setImage(imageURL);
        img_v3.setImage(imageURL);
        img_v4.setImage(imageURL);
         img_v5.setImage(imageURL);
          this.ratechoice=5;
          System.out.println("vous avez choisi le ratechoise num: "+this.ratechoice);
    }
    
}

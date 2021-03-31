/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Client;
import entities.Commentaire;
import entities.RatingR;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import services.ClientS;
import services.CommentaireS;
import services.ratingService;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class one_rating_vController implements Initializable {

    @FXML
    private AnchorPane comm;
    @FXML
    private Text userName;
    @FXML
    private Text commentaire;
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
    
     public RatingR r;
    public Client client;
     public void setDataRating(RatingR rate,Client c) {
      this.r=rate;
      this.client=c;
         ratingService rs= ratingService.getInstance();
        ClientS cs=ClientS.getClientS();
        //this.client=cs.displayClientById(this.co.getIdClient());
        userName.setText(this.client.getUserName()+" :");
        commentaire.setText(r.getCommentaire());
        if(r.getRate()==1)
        {
            this.changecolor1();
        }
        if(r.getRate()==2)
        {
            this.changecolor2();
        }
        if(r.getRate()==3)
        {
            this.changecolor3();
        }
        if(r.getRate()==4)
        {
            this.changecolor4();
        }
        if(r.getRate()==5)
        {
            this.changecolor5();
        }
        
            
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    public void changecolor1() {
        // System.out.println("1");
        String photo;
          
          Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/yellow_star.png");
           Image imageURL2 = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/white_star.png");
         
        img_v1.setImage(imageURL);
         img_v2.setImage(imageURL2);
         img_v3.setImage(imageURL2);
         img_v4.setImage(imageURL2);
        img_v5.setImage(imageURL2);
     
    }

  
    public void changecolor2() {
       // System.out.println("2");String photo;
          
          Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/yellow_star.png");
           Image imageURL2 = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/white_star.png");
         
        img_v1.setImage(imageURL);
        img_v2.setImage(imageURL);
         img_v3.setImage(imageURL2);
         img_v4.setImage(imageURL2);
        img_v5.setImage(imageURL2);
      
       
    }

    
    public void changecolor3( ) {
      //  System.out.println("3");
         Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/yellow_star.png");
          Image imageURL2 = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/white_star.png");
         
        img_v1.setImage(imageURL);
        img_v2.setImage(imageURL);
        img_v3.setImage(imageURL);
         img_v4.setImage(imageURL2);
        img_v5.setImage(imageURL2);
      
      
    }

   
    public void changecolor4( ) {
      //  System.out.println("4");
        Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/yellow_star.png");
        Image imageURL2 = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/white_star.png");
         
        img_v1.setImage(imageURL);
        img_v2.setImage(imageURL);
        img_v3.setImage(imageURL);
        img_v4.setImage(imageURL);
        img_v5.setImage(imageURL2);
      
      
    }

    
    public void changecolor5( ) {
       // System.out.println("5");
         Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/yellow_star.png");
          Image imageURL2 = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/images/white_star.png");
         
        img_v1.setImage(imageURL);
        img_v2.setImage(imageURL);
        img_v3.setImage(imageURL);
        img_v4.setImage(imageURL);
         img_v5.setImage(imageURL);
       
    }
    
}

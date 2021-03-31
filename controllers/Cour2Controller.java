/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Client;
import entities.commentairecour;
import entities.cour;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.courservice;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class Cour2Controller implements Initializable {

    @FXML
    private ImageView courpic;
    @FXML
    private JFXButton pannier;
    @FXML
    private JFXButton examen;
    @FXML
    private FontAwesomeIconView likeIcon;
    @FXML
    private Text likeNumber;
    @FXML
    private Text commNumber;
    @FXML
    private ImageView detail;
    @FXML
    private Text namecour;
    @FXML
    private ScrollPane scrollPaneComm;
    @FXML
    private GridPane gridComm;

    /**
     * Initializes the controller class.
     */
    private final listdat listdata = new listdat();
    private final List<cour> cours = new ArrayList<>();
    
     private  List<commentairecour> comms = new ArrayList<>();
    public cour cour;
     public Client c;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setuserinfo(Client c)
    {
        this.c=c;
        System.out.println(this.c+"fi cour2 controller aaaaaaa");
    }
     
     public void setData(cour cour,Client c) {
        this.cour= cour;
        this.c=c;
        
      courservice es= courservice.getInstance();
        
        namecour.setText(cour.getNom_cour());
        
           String photo;
          photo = cour.getImage_v();
          Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/image/"+photo);
        courpic.setImage(imageURL);
       
        //afficher les commentaires
        this.comms=es.displayComm(cour.getId());
        this.commNumber.setText(String.valueOf(this.comms.size()));
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <comms.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/comme.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CommeController commController = fxmlLoader.getController();
                commController.setDataComm(comms.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }
                
                 gridComm.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridComm.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridComm.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridComm.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridComm.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridComm.setMaxHeight(Region.USE_PREF_SIZE);
                gridComm.add(anchorPane, column++, row); 
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }



    @FXML
    private void addComm(MouseEvent event) {
        
         FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/Ajoutercomm.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(Cour2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               AjoutercommController acc=Loader.getController();
                acc.client=this.c;
                acc.cour=this.cour;
                acc.setinfouserandcours(this.c,this.cour);
                Parent p=Loader.getRoot();
                Stage comm=new Stage();
                Scene scene = new Scene(p);
                comm.initStyle(StageStyle.TRANSPARENT);
                comm.setScene(scene);
                comm.show();
    }

    @FXML
    private void examen(ActionEvent event) {
    }

    @FXML
    private void Details(MouseEvent event) {
             FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/Detailscour.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(DetailscourController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               DetailscourController iec=Loader.getController();
              iec.cours=this.cour;
              
              iec.setinfoCour(this.cour);
                Parent p=Loader.getRoot();
                Stage inscriE=new Stage();
                Scene scene = new Scene(p);
                inscriE.setScene(scene);
                inscriE.show();
    }

    @FXML
    private void addRemoveLike(MouseEvent event) {
    }
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Client;
import entities.commentairepack;
import entities.packge;
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
import services.packageservice;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class Pack2Controller implements Initializable {

    @FXML
    private ImageView packpic;
    @FXML
    private JFXButton pannier;
    @FXML
    private JFXButton examen;
    @FXML
    private Text commNumber;
    @FXML
    private ImageView detail;
    @FXML
    private Text namepack;
    @FXML
    private ScrollPane scrollPaneComm;
    @FXML
    private GridPane gridComm;

    private final listda listdata = new listda();
    private final List<packge> packges = new ArrayList<>();
    
     private  List<commentairepack> comms = new ArrayList<>();
    public packge packge;
     public Client c;

    /**
     * Initializes the controller class.
     */
       public void setuserinfo(Client c)
    {
        this.c=c;
        System.out.println(this.c+"fi cour2 controller aaaaaaa");
    }
     
     public void setData(packge packge,Client c) {
        this.packge= packge;
        this.c=c;
        
      packageservice es= packageservice.getInstance();
        
        namepack.setText(packge.getNompackage());
        
           String photo;
          photo = packge.getImage();
          Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/image/"+photo);
        packpic.setImage(imageURL);
       
        //afficher les commentaires
        this.comms=es.displayComm(packge.getId());
        this.commNumber.setText(String.valueOf(this.comms.size()));
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <comms.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/commpack.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CommpackController commController = fxmlLoader.getController();
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void examen(ActionEvent event) {
    }

    @FXML
    private void addComm(MouseEvent event) {
               FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/Ajoutercomme.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(Pack2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               AjoutercommeController acc=Loader.getController();
                acc.client=this.c;
                acc.packge=this.packge;
                acc.setinfouserandcours(this.c,this.packge);
                Parent p=Loader.getRoot();
                Stage comm=new Stage();
                Scene scene = new Scene(p);
                comm.initStyle(StageStyle.TRANSPARENT);
                comm.setScene(scene);
                comm.show();
    }

    @FXML
    private void Details(MouseEvent event) {
            FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/Detailspack.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(DetailspackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               DetailspackController iec=Loader.getController();
              iec.packges=this.packge;
              
              iec.setinfoCour(this.packge);
                Parent p=Loader.getRoot();
                Stage inscriE=new Stage();
                Scene scene = new Scene(p);
                inscriE.setScene(scene);
                inscriE.show();
    }
}

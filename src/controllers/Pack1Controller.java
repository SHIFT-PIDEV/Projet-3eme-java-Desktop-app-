/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.packge;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.packageservice;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class Pack1Controller implements Initializable {

    @FXML
    private Text packname;
    @FXML
    private ImageView courPic;
    @FXML
    private Text categorie;
    @FXML
    private Button modifButton;
    @FXML
    private Button suppButton;
    @FXML
    private TextArea desc;
    @FXML
    private TextField nbcour;
    @FXML
    private TextField duree;
    @FXML
    private TextField prix;
 /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
           private packge packge;
    public void setData(packge packge) {
        this.packge=packge;
         packageservice es= packageservice.getInstance();
        packname.setText(packge.getNompackage());
        nbcour.setText(Integer.toString(packge.getNbr_cour()));
        desc.setText(packge.getDescription());
        categorie.setText(packge.getCategorie());
       String photo;
          photo = packge.getImage();
          Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/image/"+photo);
          courPic.setImage(imageURL);
      prix.setText(Float.toString(packge.getPrix()));
      duree.setText(packge.getDuree());
    }

    @FXML
    private void toUpdateForm(ActionEvent event) throws SQLException, IOException {
                FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/Updatepack.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(Pack1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
              UpdatepackController uec=Loader.getController();
               uec.setTexts( packge);
                Parent p=Loader.getRoot();
                Stage updateStage=new Stage();
                Scene scene = new Scene(p);
                updateStage.setScene(scene);
                updateStage.show();
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
                  packageservice es ;
        es=packageservice.getInstance();
        es.delete(this.packge.getId());
       Notifications.create()
                 .title("Supprimer  package")
                 .text("package  est supprimer avec success")
                 .darkStyle().position(Pos.BOTTOM_RIGHT).showConfirm();
    }
}

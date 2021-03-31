/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.cour;
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
import services.courservice;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class CourController implements Initializable {

    @FXML
    private Text courname;
    @FXML
    private ImageView courPic;
    @FXML
    private Text niveau;
    @FXML
    private Text categorie;
    @FXML
    private Button modifButton;
    @FXML
    private Button suppButton;
    @FXML
    private TextArea desc;
    @FXML
    private TextField formateur;
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
       private cour cour;
    public void setData(cour cour) {
        this.cour=cour;
         courservice es= courservice.getInstance();
        courname.setText(cour.getNom_cour());
        formateur.setText(cour.getFormateur());
        desc.setText(cour.getDescription());
        categorie.setText(cour.getCategorie());
        niveau.setText(cour.getNiveau());
       String photo;
          photo = cour.getImage_v();
          Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/image/"+photo);
          courPic.setImage(imageURL);
      prix.setText(Float.toString(cour.getPrix()));
      duree.setText(cour.getDuration());
    }
    
    

    @FXML
    private void toUpdateForm(ActionEvent event) throws SQLException, IOException {
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/updatecour.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(CourController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
              UpdatecourController uec=Loader.getController();
               uec.setTexts( cour);
                Parent p=Loader.getRoot();
                Stage updateStage=new Stage();
                Scene scene = new Scene(p);
                updateStage.setScene(scene);
                updateStage.show();
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
          courservice es ;
        es=courservice.getInstance();
        es.delete(this.cour.getId());
       Notifications.create()
                 .title("Supprimer  Cour")
                 .text("cour  est supprimer avec success")
                 .darkStyle().position(Pos.BOTTOM_RIGHT).showConfirm();
    }
    
}

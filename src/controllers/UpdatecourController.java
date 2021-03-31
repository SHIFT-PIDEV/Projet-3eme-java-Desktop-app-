/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.cour;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import services.courservice;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class UpdatecourController implements Initializable {

    @FXML
    private TextField nomcour;
    @FXML
    private TextField formateur;
    @FXML
    private TextField prix;
    @FXML
    private TextArea description;
    @FXML
    private ComboBox<String> categorie;
    @FXML
    private ComboBox<String> niveau;
    @FXML
    private TextField duration;
    @FXML
    private ImageView imgview;
    @FXML
    private Button btn_add_img;
    @FXML
    private Label babel_catsaisie;
    @FXML
    private Label label_nivsaisie;
    @FXML
    private Button btn_updateEvent;
    @FXML
    private TextField idC;
  /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void setTexts(cour cour ) throws SQLException, IOException{
         
         
        idC.setText(String.valueOf(cour.getId()));
     nomcour.setText(cour.getNom_cour());
  categorie.setValue(cour.getCategorie());
  formateur.setText(cour.getFormateur());
        prix.setText(String.valueOf(cour.getPrix()));
         description.setText(cour.getDescription());
        duration.setText(cour.getDuration());
          String photo;
          photo = cour.getImage_v();
          Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/image/"+photo);
         
        imgview.setImage(imageURL);
        niveau.setValue(cour.getNiveau());
        
  
    }
public boolean isInt(TextField t){
        try{
            int value=Integer.parseInt(t.getText());
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    @FXML
    private void add_image(ActionEvent event) {
                 FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
              WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
             imgview.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void updateEvent(ActionEvent event) throws SQLException, IOException {
         cour e=new cour();
               Image image1=null;
             image1= imgview.getImage();
             String photo = null;
             
             photo = CreatecourController.saveToFileImageNormal(image1);
             System.out.println(photo);
        
        e.setId(Integer.parseInt(idC.getText()));
        e .setNom_cour(nomcour.getText());
        e.setCategorie((String) categorie.getSelectionModel().getSelectedItem());
        e.setFormateur(formateur.getText());
        
        e.setDescription(description.getText());
         e.setImage_v(photo);
         e.setPrix(Float.valueOf(prix.getText()));
         e.setNiveau((String) niveau.getSelectionModel().getSelectedItem());
         e.setDuration(duration.getText());
         
         
         
        
      
        courservice es= courservice.getInstance();
        es.update(e);
        Notifications.create()
                 .title("Modifier Cour")
                 .text("cour  est modifier avec success")
                 .darkStyle().position(Pos.BOTTOM_RIGHT).showConfirm();
    }
        
}

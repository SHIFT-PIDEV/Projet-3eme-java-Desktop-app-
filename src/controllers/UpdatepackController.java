/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.CreatecourController.saveToFileImageNormal;
import entities.packge;
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
import services.packageservice;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class UpdatepackController implements Initializable {

    @FXML
    private TextField nompackage;
    @FXML
    private TextField nbcours;
    @FXML
    private TextField prix;
    @FXML
    private TextArea description;
    @FXML
    private Button btn_updatepack;
    @FXML
    private ComboBox<String> categorie;
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
    private TextField idP;

   /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void setTexts(packge packge ) throws SQLException, IOException{
         
         
        idP.setText(String.valueOf(packge.getId()));
     nompackage.setText(packge.getNompackage());
  categorie.setValue(packge.getCategorie());
  nbcours.setText(Integer.toString(packge.getNbr_cour()));
        prix.setText(String.valueOf(packge.getPrix()));
         description.setText(packge.getDescription());
        duration.setText(packge.getDuree());
          String photo;
          photo = packge.getImage();
          Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/image/"+photo);
         
        imgview.setImage(imageURL);
        
  
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
    private void Updateepackage(ActionEvent event) throws SQLException, IOException {
            packge e=new packge();
               Image image1=null;
             image1= imgview.getImage();
             String photo = null;
             
             photo = saveToFileImageNormal(image1);
             System.out.println(photo);
        
        e.setId(Integer.parseInt(idP.getText()));
        e .setNompackage(nompackage.getText());
        e.setCategorie((String) categorie.getSelectionModel().getSelectedItem());
        e.setNbr_cour(Integer.parseInt(nbcours.getText()));
        
        e.setDescription(description.getText());
         e.setImage(photo);
         e.setPrix(Float.valueOf(prix.getText()));
        // e.setNiveau((String) niveau.getSelectionModel().getSelectedItem());
         e.setDuree(duration.getText());
         
         
         
        
      
        packageservice es= packageservice.getInstance();
        es.update(e);
        Notifications.create()
                 .title("Modifier package")
                 .text("package  est modifier avec success")
                 .darkStyle().position(Pos.BOTTOM_RIGHT).showConfirm();
    }

    @FXML
    private void add_imag(ActionEvent event) {
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
    
}

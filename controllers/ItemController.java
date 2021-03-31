/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Client;
import entities.Panier;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import services.PanierService;

/**
 * FXML Controller class
 *
 * @author Fedy
 */
public class ItemController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Button supp;
    @FXML
    private ImageView imagecour;
    @FXML
    private Label nomItem;
    @FXML
    private Label prixItem;
    public Panier p = new Panier();
    private Client c;
        private final List<Panier> pan = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   public void setData(String nom, float prix,String image,Panier pa){
//       Image images;
//       images = new Image(getClass().getResourceAsStream(image));
       nomItem.setText(nom);
       prixItem.setText(String.valueOf(prix));
       this.p =pa;
       
//       imagecour.setImage(images);
}
   
    @FXML
    private void suppItem(ActionEvent event) {
        PanierService pserv =PanierService.getInstance();
        pserv.ViderPanier(p.getIdcour(), p.getIdclient());
      
        
    }
    
}

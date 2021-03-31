/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.categorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import services.categorieservice;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class CreatecategorieController implements Initializable {

    @FXML
    private TextField nomcat;
    @FXML
    private Button btn_createcat;
    @FXML
    private ImageView imgview;
    @FXML
    private Label babel_catsaisie;
    @FXML
    private Label label_nivsaisie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void createcategorie(ActionEvent event) {
         if (nomcat.getText().isEmpty()) 
               nomcat.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");

                
             else
             {
                  categorie c = new categorie(nomcat.getText());
            categorieservice cserv= categorieservice.getInstance();
            cserv.insert(c);
       
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("categorie insérée avec succés!");
        alert.show();
            nomcat.setText("");
                     
                     }
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button btn_Ajout;
    @FXML
    private Button btn_affichE;
    @FXML
    private Button btn_updateDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_Ajout.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/ajouterE1.fxml"));
                Scene scene = new Scene(page1);
                Stage ajoutStage=new Stage();
                //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                ajoutStage.setScene(scene);
                ajoutStage.show();
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_affichE.setOnAction(event->{
            try {
                Parent page2= FXMLLoader.load(getClass().getResource("/views/affichage.fxml"));
                Scene scene = new Scene(page2);
                //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Stage affichStage=new Stage();
                affichStage.setScene(scene);
                affichStage.show();
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btn_updateDelete.setOnAction(event->{
            try {
                Parent page2= FXMLLoader.load(getClass().getResource("/views/updateDeleteE.fxml"));
                Scene scene = new Scene(page2);
                //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Stage upSupStage=new Stage();
                upSupStage.setScene(scene);
                upSupStage.show();
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }    

    @FXML
    private void ajoutEvent(ActionEvent event) {
        
    }


    @FXML
    private void afficherE(ActionEvent event) {
    }

    @FXML
    private void suppModifEvent(ActionEvent event) {
    }
    
}

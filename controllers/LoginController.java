/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import entities.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ClientS;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class LoginController implements Initializable {

    @FXML
    private MaterialDesignIconView close;
    @FXML
    private AnchorPane cardPane;
    @FXML
    private JFXButton btn_login;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Text check;
    
    public Client client;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //JFXDepthManager.setDepth(cardPane, 2);
    }    

    @FXML
    private void closeLogin(MouseEvent event) {
          Platform.exit();
        }
    
    @FXML
    private void toTheApp(ActionEvent event) {
        ClientS cs = ClientS.getClientS();
        this.client=cs.displayefoLogin(this.username.getText(), this.password.getText());
        
       if(this.client.getId()!=-1){
           
            FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/frontEventView.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               FrontEventViewController fev=Loader.getController();
               fev.c=this.client;
               fev.setNameUserandNotif();
               fev.afficherAll();
                Parent p=Loader.getRoot();
                Stage frontView=new Stage();
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                Stage s=(Stage)this.btn_login.getScene().getWindow();
                s.close();
                frontView.show();
       }
       else if(this.username.getText().equals("admin")==true&&this.password.getText().equals("admin")==true){
           FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/dashbordv2.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dashbordv2Controller dv2c=Loader.getController();
              dv2c.setData();
                Parent p=Loader.getRoot();
                Stage dashbord=new Stage();
                Scene scene = new Scene(p);
                dashbord.setScene(scene);
                Stage s=(Stage)this.btn_login.getScene().getWindow();
                s.close();
                dashbord.show();
       }
       else{
              try {
                   Thread.sleep(3000);
                  } catch (InterruptedException ex) {
                     Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                  this.check.setText("Check your inputs");
           }
       
    }
}

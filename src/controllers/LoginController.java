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
import java.util.Random;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    public String coderecap;
    public Client client;
    @FXML
    private Label label_code_ca;
    @FXML
    private TextField tf_code_ca;
    /////////////// recaptcher bloc
 public String createRandomStringRecaptcher()
    {
            // create a string of all characters
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    // create random string builder
    StringBuilder sb = new StringBuilder();

    // create an object of Random class
    Random random = new Random();

    // specify length of random string
    int length = 5;

    for(int i = 0; i < length; i++) {

      // generate random index number
      int index = random.nextInt(alphabet.length());

      // get character specified by index
      // from the string
      char randomChar = alphabet.charAt(index);

      // append the character to string builder
      sb.append(randomChar);
    }

    String randomString = sb.toString();
    System.out.println("Random String is: " + randomString);
    return randomString;
    }
 //////////////::end bloc
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.coderecap=this.createRandomStringRecaptcher();
        System.out.println(this.coderecap);
        label_code_ca.setText(coderecap);
    }    

    @FXML
    private void closeLogin(MouseEvent event) {
          Platform.exit();
        }
    
    @FXML
    private void toTheApp(ActionEvent event) {
       if( !tf_code_ca.getText().equals(label_code_ca.getText()))
               {
                   System.out.println(tf_code_ca.getText()+" "+label_code_ca.getText());
               } else {
          System.out.println(tf_code_ca.getText()+" "+label_code_ca.getText());
        }
               
        ClientS cs = ClientS.getClientS();
        this.client=cs.displayefoLogin(this.username.getText(), this.password.getText());
        
       if(this.client.getId()!=-1&&tf_code_ca.getText().equals(label_code_ca.getText()) ){
           
            FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/frontEventView.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               FrontEventViewController fev=Loader.getController();
               fev.c=this.client;
               fev.setNameUser();
               fev.afficherAll();
                Parent p=Loader.getRoot();
                Stage frontView=new Stage();
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                Stage s=(Stage)this.btn_login.getScene().getWindow();
                s.close();
                frontView.show();
       }
       else if(this.username.getText().equals("admin")==true&&this.password.getText().equals("admin")==true&&tf_code_ca.getText().equals(label_code_ca.getText()) ){
           FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/eventsView.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Parent p=Loader.getRoot();
                Stage frontView=new Stage();
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                Stage s=(Stage)this.btn_login.getScene().getWindow();
                s.close();
                frontView.show();
       }
       else{
              try {
                   Thread.sleep(2000);
                  } catch (InterruptedException ex) {
                     Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                  this.check.setText("Check your inputs");
           }
       
    }
}

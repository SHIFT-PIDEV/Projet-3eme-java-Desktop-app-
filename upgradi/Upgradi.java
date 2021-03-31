/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author asus
 */
public class Upgradi extends Application {
    private Stage primaryStage;
    private Parent parentPage;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/login.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Upgradi.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.parentPage=loader.getRoot();
        Scene scene = new Scene(this.parentPage);
        this.primaryStage.initStyle(StageStyle.TRANSPARENT);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
       
    }
    
     public  void callStart(){
         Stage stage = new Stage();
        this.start(stage);
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud_coupon;

import java.io.IOException;
import static java.util.Locale.filter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class CRUD_coupon extends Application {
     private Stage primaryStage;
    private Parent parentPage ;
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage=primaryStage;
       this.primaryStage.setTitle("TEST CRUD 0.2");
        parentPage = FXMLLoader.load(getClass().getResource("/view/acceuil_coupon.fxml"));
        Scene scene = new  Scene(parentPage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        launch(args);
  
    }
    
}
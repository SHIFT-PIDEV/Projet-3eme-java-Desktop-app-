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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
        this.primaryStage.setTitle("Acceuil");
        System.out.println("OKOKOKOKOKO");
        try {
            parentPage = FXMLLoader.load(getClass().getResource("/views/dashbord.fxml"));
        } catch (IOException ex) {
            System.out.println("taahcheeeee");
            Logger.getLogger(Upgradi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("OKOKOKOKOKO");
        Scene scene = new Scene(parentPage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

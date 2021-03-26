/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Fedy
 */
public class upgradi extends Application {

    private Stage primaryStage;
    private Parent parentPage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Upgradi");

        parentPage = FXMLLoader.load(getClass().getResource("/upgradi/Views/acceuil.fxml"));
        Scene scene = new Scene(parentPage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        /*Stripe.apiKey = "sk_test_51IUvWkGDv01GvjnlNOkQChbbj5HQVtFKplAjyhw4FrufGbRF3HPwL1cOAFJmvH0b7uRvNgiF7X7onkeHDRwRiPeH00qEINi8FO";
        CustomerCreateParams par = CustomerCreateParams.builder().build();
        try {            
           /* CustomerListParams params = CustomerListParams.builder().build();
            CustomerCollection customers = Customer.list(params);
            System.out.println(customers);
             Customer customer = Customer.create(par);
            System.out.println(customer);
            
        } catch (Exception e) {
            System.out.println(e);
        }*/
    }

}

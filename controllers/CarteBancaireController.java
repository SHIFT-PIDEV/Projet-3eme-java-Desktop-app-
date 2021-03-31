/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.PaymentMethod;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.PaymentService;

/**
 * FXML Controller class
 *
 * @author Fedy
 */
public class CarteBancaireController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField pays;
    @FXML
    private TextField numcarte;
    @FXML
    private Button btn_createcour;
    @FXML
    private Button btn_resetAll;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField codepostal;
    @FXML
    private TextField cvc;
    @FXML
    private TextField date;
    @FXML
    private Text rq_nom;
    @FXML
    private Text rq_prenom;
    @FXML
    private Text rq_email;
    @FXML
    private Text rq_pays;
    @FXML
    private Text rq_code;
    @FXML
    private Text rq_num;
    @FXML
    private Text rq_cvc;
    @FXML
    private Text rq_date;
    public float prixTot ;
    String nomcour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(prixTot);
    }    

    @FXML
    private void paymentAction(ActionEvent event) {
         PaymentMethod c;
        //control saisie
        String at = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        String datee = "^\\d{2}/\\d{2}$";
        Pattern pattern = Pattern.compile(at);
        Matcher controler = pattern.matcher(email.getText());
        Pattern pattern2 = Pattern.compile(datee);
        Matcher controler2 = pattern2.matcher(date.getText());

        if (nom.getText().length() == 0 && prenom.getText().length() == 0 && email.getText().length() == 0 && pays.getText().length() == 0 && String.valueOf(codepostal.getText()).isEmpty() && String.valueOf(numcarte.getText()).isEmpty() && date.getText().length() == 0 && String.valueOf(cvc.getText()).isEmpty()) {

            rq_nom.setText("*Veuillez Remplir ce champ!");
            rq_prenom.setText("*Veuillez Remplir ce champ!");
            rq_email.setText("*Veuillez Remplir ce champ!");
            rq_pays.setText("*Veuillez Remplir ce champ!");
            rq_code.setText("*Veuillez Remplir ce champ!");
            rq_num.setText("*Veuillez Remplir ce champ!");
            rq_date.setText("*Veuillez Remplir ce champ!");
            rq_cvc.setText("*Veuillez Remplir ce champ!");
            //change text colors
            rq_nom.setStyle("-fx-fill: #ff0f0f ;");
            rq_prenom.setStyle("-fx-fill: #ff0f0f ;");
            rq_email.setStyle("-fx-fill: #ff0f0f ;");
            rq_pays.setStyle("-fx-fill: #ff0f0f ;");
            rq_code.setStyle("-fx-fill: #ff0f0f ;");
            rq_num.setStyle("-fx-fill: #ff0f0f ;");
            rq_date.setStyle("-fx-fill: #ff0f0f ;");
            rq_cvc.setStyle("-fx-fill: #ff0f0f ;");
            //change text field color 
            nom.setStyle("-fx-border-color: #ff0f0f ;");
            prenom.setStyle("-fx-border-color: #ff0f0f ;");
            email.setStyle("-fx-border-color: #ff0f0f ;");
            pays.setStyle("-fx-border-color: #ff0f0f ;");
            codepostal.setStyle("-fx-border-color: #ff0f0f ;");
            numcarte.setStyle("-fx-border-color: #ff0f0f ;");
            date.setStyle("-fx-border-color: #ff0f0f ;");
            cvc.setStyle("-fx-border-color: #ff0f0f ;");
        } else if (nom.getText().length() == 0) {
            rq_nom.setText("*Veuillez Remplir ce champ!");
            rq_prenom.setText("");
            rq_email.setText("");
            rq_pays.setText("");
            rq_code.setText("");
            rq_num.setText("");
            rq_date.setText("");
            rq_cvc.setText("");
            rq_nom.setStyle("-fx-fill: #ff0f0f ;");
            nom.setStyle("-fx-border-color: #ff0f0f ;");
        } else if (prenom.getText().length() == 0) {
            rq_nom.setText("");
            rq_prenom.setText("*Veuillez Remplir ce champ!");
            rq_email.setText("");
            rq_pays.setText("");
            rq_code.setText("");
            rq_num.setText("");
            rq_date.setText("");
            rq_cvc.setText("");
            rq_prenom.setStyle("-fx-fill: #ff0f0f ;");
            prenom.setStyle("-fx-border-color: #ff0f0f ;");
        } else if (email.getText().length() == 0) {
            rq_nom.setText("");
            rq_prenom.setText("");
            rq_email.setText("*Veuillez Remplir ce champ!");
            rq_pays.setText("");
            rq_code.setText("");
            rq_num.setText("");
            rq_date.setText("");
            rq_cvc.setText("");
            rq_email.setStyle("-fx-fill: #ff0f0f ;");
            email.setStyle("-fx-border-color: #ff0f0f ;");
        } else if (controler.matches() == false) {
            rq_nom.setText("");
            rq_prenom.setText("");
            rq_email.setText("*Saisie Mail invalide");
            rq_pays.setText("");
            rq_code.setText("");
            rq_num.setText("");
            rq_date.setText("");
            rq_cvc.setText("");
            rq_email.setStyle("-fx-fill: #ff0f0f ;");
            email.setStyle("-fx-border-color: #ff0f0f ;");
        } else if (pays.getText().length() == 0) {
            rq_nom.setText("");
            rq_prenom.setText("");
            rq_email.setText("");
            rq_pays.setText("*Veuillez Remplir ce champ!");
            rq_code.setText("");
            rq_num.setText("");
            rq_date.setText("");
            rq_cvc.setText("");
            rq_pays.setStyle("-fx-fill: #ff0f0f ;");
            pays.setStyle("-fx-border-color: #ff0f0f ;");
        } else if (String.valueOf(codepostal.getText()).isEmpty()) {
            rq_nom.setText("");
            rq_prenom.setText("");
            rq_email.setText("");
            rq_pays.setText("");
            rq_code.setText("*Veuillez Remplir ce champ!");
            rq_num.setText("");
            rq_date.setText("");
            rq_cvc.setText("");
            rq_code.setStyle("-fx-fill: #ff0f0f ;");
            codepostal.setStyle("-fx-border-color: #ff0f0f ;");
        } else if (String.valueOf(numcarte.getText()).isEmpty()) {
            rq_nom.setText("");
            rq_prenom.setText("");
            rq_email.setText("");
            rq_pays.setText("");
            rq_code.setText("");
            rq_num.setText("*Veuillez Remplir ce champ!");
            rq_date.setText("");
            rq_cvc.setText("");
            rq_num.setStyle("-fx-fill: #ff0f0f ;");
            numcarte.setStyle("-fx-border-color: #ff0f0f ;");
        } else if (String.valueOf(numcarte.getText()).length() != 16) {
            rq_nom.setText("");
            rq_prenom.setText("");
            rq_email.setText("");
            rq_pays.setText("");
            rq_code.setText("");
            rq_num.setText("*Carte invalide");
            rq_date.setText("");
            rq_cvc.setText("");
            rq_num.setStyle("-fx-fill: #ff0f0f ;");
            numcarte.setStyle("-fx-border-color: #ff0f0f ;");
        } else if (date.getText().length() == 0) {
            rq_nom.setText("");
            rq_prenom.setText("");
            rq_email.setText("");
            rq_pays.setText("");
            rq_code.setText("");
            rq_num.setText("");
            rq_date.setText("*Veuillez Remplir ce champ!");
            rq_cvc.setText("");
            rq_date.setStyle("-fx-fill: #ff0f0f ;");
            date.setStyle("-fx-border-color: #ff0f0f ;");
        } else if (controler2.matches() == false) {
            rq_nom.setText("");
            rq_prenom.setText("");
            rq_email.setText("");
            rq_pays.setText("");
            rq_code.setText("");
            rq_num.setText("");
            rq_date.setText("*Date invalide");
            rq_cvc.setText("");
            rq_date.setStyle("-fx-fill: #ff0f0f ;");
            date.setStyle("-fx-border-color: #ff0f0f ;");
        } else if (String.valueOf(cvc.getText()).isEmpty() || cvc.getText().length() != 3) {
            rq_nom.setText("");
            rq_prenom.setText("");
            rq_email.setText("");
            rq_pays.setText("");
            rq_code.setText("");
            rq_num.setText("");
            rq_date.setText("");
            rq_cvc.setText("*Veuillez Remplir ce champ!");
            rq_cvc.setStyle("-fx-fill: #ff0f0f ;");
            cvc.setStyle("-fx-border-color: #ff0f0f ;");
        } else { //end control saisie
            c = new PaymentMethod(nom.getText(), prenom.getText(), email.getText(), pays.getText(), Integer.parseInt(codepostal.getText()), (int) Long.parseLong(numcarte.getText()), date.getText(), Integer.parseInt(cvc.getText()));
            PaymentService cserv = PaymentService.getInstance();
            cserv.insert(c);
           FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/checkout.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CourController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CheckoutController cb = Loader.getController();
        cb.prix = this.prixTot;
       
        Parent p = Loader.getRoot();
        Stage updateStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(p);
        updateStage.setScene(scene);
       updateStage.show();
       
            System.out.println(prixTot);
            System.out.println(nomcour);
        }
    }

    @FXML
    private void resretAll(ActionEvent event) {
        nom.setText("");
        prenom.setText("");
        email.setText("");
        pays.setText("");
        codepostal.setText("");
        numcarte.setText("");
        date.setText("");
        cvc.setText("");
    }
    
}

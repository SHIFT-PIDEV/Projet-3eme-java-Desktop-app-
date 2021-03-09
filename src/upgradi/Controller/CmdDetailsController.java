/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import upgradi.Entities.PaymentMethod;
import upgradi.Services.PanierService;
import upgradi.Services.PaymentService;

/**
 *
 * @author Fedy
 */
public class CmdDetailsController implements Initializable{

    private ListDataPayment listdata = new ListDataPayment();
    @FXML
    private AnchorPane body;
    @FXML
    private TableView<PaymentMethod> tablepayment;
    @FXML
    private TableColumn<PaymentMethod, ?> nom1;
    @FXML
    private TableColumn<PaymentMethod, ?> prenom1;
    @FXML
    private TableColumn<PaymentMethod, ?> email1;
    @FXML
    private TableColumn<PaymentMethod, ?> pays1;
    @FXML
    private TableColumn<PaymentMethod, ?> codepostal1;
    @FXML
    private TableColumn<PaymentMethod, ?> num1;
    @FXML
    private TableColumn<PaymentMethod, ?> date1;
    @FXML
    private TableColumn<PaymentMethod, ?> cvv1;
    @FXML
    private ImageView userimg;
    @FXML
    private Label username;
    @FXML
    private HBox acceuil;
    @FXML
    private ImageView icon_home;
    @FXML
    private Label label_acceuil;
    @FXML
    private HBox cours;
    @FXML
    private ImageView icon_cour;
    @FXML
    private Label label_cour;
    @FXML
    private HBox panier;
    @FXML
    private ImageView icon_panier;
    @FXML
    private Label label_panier;
    @FXML
    private HBox pack;
    @FXML
    private ImageView icon_pack;
    @FXML
    private Label label_pack;
    @FXML
    private HBox demande;
    @FXML
    private ImageView icon_demande;
    @FXML
    private Label label_demande;
    @FXML
    private HBox event;
    @FXML
    private ImageView icon_event;
    @FXML
    private Label label_event;
    @FXML
    private HBox news;
    @FXML
    private ImageView icon_news;
    @FXML
    private Label label_news;
    @FXML
    private HBox reclamation;
    @FXML
    private ImageView icon_reclamation;
    @FXML
    private Label label_reclamation;
    @FXML
    private HBox wishlist;
    @FXML
    private ImageView icon_wishlist;
    @FXML
    private Label label_wishlist;
    @FXML
    private ImageView icon_signout;
    @FXML
    private Label label_signout;
    @FXML
    private HBox hbexam;
    @FXML
    private ImageView icon_exam;
    @FXML
    private Label label_exam;
    @FXML
    private Label label_cmd;

     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    tablepayment.setItems(listdata.getPaymentMethod());
     nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
     prenom1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
     email1.setCellValueFactory(new PropertyValueFactory<>("email"));
     pays1.setCellValueFactory(new PropertyValueFactory<>("pays"));
     codepostal1.setCellValueFactory(new PropertyValueFactory<>("codepostal"));
     num1.setCellValueFactory(new PropertyValueFactory<>("numcarte"));
     date1.setCellValueFactory(new PropertyValueFactory<>("datecarte"));
     cvv1.setCellValueFactory(new PropertyValueFactory<>("cvc"));
    }    
    
    @FXML
    private void coursClick(MouseEvent event) {
    }

    @FXML
    private void panierClick(MouseEvent event) {
    }

    @FXML
    private void examenClick(MouseEvent event) {
    }

    @FXML
    private void Modifier(ActionEvent event) {
        PaymentService cserv= PaymentService.getInstance();
            cserv.delete();
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/cmd.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
    }
    }
    /*@FXML
    private void actualiser(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/cmd_details.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
    }
    }*/

    @FXML
    private void valider(ActionEvent event) {
         
             try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/cmd_valide.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                
          } catch (IOException ex) {
    }
            PaymentService cserv= PaymentService.getInstance();
            cserv.deleteB();
            PanierService pserv = PanierService.getInstance();
            pserv.deleteAll();
            /*Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Commande");
            alert.setHeaderText(null);
            alert.setContentText("Commande Validé!");
            alert.show();*/
    }
    
}

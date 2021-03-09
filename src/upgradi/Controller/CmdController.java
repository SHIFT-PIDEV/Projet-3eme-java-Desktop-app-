/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Controller;

import java.io.IOException;
import java.net.URL;
import static java.util.Collections.list;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import upgradi.Entities.PaymentMethod;
import upgradi.Services.PaymentService;

/**
 * FXML Controller class
 *
 * @author Fedy
 */
public class CmdController implements Initializable {

    //
    @FXML
    private AnchorPane body;
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
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField pays;
    @FXML
    private TextField codepostal;
    @FXML
    private TextField numcarte;
    @FXML
    private TextField date;
    @FXML
    private TextField cvc;
   /* @FXML
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
    private TableView<PaymentMethod> tablepayment;*/
    @FXML
    private Button btn_payez;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    /* tablepayment.setItems(listdata.getPaymentMethod());
     nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
     prenom1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
     email1.setCellValueFactory(new PropertyValueFactory<>("email"));
     pays1.setCellValueFactory(new PropertyValueFactory<>("pays"));
     codepostal1.setCellValueFactory(new PropertyValueFactory<>("codepostal"));
     num1.setCellValueFactory(new PropertyValueFactory<>("numcarte"));
     date1.setCellValueFactory(new PropertyValueFactory<>("datecarte"));
     cvv1.setCellValueFactory(new PropertyValueFactory<>("cvc"));*/
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
    private void paymentAction(ActionEvent event) {
        PaymentMethod c;
          c = new PaymentMethod(nom.getText(),prenom.getText(),email.getText(),pays.getText(),Integer.parseInt(codepostal.getText()),Integer.parseInt(numcarte.getText()),date.getText(),Integer.parseInt(cvc.getText()));
           PaymentService cserv= PaymentService.getInstance();
            cserv.insert(c); 
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/cmd_details.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

   

  
    
}

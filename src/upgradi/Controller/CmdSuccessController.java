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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Fedy
 */
public class CmdSuccessController implements Initializable{

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
    private Label label_cmd1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    private void retour(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/acceuil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
    }
    }
    
    
}

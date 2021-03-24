/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import upgradi.Entities.Panier;

/**
 *
 * @author Fedy
 */
public class WishlistController implements Initializable {
    private ListDataWishlist listdata = new ListDataWishlist();
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
    private HBox exam;
    @FXML
    private ImageView icon_exam;
    @FXML
    private Label label_exam;
    @FXML
    private TableColumn<Panier, ?> id;
    @FXML
    private TableColumn<Panier, String> nom1;
    @FXML
    private TableColumn<Panier, String> prix1;
    @FXML
    private TextField supp;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_order;
    @FXML
    private Button btn_delete1;
    @FXML
    private TextField searchBar;
    @FXML
    private TableView<Panier> tableWishlist;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableWishlist.setItems(listdata.getPanier());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prix1.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }

    @FXML
    private void acceuilClick(MouseEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/acceuil.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void wishlistClick(MouseEvent event) {
    }


    @FXML
    private void supp_panier(ActionEvent event) {
    }

    @FXML
    private void commandez(ActionEvent event) {
    }

    @FXML
    private void Tri(ActionEvent event) {
    }

    @FXML
    private void supp_panier_all(ActionEvent event) {
    }

}

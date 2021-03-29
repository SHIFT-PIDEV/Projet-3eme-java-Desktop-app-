/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Coupon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.couponService;
import upgradi.Upgradi;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class CouponBController implements Initializable {

    @FXML
    private Text iconUserDef;
    @FXML
    private HBox dashbord;
    @FXML
    private HBox eventsInEventView;
    @FXML
    private HBox HB_examen;
    @FXML
    private HBox HB_coupon;
    @FXML
    private TextField searchBar;
    @FXML
    private TableView<Coupon> tab_coupon;
    @FXML
    private TableColumn<Coupon, Integer> col_id;
    @FXML
    private TableColumn<Coupon, String> col_type;
    @FXML
    private TableColumn<Coupon, String> col_validite;
    @FXML
    private Button btn_ajouterCoupon;
    private ListeDatacoupon listedatacoupon = new ListeDatacoupon();
    @FXML
    private HBox hb_deconnexion;
    @FXML
    private FontAwesomeIconView trash;
    @FXML
    private TextField tf_id_supp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       tab_coupon.setItems(listedatacoupon.getListCoupon());
          
           col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
           col_type.setCellValueFactory(new PropertyValueFactory <>("type"));
           col_validite.setCellValueFactory(new PropertyValueFactory <>("validite"));
            FilteredList<Coupon> filteredData = new FilteredList<>(listedatacoupon.getListCoupon(), b -> true);  
       searchBar.textProperty().addListener(((observable,oldValue,newValue) -> {

       filteredData.setPredicate(e -> {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }    
       String lowerCaseFilter = newValue.toLowerCase();
       if (e.getType().toLowerCase().contains(lowerCaseFilter) ) {
            return true; 
        }
         else  
          return false; 
        });
       }));
    SortedList<Coupon> sortedData = new SortedList<>(filteredData);  
    sortedData.comparatorProperty().bind(tab_coupon.comparatorProperty());  
    tab_coupon.setItems(sortedData);
     
    }    

    @FXML
    private void showDashbord(MouseEvent event) {
    }

    @FXML
    private void eventsView(MouseEvent event) {
         Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/eventsView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void examenview(MouseEvent event) {
        Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/examenview.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void couponBview(MouseEvent event) {
    }

    @FXML
    private void deconnexion(MouseEvent event) {
         Upgradi u=new Upgradi();
        Stage s=(Stage)this.hb_deconnexion.getScene().getWindow();
        s.close();
        u.callStart();
        }


    @FXML
    private void refreshPage(MouseEvent event) {
          Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/couponB.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void recherche(KeyEvent event) {
    }


    @FXML
    private void ajoutercoupon(ActionEvent event) {
         Parent page1=null;
        try {
            page1 = FXMLLoader.load(getClass().getResource("/views/ajouterCoupon.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(EventsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                Stage ajoutStage=new Stage();
                ajoutStage.setScene(scene);
                ajoutStage.show();
    }

    @FXML
    private void supprimer_coupon(MouseEvent event) {
         int idS=Integer.valueOf(tf_id_supp.getText());
                   couponService exSer = couponService.getInstance();
                    exSer.delete(idS);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("coupon suprimée avec succés!");
                   alert.show();
                   tf_id_supp.setText("");
                     Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/couponB.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
        
                   
                   
                
    }
  

   
    
}

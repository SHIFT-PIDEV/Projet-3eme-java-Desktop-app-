/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Coupon;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
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
import javafx.stage.Stage;
import service.couponService;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class afficher_coupon_controller implements Initializable {

    @FXML
    private TableColumn<Coupon, Integer> col_id;
    @FXML
    private TableColumn<Coupon, String> col_type;
    @FXML
    private TableColumn<Coupon, String> col_validite;
    @FXML
    private Button btn_supp;
    
    private ListeData listedata = new ListeData();
    @FXML
    private TableView<Coupon> tab_coupon;
    @FXML
    private TextField tf_id_supp;
    @FXML
    private Button btn_back_acceuil;
    @FXML
    private TextField tf_rech_av;
    @FXML
    private TextField tf_tri;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       tab_coupon.setItems(listedata.getListCoupon());
          
           col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
           col_type.setCellValueFactory(new PropertyValueFactory <>("type"));
           col_validite.setCellValueFactory(new PropertyValueFactory <>("validite"));

            
       FilteredList<Coupon> filteredData = new FilteredList<>(listedata.getListCoupon(), b -> true);  
       tf_rech_av.textProperty().addListener(((observable,oldValue,newValue) -> {

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
    private void supprimer_coupon(ActionEvent event) {
         int idS=Integer.valueOf(tf_id_supp.getText());
                   couponService exSer = couponService.getInstance();
                    exSer.delete(idS);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("coupon suprimée avec succés!");
                   alert.show();
                   tf_id_supp.setText("");
                   
                   
                   // actualiser la page 
                   try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/afficher_coupon.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(acceuil_coupon_controller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    private void Tri(ActionEvent event) {
        col_id.setSortType(TableColumn.SortType.DESCENDING);
        tab_coupon.getSortOrder().add(col_id);
        tab_coupon.sort();
    }

    @FXML
    private void back_acceuil(ActionEvent event) {
        
        try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/acceuil_coupon.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(acceuil_coupon_controller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


    
}

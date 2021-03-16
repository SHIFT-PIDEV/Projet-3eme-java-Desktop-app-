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
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.naming.Binding;
import upgradi.Entities.Panier;
import upgradi.Services.PanierService;

/**
 * FXML Controller class
 *
 * @author Fedy
 */
public class PanierController implements Initializable {

    private ListData listdata = new ListData();
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
    private TextField nom;
    @FXML
    private TextField prix;
    @FXML
    private Button btn_valider;
    @FXML
    private TableView<Panier> tablePanier;
    @FXML
    private TableColumn<Panier, ?> id;
    @FXML
    private TableColumn<Panier, ?> nom1;
    @FXML
    private TableColumn<Panier, ?> prix1;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       tablePanier.setItems(listdata.getPanier());
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
       nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
       prix1.setCellValueFactory(new PropertyValueFactory<>("prix"));
       
       FilteredList<Panier> filteredData = new FilteredList<>(listdata.getPanier(), b -> true);  
       searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
       filteredData.setPredicate(e -> {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }    
       String lowerCaseFilter = newValue.toLowerCase();
        if (e.getNom().toLowerCase().contains(lowerCaseFilter) ) {
            return true; 
        }
        else  
          return false; 
        });
       });
    SortedList<Panier> sortedData = new SortedList<>(filteredData);  
    sortedData.comparatorProperty().bind(tablePanier.comparatorProperty());  
    tablePanier.setItems(sortedData);
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
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void ajouter_panier(ActionEvent event) {
        Panier c = new Panier(nom.getText(),prix.getText());
        if(nom.getText().length() == 0 && prix.getText().length() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Aucune saisie effectué!");
            alert.show();
        }
        else if(nom.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Nom Vide");
            alert.show();
        }
        else if(prix.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Prix Vide");
            alert.show();
        }
        else{
            PanierService cserv= PanierService.getInstance();
            cserv.insert(c);
        }
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/panier.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        nom.setText("");
        prix.setText("");
    }


    @FXML
    private void supp_panier(ActionEvent event) {
        int id = Integer.parseInt(supp.getText());
            PanierService cserv= PanierService.getInstance();
            cserv.delete(id);
            
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/panier.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
    }
     @FXML
    private void supp_panier_all(ActionEvent event) {
        
            PanierService cserv= PanierService.getInstance();
            cserv.deleteAll();
            
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/panier.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void Tri(ActionEvent event) {
        prix1.setSortType(TableColumn.SortType.DESCENDING);
        tablePanier.getSortOrder().add(prix1);
        tablePanier.sort();
    }

    @FXML
    private void commandez(ActionEvent event) {
        
        if (tablePanier.getItems().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Tableau Vide!");
            alert.show();
        }
        else{
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/cmd.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

    

    
    

